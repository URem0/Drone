import java.io.IOException;
import java.util.Date;
import com.fazecast.jSerialComm.*;

public class Transmission {
    private static SerialPort sp;


    public Transmission(){
        sp = SerialPort.getCommPort("COM4"); // device name TODO: must be changed
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }

    public static SerialPort getSp() {
        return sp;
    }

    public static void envoie(int b){

        for (Integer i = 0; i < 4; ++i) {
            try {
                System.out.println("Envoie");
                sp.getOutputStream().write(b);
                sp.getOutputStream().flush();
                Thread.sleep(600);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Fin");
        sp.closePort();
    }
}
