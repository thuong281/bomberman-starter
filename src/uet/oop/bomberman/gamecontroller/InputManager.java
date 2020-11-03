package uet.oop.bomberman.gamecontroller;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;

import uet.oop.bomberman.graphics.Animation.BomberManAnimation;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scenes.Sandbox;

import java.util.List;

public class InputManager {

    public static void handlePlayerMovements() {
        List keyboardInputs = EventHandler.getInputList();
        Bomber bomber = Sandbox.getBomber();

        if (keyboardInputs.contains(KeyCode.UP) || keyboardInputs.contains(KeyCode.W)) {
            bomber.move(Direction.UP);
        }
        if (keyboardInputs.contains(KeyCode.DOWN) || keyboardInputs.contains(KeyCode.S)) {
            bomber.move(Direction.DOWN);
        }
        if (keyboardInputs.contains(KeyCode.LEFT) || keyboardInputs.contains(KeyCode.A)) {
            bomber.move(Direction.LEFT);
        }
        if (keyboardInputs.contains(KeyCode.RIGHT) || keyboardInputs.contains(KeyCode.D)) {
            bomber.move(Direction.RIGHT);
        }
        if (!keyboardInputs.contains(KeyCode.LEFT) &&
                !keyboardInputs.contains(KeyCode.RIGHT) &&
                !keyboardInputs.contains(KeyCode.UP) &&
                !keyboardInputs.contains(KeyCode.DOWN) &&
                !keyboardInputs.contains(KeyCode.W) &&
                !keyboardInputs.contains(KeyCode.A) &&
                !keyboardInputs.contains(KeyCode.S) &&
                !keyboardInputs.contains(KeyCode.D)
        ) {
            if (Sandbox.getBomber().getAnimation() == BomberManAnimation.walkDown) bomber.move(0, Direction.DOWN);
            if (Sandbox.getBomber().getAnimation() == BomberManAnimation.walkUp) bomber.move(0, Direction.UP);
            if (Sandbox.getBomber().getAnimation() == BomberManAnimation.walkLeft) bomber.move(0, Direction.LEFT);
            if (Sandbox.getBomber().getAnimation() == BomberManAnimation.walkRight) bomber.move(0, Direction.RIGHT);
        }

        if (keyboardInputs.contains(KeyCode.SPACE)) {
            if (bomber.hasMoreBombs()) {
                Bomb newBomb = new Bomb(((bomber.getX() + 8) / 32) * Sprite.SCALED_SIZE,
                        ((bomber.getY() + 8) / 32) * Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                Sandbox.addEntityToGame(newBomb);
                newBomb.getPutBomb().start();
                bomber.decrementBombCount();
            } else {
                System.out.println("het bom");
            }
            keyboardInputs.remove(KeyCode.SPACE);
        }
    }
}
