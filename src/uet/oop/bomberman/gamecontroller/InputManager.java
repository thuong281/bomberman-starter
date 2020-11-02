package uet.oop.bomberman.gamecontroller;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.BomberSprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
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
            if (Sandbox.getBomber().getAnimation() == BomberSprite.walkDown) bomber.move(0, Direction.DOWN);
            if (Sandbox.getBomber().getAnimation() == BomberSprite.walkUp) bomber.move(0, Direction.UP);
            if (Sandbox.getBomber().getAnimation() == BomberSprite.walkLeft) bomber.move(0, Direction.LEFT);
            if (Sandbox.getBomber().getAnimation() == BomberSprite.walkRight) bomber.move(0, Direction.RIGHT);
        }
    }
}
