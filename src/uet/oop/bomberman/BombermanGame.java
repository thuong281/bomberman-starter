package uet.oop.bomberman;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uet.oop.bomberman.scenes.Sandbox;
import java.io.File;
import java.net.MalformedURLException;

public class BombermanGame extends Application {
    public static Button button = new Button("PlayGame");
    public static Button button1 = new Button("How to Play");
    public static Button button2 = new Button("Back");
    public static Stage window;
    public static void setScene(int num) throws MalformedURLException {
        File file = new File("res\\sprites\\Boomber.png");

        String localUrl = file.toURI().toURL().toString();
        Image image = new Image(localUrl);
        ImageView imageView = new ImageView(image);
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
        root.getChildren().add(imageView);
        switch (num){
            case 0:
                Sandbox.setupScene();
                Scene s = Sandbox.getScene();
                window.setTitle("Bomber Man v0.1");
                window.setScene(s);
                window.show();
                break;
            case 1:
                VBox layout2 =new VBox();
                layout2.getChildren().addAll(button2,root);
                Scene scene = new Scene(layout2, 594, 560);
                window.setTitle("How to Play");
                window.setScene(scene);
                window.show();
                break;
            case 2:
                VBox layout1 =new VBox();
                layout1.getChildren().addAll(button, button1);
                Scene scene1= new Scene(layout1, 300, 200);
                window.setTitle("Bomber Man v0.1");
                window.setScene(scene1);
                window.show();
                button2.setOnAction(event -> {
                    window.setTitle("Bomber Man v0.1");
                    window.setScene(scene1);
                    window.show();
                });
        }
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        button.setOnAction(event -> {
            try {
                setScene(0);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        button1.setOnAction(event -> {
            try {
                setScene(1);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });
        VBox layout1 =new VBox();
        layout1.getChildren().addAll(button, button1);
        Scene scene1= new Scene(layout1, 300, 200);
        window.setTitle("Bomber Man v0.1");
        window.setScene(scene1);
        window.show();
        button2.setOnAction(event -> {
            window.setTitle("Bomber Man v0.1");
            window.setScene(scene1);
            window.show();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

}

