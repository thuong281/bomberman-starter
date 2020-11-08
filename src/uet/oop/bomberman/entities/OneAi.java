package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
import uet.oop.bomberman.scenes.Sandbox;


public class OneAi extends Entity {

    RectBoundedBox entityBoundary;
    int step = 1;
    Direction direction = Direction.UP;

    public final Image[] movingUp = {Sprite.oneal_right1.getFxImage(), Sprite.oneal_right2.getFxImage(), Sprite.oneal_right3.getFxImage()};
    public final SpriteAnimation moveUp = new SpriteAnimation(movingUp, 10);

    public final Image[] movingDown = {Sprite.oneal_left1.getFxImage(), Sprite.oneal_left1.getFxImage(), Sprite.oneal_left1.getFxImage()};
    public final SpriteAnimation moveDown = new SpriteAnimation(movingDown, 10);

    SpriteAnimation animation = moveDown;


    public OneAi(int x, int y, Image img) {
        super(x, y, img);
        entityBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE - 2, Sprite.SCALED_SIZE - 2);
        animation.start();
    }

    public boolean collideDown() {
        int tmpY = y + step;
        entityBoundary.setPosition(x, tmpY);
        for (Entity e : Sandbox.getStillObjects()) {
            if (isColliding(e) && !e.isPlayerCollisionFriendly()) {
                entityBoundary.setPosition(x, y);
                return true;
            }
        }
        for (Entity e : Sandbox.getBomb()) {
            if (isColliding(e)) {
                entityBoundary.setPosition(x, y);
                return true;
            }
        }
        entityBoundary.setPosition(x, y);
        return false;
    }

    public boolean collideUp() {
        int tmpY = y - step;
        entityBoundary.setPosition(x, tmpY);
        for (Entity e : Sandbox.getStillObjects()) {
            if (isColliding(e) && !e.isPlayerCollisionFriendly()) {
                entityBoundary.setPosition(x, y);
                return true;
            }
        }
        for (Entity e : Sandbox.getBomb()) {
            if (isColliding(e)) {
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
        if (collideUp()) direction = Direction.DOWN;
        if (collideDown()) direction = Direction.UP;
        switch (direction) {
            case UP:
                y -= step;
                animation = moveUp;
                animation.start();
                break;
            case DOWN:
                y += step;
                animation = moveDown;
                animation.start();
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