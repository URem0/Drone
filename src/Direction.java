import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Direction {
    Text title;
    Text title2;
    Image direction;
    Image altitude;
    ImageView myDirection;
    ImageView myAltitude;

    public Direction(String title,String title2, String filename){
        this.title = new Text(175,400,title);
        this.title2 = new Text(775,400,title2);
        this.direction= new Image(filename);
        this.altitude=new Image(filename);
        this.myDirection=new ImageView(direction);
        this.myAltitude = new ImageView(altitude);

        myDirection.setFitHeight(200);
        myDirection.setFitWidth(200);

        myAltitude.setFitHeight(200);
        myAltitude.setFitWidth(200);

        myDirection.relocate(100,200);
        myAltitude.relocate(700,200);

    }


}
