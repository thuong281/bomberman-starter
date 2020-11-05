package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.gamecontroller.InputManager;
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

                renderGame();
                updateGame();
            }
        }.start();
    }

    public static void updateGame() {
        InputManager.handlePlayerMovements();
        entities.forEach(Entity::update);
        bomb.forEach(Entity::update);
        explodeBomb.forEach(Entity::update);
        enemies.forEach(Entity::update);
        if (Sandbox.bomb.size() > 0) {
            for (int i = 0; i < Sandbox.bomb.size(); i++) {
                Bomb tmpBomb = (Bomb) Sandbox.bomb.get(i);
                tmpBomb.changeFriendlyState();
                if (!tmpBomb.isAlive()) {
                    Sandbox.bomb.remove(i);
                    ExplodeBomb newBomb = new ExplodeBomb(tmpBomb.getX(), tmpBomb.getY(), Sprite.bomb_exploded.getFxImage());
                    Sandbox.addExplodeBombToGame(newBomb);
                    newBomb.getExplodeBomb().start();
                }
            }
        }
        if (explodeBomb.size() > 0) {
            for (int i = 0; i < explodeBomb.size(); i++) {
                ExplodeBomb tmpExplodeBomb = (ExplodeBomb) explodeBomb.get(i);
                if (!tmpExplodeBomb.isExploding()) {
                    explodeBomb.remove(i);
                    Sandbox.getBomber().incrementBombCount();
                }
            }
        }
    }

    public static void renderGame() {
        stillObjects.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        bomb.forEach(g -> g.render(gc));
        explodeBomb.forEach(g -> g.render(gc));
        powerUps.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

