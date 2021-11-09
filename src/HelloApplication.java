import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.control.ButtonBase;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;
import java.io.IOException;
import java.util.Date;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Pane pane = new Pane(root);
        Scene scene = new Scene( pane,800, 600);
        pane.setStyle("-fx-background-color: lightblue;");

        Jauge jauge = new Jauge(750,250,743,101,180,751,251,"-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 2;",152,10,0,8,Color.RED);
        On on = new On("on.png",350,400,100,100,408,525,10);

        Text title = new Text(175,400,"DIRECTION");
        Text title2 = new Text(575,400,"ALTITUDE");

        Image direction = new Image("drone1.png");
        Image altitude = new Image("drone1.png");

        ImageView myDirection = new ImageView(direction);
        ImageView myAltitude = new ImageView(altitude);

        myDirection.setFitHeight(200);
        myDirection.setFitWidth(200);

        myAltitude.setFitHeight(200);
        myAltitude.setFitWidth(200);

        myDirection.relocate(100,200);
        myAltitude.relocate(500,200);

        pane.getChildren().add(title);
        pane.getChildren().add(title2);
        pane.getChildren().add(myDirection);
        pane.getChildren().add(myAltitude);
        pane.getChildren().add(jauge.rectangle);
        pane.getChildren().add(jauge.rectangle1);
        pane.getChildren().add(on.button);
        pane.getChildren().add(on.circle);


        stage.setTitle("Dron'Ensea");
        stage.getIcons().add(direction);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                myDirection.relocate(100,200);
                switch (keyEvent.getCode()){
                    case Z -> {
                        myDirection.relocate( myDirection.getLayoutX(), myDirection.getLayoutY()-50);
                        break;
                    }
                    case S -> {
                        myDirection.relocate( myDirection.getLayoutX(), myDirection.getLayoutY()+50);

                        break;
                    }
                    case Q -> {
                        myDirection.relocate( myDirection.getLayoutX()-50, myDirection.getLayoutY());
                        break;
                    }
                    case D -> {
                        myDirection.relocate( myDirection.getLayoutX()+50, myDirection.getLayoutY());
                        break;
                    }
                    case W -> {
                        myDirection.relocate( 100,200);
                        break;
                    }
                    case O -> {
                        if (jauge.rectangle1.getHeight() < 150){
                            myAltitude.relocate(500,myAltitude.getLayoutY()-10);
                            jauge.rectangle1.setHeight(jauge.rectangle1.getHeight()+10);}
                        break;
                    }
                    case L-> {
                        if (jauge.rectangle1.getHeight() > 0) {
                            myAltitude.relocate(500, myAltitude.getLayoutY() + 10);
                            jauge.rectangle1.setHeight(jauge.rectangle1.getHeight() - 10);
                        }
                        break;
                    }
                }
            }
        });

        on.button.setOnAction(new EventHandler<ActionEvent>() {
        int rep = 0;
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Salut le monde !");
                on.circle.setFill(Color.GREEN);
                rep+=1;
                if (rep%2==0){
                    on.circle.setFill(Color.RED);
                }else{
                    on.circle.setFill(Color.GREEN);
                }

            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
