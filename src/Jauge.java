import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Jauge extends Rectangle {
    Rectangle rectangle;
    Rectangle rectangle1;
    Rotate rotate;

    public Jauge(double x, double y , double a, double b, double angle, double ox, double oy, String style, double h1, double w1 , double h2, double w2, Color color){
        rotate = new Rotate();
        rectangle= new Rectangle();
        rectangle1=new Rectangle();
        rotate.setAngle(180);
        rotate.setPivotX(851);
        rotate.setPivotY(251);


        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(w1);
        rectangle.setHeight(h1);
        rectangle.setStyle(style);

        rectangle1.setX(a);
        rectangle1.setY(b);
        rectangle1.setWidth(w2);
        rectangle1.setHeight(h2);
        rectangle1.setFill(color);

        rectangle1.getTransforms().addAll(rotate);
    }
}
