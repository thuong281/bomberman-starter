package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {
    RectBoundedBox entityBoundary;

    public Grass(int x, int y, Image img) {
        super(x, y, img);
        entityBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isColliding(Entity b) {
        RectBoundedBox otherEntityBoundary = (RectBoundedBox) b.getBoundingBox();
        return entityBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public RectBoundedBox getBoundingBox() {
        entityBoundary.setPosition(x, y);
        return entityBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return true;
    }
}
