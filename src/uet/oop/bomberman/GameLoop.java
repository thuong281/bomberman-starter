package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import static uet.oop.bomberman.scenes.Sandbox.*;


public class GameLoop {
    public static void start(GraphicsContext gc) {
        new AnimationTimer() {
            public void handle(long l) {
                gc.clearRect(0, 0, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
                //TODO This will have to be something like, currentScene.getEntities()
                updateGame();
                renderGame();
            }
        }.start();
    }
    public static void updateGame() {
        EventHandler.playerMovement();
        entities.forEach(Entity::update);
    }

    public static void renderGame() {
        getBomber().tick();
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
