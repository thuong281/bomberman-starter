package uet.oop.bomberman.scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static uet.oop.bomberman.MapInfo.*;
import static uet.oop.bomberman.MapInfo.getMapLines;

public class Sandbox {

    public static final int WIDTH = getCols();
    public static final int HEIGHT = getRows();
    public static Scene s;

    static Group root;
    static Canvas c;
    public static GraphicsContext gc;
    static Bomber SandboxBomber= new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static List<Entity> entities = new ArrayList<>();

    public static List<Entity> getEntities(){
        return entities;
    }

    public static List<Entity> stillObjects = new ArrayList<>();

    public static void addEntityToGame(Entity e){
        entities.add(e);
    }

    private static void init() {

        entities.add(getBomber());
        root = new Group();
        c = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        s = new Scene(root);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        GameLoop.start(gc);

        for(int i=0; i<getMapLines()[0].length(); i++) {
            for(int j=0; j<getMapLines().length; j++) {
                Entity object;
                if(Character.toString(getMapLines()[j].charAt(i)).equals("#")) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else if(Character.toString(getMapLines()[j].charAt(i)).equals("*")) {
                    object = new Wall(i, j, Sprite.brick.getFxImage());
                }
                else if(Character.toString(getMapLines()[j].charAt(i)).equals("1")) {
                    object = new Wall(i, j, Sprite.balloom_left1.getFxImage());
                }
                else if(Character.toString(getMapLines()[j].charAt(i)).equals("2")) {
                    object = new Wall(i, j, Sprite.oneal_right1.getFxImage());
                }
                else if(Character.toString(getMapLines()[j].charAt(i)).equals("b")) {
                    object = new Wall(i, j, Sprite.powerup_bombs.getFxImage());
                }
                else if(Character.toString(getMapLines()[j].charAt(i)).equals("f")) {
                    object = new Wall(i, j, Sprite.powerup_flames.getFxImage());
                }
                else if(Character.toString(getMapLines()[j].charAt(i)).equals("s")) {
                    object = new Wall(i, j, Sprite.powerup_speed.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }

    }

    public static void setupScene(){
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

    public static void setBomber(Bomber bomber){
        SandboxBomber = bomber;
        addEntityToGame(SandboxBomber);
    }

    public static Bomber getBomber(){
        return SandboxBomber;
    }

}
