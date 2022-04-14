import java.nio.charset.StandardCharsets;

public class Ordre {
    private int vit_lat;
    private int vit_vert;
    private int angle;
    byte[] payl ="server".getBytes(StandardCharsets.US_ASCII);
    byte[] up = "up".getBytes(StandardCharsets.US_ASCII);
    byte[] down = "do".getBytes(StandardCharsets.US_ASCII);

    public int getVit_lat() {
        return vit_lat;
    }

    public int getVit_vert() {
        return vit_vert;
    }

    public int getAngle() {
        return angle;
    }

    public Ordre() {
        this.vit_lat = 0;
        this.vit_vert=1000;
        this.angle=0;
    }

    public int taille(byte[] p){
        return p.length;
    }

    public byte[] build_payload(){
        byte[] sortie = new byte[6];
        sortie[0] = (byte) (angle & 0xFF);
        sortie[1]= (byte) ((angle & 0xFF00)>>8);

        sortie[2] = (byte) (vit_vert & 0xFF);
        sortie[3]= (byte) ((vit_vert & 0xFF00)>>8);

        sortie[4] = (byte) (vit_lat & 0xFF);
        sortie[5]= (byte) ((vit_lat & 0xFF00)>>8);

        return sortie;
    }

    public void setVit_lat(int vit_lat) {
        this.vit_lat = vit_lat;
    }

    public void setVit_vert(int vit_vert) {
        this.vit_vert = vit_vert;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
