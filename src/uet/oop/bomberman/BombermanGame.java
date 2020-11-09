package uet.oop.bomberman;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import uet.oop.bomberman.scenes.Sandbox;

import static uet.oop.bomberman.scenes.Sandbox.s;

public class BombermanGame extends Application {


    @Override
    public void start(Stage primaryStage) {
        Sandbox.setupScene();
        Scene s = Sandbox.getScene();
        primaryStage.setTitle("BOmber Man v0.1");
        primaryStage.setScene(s);
        primaryStage.show();

    }

    public static void workaround() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());
        stage.setScene(scene);
        stage.show();
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

