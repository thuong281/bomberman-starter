package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.MapInfo.*;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
//        for (int i = 0; i < getCols(); i++) {
//            for (int j = 0; j < getRows(); j++) {
//                Entity object;
//                if (j == 0 || j == getRows() - 1 || i == 0 || i == getCols() - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//
//                }
//                stillObjects.add(object);
//            }
//        }

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

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
