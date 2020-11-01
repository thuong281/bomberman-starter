package uet.oop.bomberman;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.scenes.Sandbox;

public class BombermanGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        Sandbox.setupScene();
        Scene s = Sandbox.getScene();
        primaryStage.setScene(s);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

