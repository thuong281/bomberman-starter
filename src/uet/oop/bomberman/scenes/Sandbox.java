package uet.oop.bomberman.scenes;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.powerup.AddBomb;
import uet.oop.bomberman.entities.powerup.Flame;
import uet.oop.bomberman.entities.powerup.Speed;
import uet.oop.bomberman.gamecontroller.EventHandler;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.MapInfo.*;
import static uet.oop.bomberman.MapInfo.getMapLines;

public class Sandbox {

    public static final int WIDTH = getCols();
    public static final int HEIGHT = getRows();
    public static Scene s;

    static Group root;
    static Canvas c;
    public static GraphicsContext gc;
    static Bomber SandboxBomber;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> bomb = new ArrayList<>();
    public static List<Entity> powerUps = new ArrayList<>();
    public static List<Entity> explodingBomb = new ArrayList<>();

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

//    public static int count() {
//        return bomb.size();
//    }

    public static void addEntityToGame(Entity e) {
        entities.add(e);
    }

    public static void addEnemies(Entity e) {
        enemies.add(e);
    }

    public static void addPowerUps(Entity e) {
        powerUps.add(e);
    }

    public static void addBombToGame(Entity e) {
        bomb.add(e);
    }

    public static void addExplodeBombToGame(Entity e) {
        explodingBomb.add(e);
    }

    private static void init() {


        root = new Group();
        c = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        s = new Scene(root);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        for (int i = 0; i < getMapLines()[0].length(); i++) {
            for (int j = 0; j < getMapLines().length; j++) {
                Entity object;
                Entity backObject;
                if (Character.toString(getMapLines()[j].charAt(i)).equals("#")) {
                    object = new Wall(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.wall.getFxImage());
                } else if (Character.toString(getMapLines()[j].charAt(i)).equals("*")) {
                    object = new Brick(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.brick.getFxImage());
                } else if (Character.toString(getMapLines()[j].charAt(i)).equals("1")) {
                    backObject = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                    stillObjects.add(backObject);
                    object = new Balloom(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage());
                    addEnemies(object);
                    continue;
                } else if (Character.toString(getMapLines()[j].charAt(i)).equals("2")) {
                    backObject = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                    stillObjects.add(backObject);
                    object = new OneAi(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage());
                    addEnemies(object);
                    continue;
                } else if (Character.toString(getMapLines()[j].charAt(i)).equals("b")) {
                    backObject = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                    stillObjects.add(backObject);
                    object = new AddBomb(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.powerup_bombs.getFxImage());
                    addPowerUps(object);
                    continue;
                } else if (Character.toString(getMapLines()[j].charAt(i)).equals("f")) {
                    backObject = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                    stillObjects.add(backObject);
                    object = new Flame(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.powerup_flames.getFxImage());
                    addPowerUps(object);
                    continue;
                } else if (Character.toString(getMapLines()[j].charAt(i)).equals("s")) {
                    backObject = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                    stillObjects.add(backObject);
                    object = new Speed(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.powerup_speed.getFxImage());
                    addPowerUps(object);
                    continue;
                } else if (Character.toString(getMapLines()[j].charAt(i)).equals("p")) {
                    backObject = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                    object = new Bomber(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.player_right.getFxImagePlayer());
                    SandboxBomber = (Bomber) object;
                    stillObjects.add(backObject);
                    setBomber(getBomber());
                    continue;
                } else {
                    object = new Grass(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }

        GameLoop.start(gc);
        EventHandler.attachEventHandlers(s);

    }

    public static void setupScene() {
        init();

    }

    public static Scene getScene() {
        return s;
    }

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    public static Canvas getCanvas() {
        return c;
    }

    public static void setBomber(Bomber bomber) {
        SandboxBomber = bomber;
        addEntityToGame(SandboxBomber);
    }

    public static Bomber getBomber() {
        return SandboxBomber;
    }

    public static List<Entity> getBomb() {
        return bomb;
    }

}
