package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
import uet.oop.bomberman.scenes.Sandbox;

public class Balloom extends Entity {

    RectBoundedBox entityBoundary;
    int step = 1;
    Direction direction = Direction.RIGHT;

    public final Image[] movingLeft = {Sprite.balloom_left1.getFxImage(), Sprite.balloom_left2.getFxImage(), Sprite.balloom_left3.getFxImage()};
    public final SpriteAnimation moveLeft = new SpriteAnimation(movingLeft, 10);

    public final Image[] movingRight = {Sprite.balloom_right1.getFxImage(), Sprite.balloom_right2.getFxImage(), Sprite.balloom_right3.getFxImage()};
    public final SpriteAnimation moveRight = new SpriteAnimation(movingRight, 10);

    SpriteAnimation animation = moveLeft;


    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        entityBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE - 2, Sprite.SCALED_SIZE - 2);
        animation.start();
    }

    public boolean collideRight() {
        int tmpX = x + step;
        entityBoundary.setPosition(tmpX, y);
        for (Entity e : Sandbox.getStillObjects()) {
            if (isColliding(e) && !e.isPlayerCollisionFriendly()) {
                entityBoundary.setPosition(x, y);
                return true;
            }
        }
        entityBoundary.setPosition(x, y);
        return false;
    }

    public boolean collideLeft() {
        int tmpX = x - step;
        entityBoundary.setPosition(tmpX, y);
        for (Entity e : Sandbox.getStillObjects()) {
            if (isColliding(e) && !e.isPlayerCollisionFriendly()) {
                entityBoundary.setPosition(x, y);
                return true;
            }
        }
        entityBoundary.setPosition(x, y);
        return false;
    }


    @Override
    public void update() {
        animation.update();
        if (collideLeft()) direction = Direction.RIGHT;
        if (collideRight()) direction = Direction.LEFT;
        switch (direction) {
            case LEFT:
                x -= step;
                animation = moveLeft;
                break;
            case RIGHT:
                x += step;
                animation = moveRight;
                break;
        }
    }

    @Override
    public boolean isColliding(Entity b) {
        RectBoundedBox otherEntityBoundary = b.getBoundingBox();
        return entityBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public RectBoundedBox getBoundingBox() {
        return null;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(animation.getSprite(), x, y);
    }
}