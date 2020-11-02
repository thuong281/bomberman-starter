package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;

public class Balloom extends Entity {

    public Balloom(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isColliding(Entity b) {
        return false;
    }

    @Override
    public RectBoundedBox getBoundingBox() {
        return null;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return false;
    }
}