import com.fazecast.jSerialComm.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class transmit implements SerialPortDataListener{

    private byte[] buffer = new byte[255];
    public byte[] RxPacket = new byte[229];
    private int RxByteCounter=0,BytesToReceive = 0;
    private byte checksum=0;
    private SerialPort serialPort;
    public CMD_Confirm[] cmdConfirmation_array = new CMD_Confirm[ThyoneI.CMDCONFIRMATIONARRAY_LENGTH];


    public transmit (){
        try {
            SerialPort[] ports = SerialPort.getCommPorts();
            List<String> list = new ArrayList<String>();
            for (SerialPort port : ports) {
                System.out.println(port.getSystemPortName());
                list.add(port.getSystemPortName());
            }

            initSerialPort(list.get(0), 115200);
            openPort();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < ThyoneI.CMDCONFIRMATIONARRAY_LENGTH; i++) {
            cmdConfirmation_array[i]= new CMD_Confirm();
        }
        Arrays.fill(RxPacket, (byte) 0);

    }

    private void initSerialPort(String name, int baud) throws Exception {
        if (serialPort != null && serialPort.isOpen()) {
            closePort();
        }
        serialPort = SerialPort.getCommPort(name);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setNumDataBits(8);
        serialPort.addDataListener(this);
        serialPort.setBaudRate(baud);


    }


    public void closePort(){
        if (serialPort != null) {
            serialPort.removeDataListener();
            serialPort.closePort();
        }
    }

    public List<String> getPortNames() {
        return Arrays.stream(SerialPort.getCommPorts())
                .map(SerialPort::getSystemPortName)
                .collect(Collectors.toList());
    }

    public boolean openPort() throws Exception {

        if (serialPort == null) {
            throw new Exception("The connection wasn't initialized");
        }

        if (serialPort.isOpen()) {
            throw new Exception("Can not connect, serial port is already open");
        }

        return serialPort.openPort();
    }


    public void sendByteImmediately(byte b) throws Exception {
        serialPort.writeBytes(new byte[]{b}, 1);
    }


    public void sendBytes(byte[] array, long length) {

        serialPort.writeBytes(array, length);
        System.out.printf("\n%d envoyé", length);
    }


    public boolean isOpen() {
        return serialPort.isOpen();
    }

    public void readBytes() {
        
        byte[] readbuf = new byte[1]; // Buffer used to read data.
        boolean i=true;
        
        do
        {
            if(serialPort.bytesAvailable() != 0) {
                serialPort.readBytes(readbuf, 1);
                i = handleOneByte(readbuf[0]);
            }

        } while(i);
    }

    public void setNullRxByteCounter() {
        RxByteCounter = 0;
    }

    @Override
    public int getListeningEvents() {

        //return SerialPort.LISTENING_EVENT_PARITY_ERROR;
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
            return;
        }

        int bytesAvailable = serialPort.bytesAvailable();
        if (bytesAvailable <= 0) {
            return;
        }

        int bytesRead = serialPort.readBytes(buffer, Math.min(buffer.length, bytesAvailable));
        for (int i=0;i< buffer.length; i++) handleOneByte(buffer[i]);
    }


    private boolean handleOneByte(byte received_byte){


        RxPacket[RxByteCounter] = received_byte;


        switch (RxByteCounter)
        {
            case 0:
                /* wait for start byte of frame */
                if (RxPacket[RxByteCounter] == ThyoneI.CMD_STX)
                {
                    BytesToReceive = 0;
                    RxByteCounter = 1;
                }
                break;

            case 1:
                /* CMD */
                RxByteCounter++;
                break;

            case 2:
                /* length field lsb */
                RxByteCounter++;
                BytesToReceive = (short)(RxPacket[RxByteCounter - 1]);
                break;

            case 3:
                /* length field msb */
                RxByteCounter++;
                BytesToReceive += (((short)RxPacket[RxByteCounter - 1]<<8) + ThyoneI.LENGTH_CMD_OVERHEAD); /* len_msb + len_lsb + crc + sfd + cmd */

                break;

            default:
                /* data field */
                RxByteCounter++;

                if (RxByteCounter == BytesToReceive)
                {

                    /* check CRC */
                    checksum = 0;
                    int i = 0;
                    for (i = 0; i < (BytesToReceive - 1); i++)
                    {
                        checksum ^= RxPacket[i];
                    }

                    if (checksum == RxPacket[BytesToReceive - 1] )
                    {
                        /* received frame ok, interpret it now */
                        HandleRxPacket(RxPacket);
                    }
                    /*else {
                        System.out.println("STOPPPP");
                        System.out.printf("%02x ",checksum);
                        System.out.printf("%02x ",RxPacket[BytesToReceive - 1]);

                    }*/

                    RxByteCounter = 0;
                    BytesToReceive = 0;
                    return false;
                }
                break;
        }
        return true;
    }

    public void HandleRxPacket(byte[] pRxBuffer)
    {
        CMD_Confirm cmdConfirmation = new CMD_Confirm();
        cmdConfirmation.cmd = ThyoneI.CNFINVALID;
        cmdConfirmation.status = false;

        short cmd_length = (short)(pRxBuffer[ThyoneI.CMD_POSITION_LENGTH_LSB]+(pRxBuffer[ThyoneI.CMD_POSITION_LENGTH_MSB]<<8));

        System.arraycopy(pRxBuffer,0,RxPacket,0,cmd_length + ThyoneI.LENGTH_CMD_OVERHEAD);

        switch (RxPacket[ThyoneI.CMD_POSITION_CMD])
        {
            case ThyoneI.THYONEI_CMD_RESET_CNF:
            case ThyoneI.THYONEI_CMD_GETSTATE_CNF:
            case ThyoneI.THYONEI_CMD_START_IND:
            case ThyoneI.THYONEI_CMD_GPIO_REMOTE_GETCONFIG_RSP:
            case ThyoneI.THYONEI_CMD_GPIO_REMOTE_READ_RSP:
            {
                cmdConfirmation.cmd = RxPacket[ThyoneI.CMD_POSITION_CMD];
                cmdConfirmation.status = false;
                break;
            }

            case ThyoneI.THYONEI_CMD_DATA_CNF:
            {
                break;
            }
            case ThyoneI.THYONEI_CMD_GET_CNF:
            case ThyoneI.THYONEI_CMD_SET_CNF:
            case ThyoneI.THYONEI_CMD_FACTORYRESET_CNF:
            case ThyoneI.THYONEI_CMD_SLEEP_CNF:
            case ThyoneI.THYONEI_CMD_GPIO_LOCAL_SETCONFIG_CNF:
            case ThyoneI.THYONEI_CMD_GPIO_LOCAL_GETCONFIG_CNF:
            case ThyoneI.THYONEI_CMD_GPIO_LOCAL_WRITE_CNF:
            case ThyoneI.THYONEI_CMD_GPIO_LOCAL_READ_CNF:
            case ThyoneI.THYONEI_CMD_GPIO_REMOTE_SETCONFIG_CNF:
            case ThyoneI.THYONEI_CMD_GPIO_REMOTE_GETCONFIG_CNF:
            case ThyoneI.THYONEI_CMD_GPIO_REMOTE_WRITE_CNF:
            case ThyoneI.THYONEI_CMD_TXCOMPLETE_RSP:
            {
                cmdConfirmation.cmd = RxPacket[ThyoneI.CMD_POSITION_CMD];
                cmdConfirmation.status = (RxPacket[ThyoneI.CMD_POSITION_DATA]!=0);
                System.out.println("Le message est envoye\n");
                break;
            }

            case ThyoneI.THYONEI_CMD_GPIO_REMOTE_READ_CNF:
            {
                cmdConfirmation.cmd = RxPacket[ThyoneI.CMD_POSITION_CMD];
                cmdConfirmation.status = false;
                break;
            }


            case ThyoneI.THYONEI_CMD_DATA_IND:
            {

                short payload_length = (short) (((((short) RxPacket[ThyoneI.CMD_POSITION_LENGTH_LSB] << 0)
                                        | ((short) RxPacket[ThyoneI.CMD_POSITION_LENGTH_MSB] << 8))) - 5);

                int sourceAddress = (int)RxPacket[ThyoneI.CMD_POSITION_DATA];
                sourceAddress = (sourceAddress << 8) + (int)RxPacket[ThyoneI.CMD_POSITION_DATA+1];
                sourceAddress = (sourceAddress << 8) + (int)RxPacket[ThyoneI.CMD_POSITION_DATA+2];
                sourceAddress = (sourceAddress << 8) + (int)RxPacket[ThyoneI.CMD_POSITION_DATA+3];


                RxCallback(RxPacket, payload_length, sourceAddress, RxPacket[ThyoneI.CMD_POSITION_DATA + 4]);

                break;
            }

            default:
            {

                break;
            }
        }

        int i = 0;
        for(i=0; i<ThyoneI.CMDCONFIRMATIONARRAY_LENGTH; i++)
        {
            if(cmdConfirmation_array[i].cmd == ThyoneI.CNFINVALID)
            {
                cmdConfirmation_array[i].cmd = cmdConfirmation.cmd;
                cmdConfirmation_array[i].status = cmdConfirmation.status;
                break;
            }
        }
    }
    private void RxCallback(byte[] payload, short payload_length, int sourceAddress, byte rssi){
        int i = 0;
        System.out.printf("Received data from address 0x%02x with %d dBm:\n-> ", sourceAddress, rssi);
        System.out.print("0x ");
        for(i=9; i<9+payload_length; i++)
        {
            System.out.printf("%02x ",  payload[i]);
        }
        System.out.print("\n-> ");
        for(i=9; i<9+payload_length; i++)
        {
            System.out.printf("%c",payload[i]);
        }
        System.out.println("\nFin de reception");

    }

}
