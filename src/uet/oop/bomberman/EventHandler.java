package uet.oop.bomberman;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.BomberSprite;
import uet.oop.bomberman.scenes.Sandbox;

import static uet.oop.bomberman.graphics.BomberSprite.*;


public class EventHandler {
    public static void playerMovement() {

        Sandbox.s.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D) {
                Sandbox.getBomber().setVelX(2);
                animation = walkRight;
                animation.start();

            }
            if (e.getCode() == KeyCode.A) {
                Sandbox.getBomber().setVelX(-2);
                animation = walkLeft;
                animation.start();
            }
            if (e.getCode() == KeyCode.W) {
                Sandbox.getBomber().setVelY(-2);
                animation = walkUp;
                animation.start();
            }
            if (e.getCode() == KeyCode.S) {
                Sandbox.getBomber().setVelY(2);
                animation = walkDown;
                animation.start();
            }
        });

        Sandbox.s.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.D) {
                Sandbox.getBomber().setVelX(0);
                animation.reset();
                animation = standRight;
            }
            if (e.getCode() == KeyCode.A) {
                Sandbox.getBomber().setVelX(0);
                animation.reset();
                animation = standLeft;
            }
            if (e.getCode() == KeyCode.W) {
                Sandbox.getBomber().setVelY(0);
                animation.reset();
                animation = standUp;
            }
            if (e.getCode() == KeyCode.S) {
                Sandbox.getBomber().setVelY(0);
                animation.reset();
                animation = standDown;
            }
        });

    }

}
