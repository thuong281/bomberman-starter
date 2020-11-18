package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.explodebomb.ExplodeBomb;
import uet.oop.bomberman.gamecontroller.EventHandler;
import uet.oop.bomberman.gamecontroller.InputManager;
import uet.oop.bomberman.graphics.Animation.BomberManAnimation;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scenes.Sandbox;

import uet.oop.bomberman.sound.Sounds;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import static uet.oop.bomberman.scenes.Sandbox.*;



public class GameLoop {
    public static Sounds playgame;
    public static void start(GraphicsContext gc) {
        playgame = new Sounds(new File("res/sounds/playgame.wav"));
        playgame.play();
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
        if (Sandbox.getBomber().isWinner()) {

            System.out.println("win");
            EventHandler.getInputList().clear();
            EventHandler.removeEventHandlers(s);
            Image image = Sprite.winner;

            gc.drawImage(image, 300, 100);
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            }, 3000);

        }

        if (Sandbox.getBomber().isDead()) {
            System.out.println("Game Over !!!!");
            Sounds.getIstance(Sounds.bomber_die).play();
            playgame.stop();
            EventHandler.getInputList().clear();
            Sandbox.getBomber().setAnimation(BomberManAnimation.die);
            Sandbox.getBomber().startAnimation();
            EventHandler.removeEventHandlers(s);
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    Sandbox.getBomber().setAlive(false);
                    entities.remove(Sandbox.getBomber());
                    Platform.exit();
                }
            }, 1550);
        }


        Sandbox.getBricks().removeIf(Entity::isCollideWithFlame);
        Sandbox.getStillObjects().removeIf(Entity::isCollideWithFlame);
        for (Entity e : Sandbox.getEnemies()) {
            if (e.isCollideWithFlame()) {

                if (e instanceof OneAi) {
                    Sounds.getIstance(Sounds.monstersd).play();
                    ((OneAi) e).setAlive(false);
                    ((OneAi) e).setAnimation(((OneAi) e).die);
                    ((OneAi) e).startAnimation();
                    Timer time = new Timer();
                    time.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Sandbox.getEnemies().remove(e);
                        }
                    }, 2000);
                }

                if (e instanceof Balloom) {
                    Sounds.getIstance(Sounds.monstersd).play();
                    ((Balloom) e).setAlive(false);
                    ((Balloom) e).setAnimation(((Balloom) e).die);
                    ((Balloom) e).startAnimation();
                    Timer time = new Timer();
                    time.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Sandbox.getEnemies().remove(e);
                        }
                    }, 2000);
                }

            }
        }
        if (Sandbox.bomb.size() > 0) {
            for (int i = 0; i < Sandbox.bomb.size(); i++) {
                Bomb tmpBomb = (Bomb) Sandbox.bomb.get(i);
                tmpBomb.changeFriendlyState();
                if (!tmpBomb.isAlive() || tmpBomb.isCollideWithFlame()) {
                    for (ExplodeBomb explodeBomb : tmpBomb.getExplodeRange()) {
                        Sandbox.addExplodeBombToGame(explodeBomb);
                        explodeBomb.getExplodingBomb().start();
                    }

                    Sandbox.bomb.remove(i);
                }
            }
        }
        if (explodingBomb.size() > 0) {
            Sounds.getIstance(Sounds.bomb_bang).play();
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
        gates.forEach(g -> g.render(gc));
        bricks.forEach(g -> g.render(gc));
        walls.forEach(g -> g.render(gc));
        explodingBomb.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        bomb.forEach(g -> g.render(gc));
        powerUps.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

