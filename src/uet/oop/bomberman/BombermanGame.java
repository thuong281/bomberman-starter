package uet.oop.bomberman;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uet.oop.bomberman.scenes.Sandbox;

import javax.swing.*;

import java.io.File;
import java.net.MalformedURLException;

import static uet.oop.bomberman.scenes.Sandbox.s;

public class BombermanGame extends Application {
    public Button button, button1, button2;
    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        File file = new File("res\\sprites\\Boomber.png");

        // --> file:/C:/MyImages/myphoto.jpg
        String localUrl = file.toURI().toURL().toString();
        Image image = new Image(localUrl);
        ImageView imageView = new ImageView(image);
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
        root.getChildren().add(imageView);
        button =new Button("PlayGame");
        button1 =new Button("How to Play");
        button2 =new Button("Back");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sandbox.setupScene();
                Scene s = Sandbox.getScene();
                primaryStage.setTitle("BOmber Man v0.1");
                primaryStage.setScene(s);
                primaryStage.show();
            }
        });
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VBox layout2 =new VBox();
                layout2.getChildren().addAll(button2,root);
                Scene scene = new Scene(layout2, 594, 560);
                primaryStage.setTitle("How to Play");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        VBox layout1 =new VBox();
        layout1.getChildren().addAll(button, button1);
        Scene scene1= new Scene(layout1, 300, 200);
        primaryStage.setTitle("BOmber Man v0.1");
        primaryStage.setScene(scene1);
        primaryStage.show();
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setTitle("BOmber Man v0.1");
                primaryStage.setScene(scene1);
                primaryStage.show();
            }
        });
    }
    public static void workaround() {
    }

    public static void main(String[] args) {
        launch(args);
    }

}

