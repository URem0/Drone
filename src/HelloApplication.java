import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
        Group root2 = new Group();
        Pane pane = new Pane(root);
        Pane pane2 = new Pane(root2);
        Tab tab = new Tab("Drone 1",pane);
        Tab tab2 = new Tab("Drone 2",pane2);
        pane.setStyle("-fx-background-color: lightblue;");
        TabPane tabPane = new TabPane(tab,tab2);
        Scene scene = new Scene(tabPane,1000, 600);

        Jauge jauge = new Jauge(950,250,743,101,180,751,251,"-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 2;",152,10,0,8,Color.RED);
        On on = new On("on.png",450,400,100,100,508,525,10);
        Direction direction = new Direction("DIRECTION","ALTITUDE","drone1.png");

        Jauge jauge2 = new Jauge(950,250,743,101,180,751,251,"-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 2;",152,10,0,8,Color.RED);
        On on2 = new On("on.png",450,400,100,100,508,525,10);
        Direction direction2 = new Direction("DIRECTION","ALTITUDE","drone1.png");



        pane.getChildren().add(direction.title);
        pane.getChildren().add(direction.title2);
        pane.getChildren().add(direction.myDirection);
        pane.getChildren().add(direction.myAltitude);
        pane.getChildren().add(jauge.rectangle);
        pane.getChildren().add(jauge.rectangle1);
        pane.getChildren().add(on.button);
        pane.getChildren().add(on.circle);

        pane2.getChildren().add(direction2.title);
        pane2.getChildren().add(direction2.title2);
        pane2.getChildren().add(direction2.myDirection);
        pane2.getChildren().add(direction2.myAltitude);
        pane2.getChildren().add(jauge2.rectangle);
        pane2.getChildren().add(jauge2.rectangle1);
        pane2.getChildren().add(on2.button);
        pane2.getChildren().add(on2.circle);

        stage.setTitle("Dron'Ensea");
        stage.getIcons().add(direction.direction);
        stage.setScene(scene);
        stage.show();


        final int[] i = {1};

        tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab.isSelected()) {
                    i[0]=1;
                    System.out.println(i[0]);

                }
            }
        });

        tab2.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab2.isSelected()) {
                    i[0]=2;
                    System.out.println(i[0]);

                }
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                transmission.getSp().openPort();
                direction.myDirection.relocate(100,200);
                direction2.myDirection.relocate(100,200);
                switch (keyEvent.getCode()){
                    case Z -> {
                        if (i[0]==1){
                            direction.myDirection.relocate( direction.myDirection.getLayoutX(), direction.myDirection.getLayoutY()-50);
                            System.out.println("Avance Drone 1");
                            Transmission.envoie(1);}
                        if (i[0]==2){
                            direction2.myDirection.relocate( direction2.myDirection.getLayoutX(), direction2.myDirection.getLayoutY()-50);
                            System.out.println("Avance Drone 2");
                            Transmission.envoie(1);}
                        break;
                    }
                    case S -> {
                        if (i[0]==1){
                            direction.myDirection.relocate( direction.myDirection.getLayoutX(), direction.myDirection.getLayoutY()+50);
                            System.out.println("Recule Drone 1");
                            Transmission.envoie(2);}
                        if (i[0]==2){
                            direction2.myDirection.relocate( direction2.myDirection.getLayoutX(), direction2.myDirection.getLayoutY()+50);
                            System.out.println("Recule Drone 2");
                            Transmission.envoie(2);}
                        break;
                    }
                    case Q -> {
                        if (i[0]==1){
                            direction.myDirection.relocate( direction.myDirection.getLayoutX()-50, direction.myDirection.getLayoutY());
                            System.out.println("Gauche Drone 1");
                            Transmission.envoie(3);}
                        if (i[0]==2){
                            direction2.myDirection.relocate( direction2.myDirection.getLayoutX()-50, direction2.myDirection.getLayoutY());
                            System.out.println("Gauche Drone 2");
                            Transmission.envoie(3);}
                        break;
                    }
                    case D -> {
                        if (i[0]==1){
                            direction.myDirection.relocate( direction.myDirection.getLayoutX()+50, direction.myDirection.getLayoutY());
                            System.out.println("Droite Drone 1");
                            Transmission.envoie(4);}
                        if (i[0]==2){
                            direction2.myDirection.relocate( direction2.myDirection.getLayoutX()+50, direction2.myDirection.getLayoutY());
                            System.out.println("Droite Drone 2");
                            Transmission.envoie(4);}
                        break;
                    }
                    case W -> {
                        if (i[0]==1){
                            direction.myDirection.relocate( 100,200);}
                        if (i[0]==2){
                            direction2.myDirection.relocate( 100,200);}
                        break;
                    }
                    case O -> {
                        if (i[0]==1){
                            if (jauge.rectangle1.getHeight() < 150){
                                direction.myAltitude.relocate(700,direction.myAltitude.getLayoutY()-10);
                                jauge.rectangle1.setHeight(jauge.rectangle1.getHeight()+10);
                                System.out.println("Monte Drone 1");
                                Transmission.envoie(5);}}
                        if (i[0]==2){
                            if (jauge2.rectangle1.getHeight() < 150){
                                direction2.myAltitude.relocate(700,direction2.myAltitude.getLayoutY()-10);
                                jauge2.rectangle1.setHeight(jauge2.rectangle1.getHeight()+10);
                                System.out.println("Monte Drone 2");
                                Transmission.envoie(5);}}
                        break;
                    }
                    case L-> {
                        if (i[0]==1){
                            if (jauge.rectangle1.getHeight() > 0) {
                                direction.myAltitude.relocate(700, direction.myAltitude.getLayoutY() + 10);
                                jauge.rectangle1.setHeight(jauge.rectangle1.getHeight() - 10);
                                System.out.println("Descend Drone 1");
                                Transmission.envoie(6);}}
                        if (i[0]==2){
                            if (jauge2.rectangle1.getHeight() < 150){
                                direction2.myAltitude.relocate(700,direction2.myAltitude.getLayoutY()-10);
                                jauge2.rectangle1.setHeight(jauge2.rectangle1.getHeight()+10);
                                System.out.println("Descend Drone 2");
                                Transmission.envoie(5);}}
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
                    direction.myAltitude.relocate(700,200);
                    jauge.rectangle1.setHeight(0);
                    System.out.println("Salut le monde !");
                    on.circle.setFill(Color.GREEN);
                    rep+=1;
                    if (rep%2==0){
                        on.circle.setFill(Color.RED);
                        Transmission.envoie(7);
                        System.out.println("OFF");
                    }else{
                        on.circle.setFill(Color.GREEN);
                        Transmission.envoie(8);
                        System.out.println("OFF");
                    }


                    }

        });
        on2.button.setOnAction(new EventHandler<ActionEvent>() {
            int rep = 0;
            @Override
            public void handle(ActionEvent actionEvent) {

                    direction2.myDirection.relocate(100,200);
                    direction2.myAltitude.relocate(700,200);
                    jauge2.rectangle1.setHeight(0);
                    System.out.println("Salut le monde !");
                    on2.circle.setFill(Color.GREEN);
                    rep+=1;
                    if (rep%2==0){
                        on2.circle.setFill(Color.RED);
                        Transmission.envoie(7);
                        System.out.println("OFF");
                    }else{
                        on2.circle.setFill(Color.GREEN);
                        Transmission.envoie(8);
                        System.out.println("OFF");
                    }
                }

        });
    }

    public static void main(String[] args) {
        launch();

    }
}
