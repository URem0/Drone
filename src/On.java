import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class On {
    Button button;
    Image icon;
    ImageView myIcon;
    Circle circle;

    public On(String filename, double x, double y, double sizex, double sizey, double circle_x, double circle_y,double rad){
        this.button = new Button();
        this.icon = new Image(filename);
        this.myIcon = new ImageView(icon);
        this.circle=new Circle();

        circle.setCenterX(circle_x);
        circle.setCenterY(circle_y);
        circle.setRadius(rad);
        circle.setFill(Color.RED);
        circle.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");


        myIcon.setFitWidth(sizex);
        myIcon.setFitHeight(sizey);
        button.setGraphic(myIcon);
        button.setStyle("-fx-background-color: transparent;");
        button.relocate(x,y);

    }
}
