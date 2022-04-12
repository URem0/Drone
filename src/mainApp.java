import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class mainApp {
    public static void main(String[] args) throws UnsupportedEncodingException {

        byte[] data = new byte[5];

        // On creer l'objet thyoneI qui contient toutes les methodes utiles
        // Un objet transmit est créé lors de l'appel au constructeur de ThyoneI
        ThyoneI thyoneI = new ThyoneI();

        // #TODO: Actuellement on choisis le port COM à la compilation dans transmit.java. Ca pourrait être bien de
        //  regarder sur interner pour pouvoir afficher les différents ports dispo et en choisir un.

        // Petit test avec getSerialNumber
        thyoneI.getSerialNumber(data);


        System.out.printf("Serial Number: 0x ",data);
        for(int i =0; i< data.length; i++) System.out.printf("%02x", data[i]);
        System.out.println("\n");



        // On attends dans cet exemple de recevoir un message d'un drone
        // (le drone envoie "Waiting" quand il tente de se connecter lorsqu'on presse le Blue Button
        thyoneI.THYONEI_receiveBytes();


        // Si on a recu un message du drone alors on répond "server" et l'apparaillage est effectué
        byte[] payl = "server".getBytes(StandardCharsets.US_ASCII);


        thyoneI.ThyoneI_TransmitUnicast(payl,6);



        System.exit(0);

    }
}
