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
import com.fazecast.jSerialComm.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException,InterruptedException {
        Transmission transmission = new Transmission();
        Group root = new Group();
        Pane pane = new Pane(root);
        Scene scene = new Scene( pane,800, 600);
        pane.setStyle("-fx-background-color: lightblue;");

        Jauge jauge = new Jauge(750,250,743,101,180,751,251,"-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 2;",152,10,0,8,Color.RED);
        On on = new On("on.png",350,400,100,100,408,525,10);
        Direction direction = new Direction("DIRECTION","ALTITUDE","drone1.png");



        byte[] data = new byte[10];
        for (int i =0 ; i<10;i++){
            data[i]=1;
        }

        pane.getChildren().add(direction.title);
        pane.getChildren().add(direction.title2);
        pane.getChildren().add(direction.myDirection);
        pane.getChildren().add(direction.myAltitude);
        pane.getChildren().add(jauge.rectangle);
        pane.getChildren().add(jauge.rectangle1);
        pane.getChildren().add(on.button);
        pane.getChildren().add(on.circle);

        stage.setTitle("Dron'Ensea");
        stage.getIcons().add(direction.direction);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                direction.myDirection.relocate(100,200);
                switch (keyEvent.getCode()){
                    case Z -> {
                        direction.myDirection.relocate( direction.myDirection.getLayoutX(), direction.myDirection.getLayoutY()-50);
                        System.out.println("Avance");
                        Transmission.envoie(1);
                        break;
                    }
                    case S -> {
                        direction.myDirection.relocate( direction.myDirection.getLayoutX(), direction.myDirection.getLayoutY()+50);
                        System.out.println("Recule");
                        Transmission.envoie(2);
                        break;
                    }
                    case Q -> {
                        direction.myDirection.relocate( direction.myDirection.getLayoutX()-50, direction.myDirection.getLayoutY());
                        System.out.println("Gauche");
                        Transmission.envoie(3);
                        break;
                    }
                    case D -> {
                        direction.myDirection.relocate( direction.myDirection.getLayoutX()+50, direction.myDirection.getLayoutY());
                        System.out.println("Droite");
                        Transmission.envoie(4);
                        break;
                    }
                    case W -> {
                        direction.myDirection.relocate( 100,200);
                        break;
                    }
                    case O -> {
                        if (jauge.rectangle1.getHeight() < 150){
                            direction.myAltitude.relocate(500,direction.myAltitude.getLayoutY()-10);
                            jauge.rectangle1.setHeight(jauge.rectangle1.getHeight()+10);
                            System.out.println("Monte");
                            Transmission.envoie(5);}
                        break;
                    }
                    case L-> {
                        if (jauge.rectangle1.getHeight() > 0) {
                            direction.myAltitude.relocate(500, direction.myAltitude.getLayoutY() + 10);
                            jauge.rectangle1.setHeight(jauge.rectangle1.getHeight() - 10);
                            System.out.println("Descend");
                            Transmission.envoie(6);}
                        break;
                        }
                }
            }
        });

        on.button.setOnAction(new EventHandler<ActionEvent>() {
        int rep = 0;
            @Override
            public void handle(ActionEvent actionEvent) {
                direction.myDirection.relocate(100,200);
                direction.myAltitude.relocate(500,200);
                jauge.rectangle1.setHeight(0);
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
