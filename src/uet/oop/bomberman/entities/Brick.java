package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
}