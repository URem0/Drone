import java.io.IOException;
import java.util.Arrays;
import com.fazecast.jSerialComm.*;
public class ThyoneI{

    byte ThyoneI_GPIO_1 = (byte)0x01;
    byte ThyoneI_GPIO_2 = (byte)0x02;
    byte ThyoneI_GPIO_3 = (byte)0x03;
    byte ThyoneI_GPIO_4 = (byte)0x04;
    byte ThyoneI_GPIO_5 = (byte)0x05;
    byte ThyoneI_GPIO_6 = (byte)0x06;

    byte ThyoneI_GPIO_IO_Disconnected  = (byte)0x00;
    byte ThyoneI_GPIO_IO_Input         = (byte)0x01;
    byte ThyoneI_GPIO_IO_Output        = (byte)0x02;
    byte ThyoneI_GPIO_IO_PWM           = (byte)0x03;

    byte ThyoneI_GPIO_Output_Low  = (byte)0x00;
    byte ThyoneI_GPIO_Output_High = (byte)0x01;

    byte ThyoneI_GPIO_Input_NoPull   = (byte)0x00;
    byte ThyoneI_GPIO_Input_PullDown = (byte)0x01;
    byte ThyoneI_GPIO_Input_PullUp   = (byte)0x02;

    short period;
    byte ratio;

    byte ThyoneI_ResetReason_PowerOn   = (byte)0x01;
    byte ThyoneI_ResetReason_PinReset  = (byte)0x02;
    byte ThyoneI_ResetReason_SoftReset = (byte)0x04;
    byte ThyoneI_ResetReason_WakeUp    = (byte)0x06;
    byte ThyoneI_ResetReason_Invalid   = (byte)0xFF;

    byte ThyoneI_State_Application = (byte)0x00;
    byte ThyoneI_State_Test        = (byte)0x01;
    byte ThyoneI_State_Invalid     = (byte)0xFF;

    byte ThyoneI_OperatingMode_Normal   = (byte)0;
    byte ThyoneI_OperatingMode_Sniffer  = (byte)1;
    byte ThyoneI_OperatingMode_Repeater = (byte)2;
    byte ThyoneI_OperatingMode_Invalid  = (byte)0xFF;

    byte ThyoneI_USERSETTING_INDEX_SERIAL_NUMBER               = (byte)0x01;
    byte ThyoneI_USERSETTING_INDEX_FW_VERSION                  = (byte)0x02;
    byte ThyoneI_USERSETTING_INDEX_UART_CONFIG                 = (byte)0x04;
    byte ThyoneI_USERSETTING_INDEX_RF_CHANNEL                  = (byte)0x07;
    byte ThyoneI_USERSETTING_INDEX_ENCRYPTION_MODE             = (byte)0x08;
    byte ThyoneI_USERSETTING_INDEX_RF_PROFILE                  = (byte)0x09;
    byte ThyoneI_USERSETTING_INDEX_RF_NUM_RETRIES              = (byte)0x0A;
    byte ThyoneI_USERSETTING_INDEX_RF_TX_POWER                 = (byte)0x0B;
    byte ThyoneI_USERSETTING_INDEX_RF_RP_NUM_SLOTS             = (byte)0x0C;
    byte ThyoneI_USERSETTING_INDEX_MAC_SOURCE_ADDRESS          = (byte)0x10;
    byte ThyoneI_USERSETTING_INDEX_MAC_DESTINATION_ADDRESS     = (byte)0x11;
    byte ThyoneI_USERSETTING_INDEX_MAC_GROUP_ID                = (byte)0x12;
    byte ThyoneI_USERSETTING_INDEX_MAC_ENCRYPTION_KEY          = (byte)0x14;
    byte ThyoneI_USERSETTING_INDEX_MAC_TLL                     = (byte)0x15;
    byte ThyoneI_USERSETTING_INDEX_CCA_MODE                    = (byte)0x16;
    byte ThyoneI_USERSETTING_INDEX_CCA_THRESHOLD               = (byte)0x17;
    byte ThyoneI_USERSETTING_INDEX_REMOTE_GPIO_CONFIG          = (byte)0x18;
    byte ThyoneI_USERSETTING_INDEX_MODULE_MODE                 = (byte)0x20;

    int ThyoneI_TXPower_8       = (int) 8;
    int ThyoneI_TXPower_4       = (int) 4;
    int ThyoneI_TXPower_0       = (int) 0;
    int ThyoneI_TXPower_minus4  = (int)-4;
    int ThyoneI_TXPower_minus8  = (int)-8;
    int ThyoneI_TXPower_minus12 = (int)-12;
    int ThyoneI_TXPower_minus16 = (int)-16;
    int ThyoneI_TXPower_minus20 = (int)-20;
    int ThyoneI_TXPower_minus40 = (int)-40;

    byte ThyoneI_BaudRateIndex_1200    = (byte)0;
    byte ThyoneI_BaudRateIndex_2400    = (byte)2;
    byte ThyoneI_BaudRateIndex_4800    = (byte)4;
    byte ThyoneI_BaudRateIndex_9600    = (byte)6;
    byte ThyoneI_BaudRateIndex_14400   = (byte)8;
    byte ThyoneI_BaudRateIndex_19200   = (byte)10;
    byte ThyoneI_BaudRateIndex_28800   = (byte)12;
    byte ThyoneI_BaudRateIndex_38400   = (byte)14;
    byte ThyoneI_BaudRateIndex_56000   = (byte)16;
    byte ThyoneI_BaudRateIndex_57600   = (byte)18;
    byte ThyoneI_BaudRateIndex_76800   = (byte)20;
    byte ThyoneI_BaudRateIndex_115200  = (byte)22;
    byte ThyoneI_BaudRateIndex_230400  = (byte)24;
    byte ThyoneI_BaudRateIndex_250000  = (byte)26;
    byte ThyoneI_BaudRateIndex_460800  = (byte)28;
    byte ThyoneI_BaudRateIndex_921600  = (byte)30;
    byte ThyoneI_BaudRateIndex_1000000 = (byte)32;

    byte ThyoneI_EncryptionMode_Unencrypted   = (byte)0;
    byte ThyoneI_EncryptionMode_TxEncrypted   = (byte)1; /* Transmit encrypted messages */
    byte ThyoneI_EncryptionMode_RxEncrypted   = (byte)2; /* Receive only encrypted messages. Discard all other messages*/
    byte ThyoneI_EncryptionMode_RxTxEncrypted = (byte)3; /* Transmit and receive encrypted messages. Discard all other messages*/
    byte ThyoneI_EncryptionMode_Invalid       = (byte)0xFF;

    byte ThyoneI_Profile_125kbit  = 0;
    byte ThyoneI_Profile_500kbit  = 1;
    byte ThyoneI_Profile_1000kbit = 2;
    byte ThyoneI_Profile_2000kbit = 3;
    byte ThyoneI_Profile_Invalid  = (byte) 255;

    byte ThyoneI_AddressMode_Broadcast = 0;
    byte ThyoneI_AddressMode_Multicast = 1;
    byte ThyoneI_AddressMode_Unicast   = 2;

    int CMD_WAIT_TIME = 1500;
    static byte CNFINVALID = (byte) 255;

    static byte LENGTH_CMD_OVERHEAD             =5;
    byte LENGTH_CMD_OVERHEAD_WITHOUT_CRC = (byte) (LENGTH_CMD_OVERHEAD - 1);
    static int MAX_PAYLOAD_LENGTH              = (byte) 224;
    byte MAX_PAYLOAD_LENGTH_MULTICAST_EX = (byte) 223;
    byte MAX_PAYLOAD_LENGTH_UNICAST_EX   = (byte) 220;
    static byte MAX_CMD_LENGTH = (byte) (MAX_PAYLOAD_LENGTH + LENGTH_CMD_OVERHEAD);

    static byte CMD_POSITION_STX        =0;
    static byte CMD_POSITION_CMD        =1;
    static byte CMD_POSITION_LENGTH_LSB =2;
    static byte CMD_POSITION_LENGTH_MSB =3;
    static byte CMD_POSITION_DATA       =4;

    static byte CMD_STX = 0x02;

    static final byte THYONEI_CMD_TYPE_REQ =(byte) (0 << 6);
    static final byte THYONEI_CMD_TYPE_CNF =(byte) (1 << 6);
    static final byte THYONEI_CMD_TYPE_IND = (byte) (2 << 6);
    static final byte THYONEI_CMD_TYPE_RSP =(byte) (3 << 6);

    static final byte THYONEI_CMD_RESET =0x00;
    static final byte THYONEI_CMD_RESET_REQ =(THYONEI_CMD_RESET | THYONEI_CMD_TYPE_REQ);


    static final int THYONEI_CMD_RESET_CNF =(THYONEI_CMD_RESET | THYONEI_CMD_TYPE_CNF);

    static final  byte THYONEI_CMD_GETSTATE= 0x01;
    static final byte THYONEI_CMD_GETSTATE_REQ =(THYONEI_CMD_GETSTATE | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GETSTATE_CNF =(THYONEI_CMD_GETSTATE | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_SLEEP =0x02;
    static final byte THYONEI_CMD_SLEEP_REQ =(THYONEI_CMD_SLEEP | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_SLEEP_CNF =(THYONEI_CMD_SLEEP | THYONEI_CMD_TYPE_CNF);
    static final byte THYONEI_CMD_SLEEP_IND =(THYONEI_CMD_SLEEP | THYONEI_CMD_TYPE_IND);

    static final byte THYONEI_CMD_START_IND =0x73;

    static final byte THYONEI_CMD_UNICAST_DATA= 0x04;
    static final byte THYONEI_CMD_UNICAST_DATA_REQ =(THYONEI_CMD_UNICAST_DATA | THYONEI_CMD_TYPE_REQ);

    /* Transmissions of any kind will be confirmed and indicated by the same message CMD_DATA_CNF od CMD_DATA_IND*/
    static final byte THYONEI_CMD_DATA_CNF =(THYONEI_CMD_UNICAST_DATA | THYONEI_CMD_TYPE_CNF);
    static final byte THYONEI_CMD_DATA_IND =(THYONEI_CMD_UNICAST_DATA | THYONEI_CMD_TYPE_IND);
    static final byte THYONEI_CMD_TXCOMPLETE_RSP =(THYONEI_CMD_UNICAST_DATA | THYONEI_CMD_TYPE_RSP);

    static final byte THYONEI_CMD_MULTICAST_DATA =0x05;
    static final byte THYONEI_CMD_MULTICAST_DATA_REQ =(THYONEI_CMD_MULTICAST_DATA | THYONEI_CMD_TYPE_REQ);

    static final byte THYONEI_CMD_BROADCAST_DATA =0x06;
    static final byte THYONEI_CMD_BROADCAST_DATA_REQ =(THYONEI_CMD_BROADCAST_DATA | THYONEI_CMD_TYPE_REQ);

    static final byte THYONEI_CMD_UNICAST_DATA_EX =0x07;
    static final byte THYONEI_CMD_UNICAST_DATA_EX_REQ =(THYONEI_CMD_UNICAST_DATA_EX | THYONEI_CMD_TYPE_REQ);

    static final byte THYONEI_CMD_MULTICAST_DATA_EX= 0x08;
    static final byte THYONEI_CMD_MULTICAST_DATA_EX_REQ =(THYONEI_CMD_MULTICAST_DATA_EX | THYONEI_CMD_TYPE_REQ);

    static final byte THYONEI_CMD_SETCHANNEL= 0x09;
    static final byte THYONEI_CMD_SETCHANNEL_REQ= (THYONEI_CMD_SETCHANNEL | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_SETCHANNEL_CNF =(THYONEI_CMD_SETCHANNEL | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GET =0x10;
    static final byte THYONEI_CMD_GET_REQ =(THYONEI_CMD_GET | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GET_CNF =(THYONEI_CMD_GET | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_SET= 0x11;
    static final byte THYONEI_CMD_SET_REQ =(THYONEI_CMD_SET | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_SET_CNF= (THYONEI_CMD_SET | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_FACTORYRESET =0x1C;
    static final byte THYONEI_CMD_FACTORYRESET_REQ=(THYONEI_CMD_FACTORYRESET | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_FACTORYRESET_CNF =(THYONEI_CMD_FACTORYRESET | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GPIO_LOCAL_SETCONFIG= 0x25;
    static final byte THYONEI_CMD_GPIO_LOCAL_SETCONFIG_REQ= (THYONEI_CMD_GPIO_LOCAL_SETCONFIG | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_LOCAL_SETCONFIG_CNF =(THYONEI_CMD_GPIO_LOCAL_SETCONFIG | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GPIO_LOCAL_GETCONFIG =0x26;
    static final byte THYONEI_CMD_GPIO_LOCAL_GETCONFIG_REQ =(THYONEI_CMD_GPIO_LOCAL_GETCONFIG | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_LOCAL_GETCONFIG_CNF= (THYONEI_CMD_GPIO_LOCAL_GETCONFIG | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GPIO_LOCAL_WRITE= 0x27;
    static final byte THYONEI_CMD_GPIO_LOCAL_WRITE_REQ= (THYONEI_CMD_GPIO_LOCAL_WRITE | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_LOCAL_WRITE_CNF= (THYONEI_CMD_GPIO_LOCAL_WRITE | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GPIO_LOCAL_READ =0x28;
    static final byte THYONEI_CMD_GPIO_LOCAL_READ_REQ =(THYONEI_CMD_GPIO_LOCAL_READ | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_LOCAL_READ_CNF =(THYONEI_CMD_GPIO_LOCAL_READ | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GPIO_REMOTE_SETCONFIG =0x29;
    static final byte THYONEI_CMD_GPIO_REMOTE_SETCONFIG_REQ =(THYONEI_CMD_GPIO_REMOTE_SETCONFIG | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_REMOTE_SETCONFIG_CNF=(THYONEI_CMD_GPIO_REMOTE_SETCONFIG | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GPIO_REMOTE_GETCONFIG =0x2A;
    static final byte THYONEI_CMD_GPIO_REMOTE_GETCONFIG_REQ=(THYONEI_CMD_GPIO_REMOTE_GETCONFIG | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_REMOTE_GETCONFIG_CNF= (THYONEI_CMD_GPIO_REMOTE_GETCONFIG | THYONEI_CMD_TYPE_CNF);
    static final byte THYONEI_CMD_GPIO_REMOTE_GETCONFIG_RSP =(THYONEI_CMD_GPIO_REMOTE_GETCONFIG | THYONEI_CMD_TYPE_RSP);

    static final byte THYONEI_CMD_GPIO_REMOTE_WRITE= (byte)0x2B;
    static final byte THYONEI_CMD_GPIO_REMOTE_WRITE_REQ =(THYONEI_CMD_GPIO_REMOTE_WRITE | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_REMOTE_WRITE_CNF =(THYONEI_CMD_GPIO_REMOTE_WRITE | THYONEI_CMD_TYPE_CNF);

    static final byte THYONEI_CMD_GPIO_REMOTE_READ =(byte)0x2C;
    static final byte THYONEI_CMD_GPIO_REMOTE_READ_REQ =(THYONEI_CMD_GPIO_REMOTE_READ | THYONEI_CMD_TYPE_REQ);
    static final byte THYONEI_CMD_GPIO_REMOTE_READ_CNF= (THYONEI_CMD_GPIO_REMOTE_READ | THYONEI_CMD_TYPE_CNF);
    static final byte THYONEI_CMD_GPIO_REMOTE_READ_RSP =(THYONEI_CMD_GPIO_REMOTE_READ | THYONEI_CMD_TYPE_RSP);
    static final byte[] CMD_Array = new byte[229]; /* for UART TX to module*/

    public int src;

    public int CMD_ARRAY_SIZE(){
        return ((((int)CMD_Array[CMD_POSITION_LENGTH_LSB] << 0) | ((int)CMD_Array[CMD_POSITION_LENGTH_MSB] << 8)) + LENGTH_CMD_OVERHEAD);
    }


    /////// ATTENTION, BYTE = SIGNE EN JAVA DONC POUR LES VALEURS SUPERIEURES A 128 IL FAUT CONVERTIR SI ON VEUT COMPARER
    // EXEMPLE :   if (length <= (int)( 0xff & MAX_PAYLOAD_LENGTH))

    static int CMDCONFIRMATIONARRAY_LENGTH = 3;

    private transmit Serial;


    public ThyoneI(){

        Serial = new transmit();


    }
    public void THYONEI_receiveBytes()
    {
        Serial.readBytes();
    }


    private boolean Wait4CNF(int max_time_ms, int expectedCmdConfirmation, boolean expectedStatus, boolean reset_confirmstate)
    {

        double diff= 0;
        long start, stop;
        start = System.currentTimeMillis();


        Serial.setNullRxByteCounter();
        if (reset_confirmstate) {
            for (int i = 0; i < CMDCONFIRMATIONARRAY_LENGTH; i++) {
                Serial.cmdConfirmation_array[i].cmd = CNFINVALID;
            }
        }

        while (true) {
            stop = System.currentTimeMillis();


            THYONEI_receiveBytes();
            if (diff >= max_time_ms)
            {
                break;
            }
            for (int i = 0; i < CMDCONFIRMATIONARRAY_LENGTH; i++) {
                if (expectedCmdConfirmation == Serial.cmdConfirmation_array[i].cmd) {
                    return (Serial.cmdConfirmation_array[i].status == expectedStatus);
                }
            }
        }
        return false;
    }


    public boolean ThyoneI_Get(ThyoneI_UserSettings_t userSetting, byte[] ResponseP, int Response_LengthP) {
        boolean ret = false;

        /* fill CMD_ARRAY packet */
        CMD_Array[CMD_POSITION_STX] = CMD_STX;
        CMD_Array[CMD_POSITION_CMD] = THYONEI_CMD_GET_REQ;
        CMD_Array[CMD_POSITION_LENGTH_LSB] = 1;
        CMD_Array[CMD_POSITION_LENGTH_MSB] = 0;
        CMD_Array[CMD_POSITION_DATA] = userSetting.value;

        if (FillChecksum(CMD_Array, CMD_ARRAY_SIZE()))
        {

            // now send CMD_ARRAY
            Serial.sendBytes(CMD_Array, CMD_ARRAY_SIZE());

            /* wait for cnf */
            if (Wait4CNF(CMD_WAIT_TIME, THYONEI_CMD_GET_CNF, false, true))
            {
                int length = ((int) Serial.RxPacket[CMD_POSITION_LENGTH_LSB] << 0) + ((int) Serial.RxPacket[CMD_POSITION_LENGTH_MSB] << 8);
                System.arraycopy(Serial.RxPacket,CMD_POSITION_DATA + 1,ResponseP,0,length - 1 );
                ret = true;
            }
        }
        return ret;
    }

    public boolean getSerialNumber(byte[] serialNumberP)
    {
        int length=0;
        ThyoneI_UserSettings_t userSetting = new ThyoneI_UserSettings_t();
        return ThyoneI_Get(userSetting, serialNumberP, length);
    }


    private Boolean FillChecksum(byte[] pArray, int length)
    {
        Boolean ret = false;

        if ((length >= LENGTH_CMD_OVERHEAD) && (pArray[0] == CMD_STX))
        {

            byte checksum = 0;
            int payload_length = (pArray[CMD_POSITION_LENGTH_MSB] << 8) + pArray[CMD_POSITION_LENGTH_LSB];
            int i = 0;
            for (i = 0;i < (payload_length + LENGTH_CMD_OVERHEAD_WITHOUT_CRC); i++)
            {
                checksum ^= pArray[i];
            }
            pArray[payload_length + LENGTH_CMD_OVERHEAD_WITHOUT_CRC] =  checksum;

            ret = true;
        }
        return ret;
    }

    public boolean ThyoneI_TransmitBroadcast(byte[] payloadP, int length) {


        boolean ret = false;

        if (length <= (int)( 0xff & MAX_PAYLOAD_LENGTH)) {
            CMD_Array[CMD_POSITION_STX] = CMD_STX;
            CMD_Array[CMD_POSITION_CMD] = THYONEI_CMD_BROADCAST_DATA_REQ;
            CMD_Array[CMD_POSITION_LENGTH_LSB] = (byte) (length >> 0);
            CMD_Array[CMD_POSITION_LENGTH_MSB] = (byte) (length >> 8);


            System.arraycopy(payloadP,0,CMD_Array,CMD_POSITION_DATA,length);

            if (FillChecksum(CMD_Array, CMD_ARRAY_SIZE())) {

                Serial.sendBytes(CMD_Array, CMD_ARRAY_SIZE());
                ret = Wait4CNF(CMD_WAIT_TIME, THYONEI_CMD_TXCOMPLETE_RSP, false, true);
            }
        }

        return ret;
    }
    boolean ThyoneI_TransmitUnicastExtended(int address, byte[] payloadP, int length)
    {
        boolean ret = false;
        if (length <= MAX_PAYLOAD_LENGTH_UNICAST_EX)
        {
            int cmdLength = length + 4;
            CMD_Array[CMD_POSITION_STX] = CMD_STX;
            CMD_Array[CMD_POSITION_CMD] = THYONEI_CMD_UNICAST_DATA_EX_REQ;
            CMD_Array[CMD_POSITION_LENGTH_LSB] = (byte) (cmdLength >> 0);
            CMD_Array[CMD_POSITION_LENGTH_MSB] = (byte) (cmdLength >> 8);
            byte[] addr= new byte[4] ;
            addr[0]= (byte) (0xFF &  address);
            addr[1]= (byte) (0xFF & address>>8);
            addr[2]= (byte) (0xFF & address>>16);
            addr[3]= (byte) (0xFF & address>>24);


            System.arraycopy(addr,0,CMD_Array,CMD_POSITION_DATA,4);
            System.arraycopy(payloadP,CMD_POSITION_DATA,CMD_Array,CMD_POSITION_DATA+4,length);


            if (FillChecksum(CMD_Array, CMD_ARRAY_SIZE()))
            {
                Serial.sendBytes(CMD_Array, CMD_ARRAY_SIZE());
                ret = Wait4CNF(CMD_WAIT_TIME, THYONEI_CMD_TXCOMPLETE_RSP, false, true);
            }
        }

        return ret;
    }
    boolean ThyoneI_TransmitUnicast(byte[] payloadP, int length)
    {
        boolean ret = false;
        if (length <= (int)( 0xff & MAX_PAYLOAD_LENGTH))
        {
            int cmdLength = length + 4;
            CMD_Array[CMD_POSITION_STX] = CMD_STX;
            CMD_Array[CMD_POSITION_CMD] = THYONEI_CMD_UNICAST_DATA_EX_REQ;
            CMD_Array[CMD_POSITION_LENGTH_LSB] = (byte) (cmdLength >> 0);
            CMD_Array[CMD_POSITION_LENGTH_MSB] = (byte) (cmdLength >> 8);

            System.arraycopy(Serial.sourceaddr,0,CMD_Array,CMD_POSITION_DATA,4);
            System.arraycopy(payloadP,0,CMD_Array,CMD_POSITION_DATA+4,length);


            if (FillChecksum(CMD_Array, CMD_ARRAY_SIZE()))
            {
                Serial.sendBytes(CMD_Array, CMD_ARRAY_SIZE());
                ret = Wait4CNF(CMD_WAIT_TIME, THYONEI_CMD_TXCOMPLETE_RSP, false, true);
            }
        }

        return ret;
    }


}