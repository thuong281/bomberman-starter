package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scenes.Sandbox;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import static uet.oop.bomberman.scenes.Sandbox.*;


public class GameLoop {
    public static void start(GraphicsContext gc) {
        new AnimationTimer() {
            public void handle(long l) {
                gc.clearRect(0, 0, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
                s.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.D) {
                        Bomber tmpBomber = (Bomber) Sandbox.getBomber();
                        tmpBomber.goRight();
                        System.out.println("D key was pressed");
                    }
                    if (e.getCode() == KeyCode.A) {
                        Bomber tmpBomber = (Bomber) Sandbox.getBomber();
                        tmpBomber.goLeft();
                        System.out.println("A key was pressed");
                    }if (e.getCode() == KeyCode.W) {
                        Bomber tmpBomber = (Bomber) Sandbox.getBomber();
                        tmpBomber.goUp();
                        System.out.println("W key was pressed");
                    }if (e.getCode() == KeyCode.S) {
                        Bomber tmpBomber = (Bomber) Sandbox.getBomber();
                        tmpBomber.goDown();
                        System.out.println("S key was pressed");
                    }
                });
                //TODO This will have to be something like, currentScene.getEntities()
                updateGame();
                renderGame();
            }
        }.start();
    }
    public static void updateGame() {

        entities.forEach(Entity::update);
    }

    public static void renderGame() {
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
