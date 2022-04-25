
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        Circle circle = new Circle();
        circle.setCenterX(200);
        circle.setCenterY(300);
        circle.setRadius(70.0f);
        circle.setStyle("-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 2;");

        Circle circle2 = new Circle();
        circle2.setCenterX(200);
        circle2.setCenterY(300);
        circle2.setRadius(180);
        circle2.setStyle("-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 2;");


        ligne ligne = new ligne();



        byte[] data = new byte[5];
        //ThyoneI thyoneI = new ThyoneI(); //à decommenter
        //thyoneI.getSerialNumber(data); //à decommenter

        System.out.printf("Serial Number: 0x ",data);
        for(int i =0; i< data.length; i++) System.out.printf("%02x", data[i]);
        System.out.println("\n");

        //thyoneI.THYONEI_receiveBytes(); //à decommenter

        Ordre ordre =new Ordre();


        pane.getChildren().add(direction.title);
        pane.getChildren().add(direction.title2);
        pane.getChildren().add(direction.myDirection);
        pane.getChildren().add(direction.myAltitude);
        pane.getChildren().add(jauge.rectangle);
        pane.getChildren().add(jauge.rectangle1);
        pane.getChildren().add(on.button);
        pane.getChildren().add(on.circle);
        pane.getChildren().add(circle);
        pane.getChildren().add(circle2);
        pane.getChildren().add(ligne.getCercle());
        pane.getChildren().add(ligne.getMaLigne());

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



        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });



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
                            if (ordre.getVit_lat()<ligne.vitesseLaterale_max_value){
                            //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload()));//à decommenter
                            ordre.setVit_lat(ordre.getVit_lat()+2);

                            //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload()));//à decommenter
                            System.out.println(ordre.getVit_lat());
                            ligne.setFromAngle(ordre.getAngle(), ordre.getVit_lat());
                            }}
                        if (i[0]==2){

                        }
                        break;
                    }
                    case S -> {
                        if (i[0]==1){
                            if (ordre.getVit_lat()>0){
                            ordre.setVit_lat(ordre.getVit_lat()-2);
                            //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload()));//à decommenter
                            System.out.println(ordre.getVit_lat());
                            ligne.setFromAngle(ordre.getAngle(), ordre.getVit_lat());}

                            }
                        if (i[0]==2){
                            direction2.myDirection.relocate( direction2.myDirection.getLayoutX(), direction2.myDirection.getLayoutY()+50);
                            System.out.println("Recule Drone 2");

                        }
                        break;
                    }
                    case Q -> {
                        if (i[0]==1){
                            if (ordre.getAngle()<=0){
                                ordre.setAngle(ordre.getAngle()+360-5);

                            }else{
                            ordre.setAngle(ordre.getAngle()-5);}
                            ligne.setFromAngle(ordre.getAngle(), ordre.getVit_lat());
                            //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload()));//à decommenter
                            System.out.println(ordre.getAngle());

                        }
                        if (i[0]==2){
                            direction2.myDirection.relocate( direction2.myDirection.getLayoutX()-50, direction2.myDirection.getLayoutY());
                            System.out.println("Gauche Drone 2");

                            }
                        break;
                    }
                    case D -> {
                        if (i[0]==1){
                            if (ordre.getAngle()>=360){
                                ordre.setAngle(0);

                            }else{
                            ordre.setAngle(ordre.getAngle()+5);}
                            ligne.setFromAngle(ordre.getAngle(), ordre.getVit_lat());
                            //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload()));//à decommenter
                            System.out.println(ordre.getAngle());

                        }
                        if (i[0]==2){
                            direction2.myDirection.relocate( direction2.myDirection.getLayoutX()+50, direction2.myDirection.getLayoutY());
                            System.out.println("Droite Drone 2");

                        }
                        break;
                    }
                    case W -> {
                        if (i[0]==1){
                            direction.myDirection.relocate( 100,200);
                            ordre.setVit_lat(0);
                            ordre.setVit_vert(1000);
                            ordre.setAngle(0);
                            //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload()));//à decommenter
                            jauge.rectangle1.setHeight(0);
                            direction.myAltitude.relocate(700,200);
                        }
                        if (i[0]==2){
                            direction2.myDirection.relocate( 100,200);}
                        break;
                    }
                    case O -> {
                        if (i[0]==1){
                            if (jauge.rectangle1.getHeight() < 150){
                                direction.myAltitude.relocate(700,direction.myAltitude.getLayoutY()-10);
                                jauge.rectangle1.setHeight(jauge.rectangle1.getHeight()+2);
                                System.out.println("Monte Drone 1");
                                //System.out.println(thyoneI.src);//à decommenter
                                if (ordre.getVit_vert()<2000){

                                    ordre.setVit_vert(ordre.getVit_vert()+10);

                                    //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload()));//à decommenter
                                    System.out.println(ordre.getVit_vert());
                                }

                            }}
                        if (i[0]==2){
                            if (jauge2.rectangle1.getHeight() < 150){
                                direction2.myAltitude.relocate(700,direction2.myAltitude.getLayoutY()-5);
                                jauge2.rectangle1.setHeight(jauge2.rectangle1.getHeight()+10);
                                System.out.println("Monte Drone 2");
                            }}
                        break;
                    }
                    case L-> {
                        if (i[0]==1){
                            if (jauge.rectangle1.getHeight() > 0) {
                                direction.myAltitude.relocate(700, direction.myAltitude.getLayoutY() + 10);
                                jauge.rectangle1.setHeight(jauge.rectangle1.getHeight() - 2);
                                System.out.println("Descend Drone 1");
                                if (ordre.getVit_vert()>1000){
                                ordre.setVit_vert(ordre.getVit_vert()-10);
                                //thyoneI.ThyoneI_TransmitUnicast(ordre.build_payload(),ordre.taille(ordre.build_payload())); //à decommenter
                                System.out.println(ordre.getVit_vert());
                                }

                            }}
                        if (i[0]==2){
                            if (jauge2.rectangle1.getHeight() < 150){
                                direction2.myAltitude.relocate(700,direction2.myAltitude.getLayoutY()+10);
                                jauge2.rectangle1.setHeight(jauge2.rectangle1.getHeight()-5);
                                System.out.println("Descend Drone 2");

                            }}
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
                        System.out.println("OFF");
                    }else{
                        on.circle.setFill(Color.GREEN);
                        System.out.println("ON");
                        //thyoneI.ThyoneI_TransmitUnicast(ordre.payl,ordre.taille(ordre.payl)); //à decommenter

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
