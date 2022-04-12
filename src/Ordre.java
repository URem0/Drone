import java.nio.charset.StandardCharsets;

public class Ordre {
    byte[] payl ="server".getBytes(StandardCharsets.US_ASCII);
    byte[] up = "up".getBytes(StandardCharsets.US_ASCII);
    byte[] down = "do".getBytes(StandardCharsets.US_ASCII);

    public int taille(byte[] p){
        return p.length;
    }


}
