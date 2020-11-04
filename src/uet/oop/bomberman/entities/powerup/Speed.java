package uet.oop.bomberman.entities.powerup;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;

public class Speed extends Entity {

    public Speed(int x, int y, Image img) {
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
