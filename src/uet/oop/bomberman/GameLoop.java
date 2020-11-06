package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.explodebomb.ExplodeBomb;
import uet.oop.bomberman.gamecontroller.InputManager;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scenes.Sandbox;


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
        explodingBomb.forEach(Entity::update);
        enemies.forEach(Entity::update);
        if (Sandbox.bomb.size() > 0) {
            for (int i = 0; i < Sandbox.bomb.size(); i++) {
                Bomb tmpBomb = (Bomb) Sandbox.bomb.get(i);
                tmpBomb.changeFriendlyState();
                if (!tmpBomb.isAlive()) {
                    for (ExplodeBomb explodeBomb : tmpBomb.getExplodeRange()) {
                        Sandbox.addExplodeBombToGame(explodeBomb);
                        explodeBomb.getExplodingBomb().start();
                    }
                    Sandbox.bomb.remove(i);
                }
            }
        }
        if (explodingBomb.size() > 0) {
            for (int i = 0; i < explodingBomb.size(); i++) {
                ExplodeBomb tmpExplodeBomb = (ExplodeBomb) explodingBomb.get(i);
                if (!tmpExplodeBomb.isExploding()) {
                    explodingBomb.remove(i);
                    Sandbox.getBomber().incrementBombCount();
                }
            }
        }
    }

    public static void renderGame() {
        stillObjects.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        bomb.forEach(g -> g.render(gc));
        explodingBomb.forEach(g -> g.render(gc));
        powerUps.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

