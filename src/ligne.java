import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.lang.Math; // Importé pour Math.sin et cos et PI

public class ligne {

    // Si il y a des pb entre double et int tu doit pouvoir utilisé Double.valueOf()

    int rayon_petit_cercle = 70;
    int rayon_grand_cercle = 180;

    int vitesseLaterale_max_value = 50;

    double centre_des_cercles_X = 200;
    double centre_des_cercles_Y = 300;


    private Line maLigne = new Line(centre_des_cercles_X, centre_des_cercles_Y, 0, 0);
    private Circle cercle = new Circle();
    // Je crée aussi un cercle à mettre au bout de la ligne


    public Line getMaLigne() {
        return maLigne;
    }

    public Circle getCercle() {
        return cercle;
    }

    public ligne(){
        // constructeur: oon met les propriétés

        cercle.setRadius(10.0f);
        cercle.setFill(Color.GREY);

        maLigne.setStrokeWidth(5);
        maLigne.setStroke(Color.GREY);

        setFromAngle(0,0); // Avec angle en degrés


        // Et après tu modifie avec setFromAngle
        // tu fait angle = setFromAngle(angle) et ca update ton angle (si > à 360 ou négatif...)


    }


    public int setFromAngle(int angle, int vitesseLaterale){

        if(angle<0) angle = 360-angle;  // Vérification que l'angle soit pas négatif
        angle = angle % 360;            // Vérification que l'angle soit pas supérieur à 360

        double alpha = Math.toRadians(angle); // Conversion en Radian pour le sinus et le cosinus

        double newValX = centre_des_cercles_X, newValY = centre_des_cercles_Y; // On part du centre

        int norme = getNorm(vitesseLaterale);
        // On récupère la longueur de la ligne "normalisé" pour être entre les deux cercle

        newValX += norme*Math.cos(alpha-Math.PI/2); // On ajoute au centre les nouvelles coordonnées
        newValY += norme*Math.sin(alpha-Math.PI/2);

        // Enfin on affecte au deuxieme point de la ligne et au centre du cercle "curseur"
        maLigne.setEndX(newValX);
        maLigne.setEndY(newValY);

        cercle.setCenterX(newValX);
        cercle.setCenterY(newValY);

        return angle; // On renvoie l'angle mis a jour si jamais il est négatif ou supérieur à 360
    }

    public int getNorm(int vitesseLaterale){
        return rayon_petit_cercle + vitesseLaterale*(rayon_grand_cercle-rayon_petit_cercle)/vitesseLaterale_max_value;
        // Ce qu'on fait ici est que l'on normalise pour que à V=0 le ligne arrive sur le petit cercle
        // Et à V=Vmax on soit sur le grand
    }

}

