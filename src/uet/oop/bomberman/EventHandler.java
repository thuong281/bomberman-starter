package uet.oop.bomberman;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.scenes.Sandbox;

import static uet.oop.bomberman.entities.Bomber.getStep;
import static uet.oop.bomberman.graphics.BomberSprite.*;
import static uet.oop.bomberman.scenes.Sandbox.*;


public class EventHandler {
    public static void playerMovement() {

        Sandbox.s.setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT)) {

                    getBomber().setVelX(getStep());
                    animation = walkRight;
                    animation.start();


            }
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                getBomber().setVelX(-getStep());
                animation = walkLeft;
                animation.start();
            }
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                getBomber().setVelY(-getStep());
                animation = walkUp;
                animation.start();
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                getBomber().setVelY(getStep());
                animation = walkDown;
                animation.start();
            }
            if (e.getCode() == KeyCode.X) {
                Platform.exit();
            }
        });

        Sandbox.s.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                getBomber().setVelX(0);
                animation.reset();
                animation = standRight;
            }
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                getBomber().setVelX(0);
                animation.reset();
                animation = standLeft;
            }
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                getBomber().setVelY(0);
                animation.reset();
                animation = standUp;
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                getBomber().setVelY(0);
                animation.reset();
                animation = standDown;
            }
        });

    }

}
