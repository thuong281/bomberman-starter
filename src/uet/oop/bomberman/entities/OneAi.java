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
    boolean isAlive = true;

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    Direction direction = Direction.RIGHT;

    public final Image[] movingRight = {Sprite.oneal_right1.getFxImage(), Sprite.oneal_right2.getFxImage(), Sprite.oneal_right3.getFxImage()};
    public final SpriteAnimation moveRight = new SpriteAnimation(movingRight, 10);

    public final Image[] movingLeft = {Sprite.oneal_left1.getFxImage(), Sprite.oneal_left2.getFxImage(), Sprite.oneal_left3.getFxImage()};
    public final SpriteAnimation moveLeft = new SpriteAnimation(movingLeft, 10);

    public final Image[] dying = {Sprite.oneal_dead.getFxImage(), Sprite.mob_dead1.getFxImage(), Sprite.mob_dead2.getFxImage(), Sprite.mob_dead3.getFxImage()};
    public final SpriteAnimation die = new SpriteAnimation(dying, 40);

    SpriteAnimation animation = moveLeft;

    public void setAnimation(SpriteAnimation animation) {
        this.animation = animation;
    }

    public OneAi(int x, int y, Image img) {
        super(x, y, img);
        entityBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE - 2, Sprite.SCALED_SIZE - 2);
        animation.start();
    }

    public boolean collideLeft() {
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

    public boolean collideRight() {
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
        if (isAlive) {
            if (collideRight()) direction = Direction.LEFT;
            if (collideLeft()) direction = Direction.RIGHT;
            switch (direction) {
                case RIGHT:
                    y -= step;
                    animation = moveRight;
                    animation.start();
                    break;
                case LEFT:
                    y += step;
                    animation = moveLeft;
                    animation.start();
                    break;
            }
        }
    }

    @Override
    public boolean isColliding(Entity b) {
        RectBoundedBox otherEntityBoundary = b.getBoundingBox();
        return entityBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public RectBoundedBox getBoundingBox() {
        entityBoundary.setPosition(x, y);
        return entityBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(animation.getSprite(), x, y);
    }

    public void startAnimation() {
        animation.start();
    }


}