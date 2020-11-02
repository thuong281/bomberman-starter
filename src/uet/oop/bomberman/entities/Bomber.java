package uet.oop.bomberman.entities;


import javafx.animation.Animation;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.MapInfo;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.BomberSprite;

import static uet.oop.bomberman.graphics.BomberSprite.*;

import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
import uet.oop.bomberman.scenes.Sandbox;

import java.awt.image.BufferedImage;
import java.util.Map;

import static uet.oop.bomberman.MapInfo.getMapLines;

public class Bomber extends Entity {

    Direction currentDirection;

    public int getStep() {
        return step;
    }

    private int step = 2;
    RectBoundedBox playerBoundary;

    public void tick() {
        BomberSprite.animation.update();
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        playerBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE - 8, Sprite.SCALED_SIZE);
    }

    @Override
    public boolean isColliding(Entity b) {
        RectBoundedBox otherEntityBoundary = b.getBoundingBox();
        return playerBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public RectBoundedBox getBoundingBox() {
        playerBoundary.setPosition(x, y);
        return playerBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(animation.getSprite(), x, y);
    }

    private boolean checkCollisions(int positionX, int positionY) {
        playerBoundary.setPosition(positionX, positionY);

        for (Entity e : Sandbox.getStillObjects()) {
            if (isColliding(e) && !e.isPlayerCollisionFriendly()) {
                playerBoundary.setPosition(x, y);
                System.out.println("Player x=" + getX() + " y="
                        + getY() + " colliding with x=" + e.getX()
                        + " y=" + e.getY());
                return true;
            }
        }
        playerBoundary.setPosition(x, y);
        return false;
    }

    public void move(int steps, Direction direction) {
        animation.reset();
        switch (direction) {
            case UP:
                animation = walkUp;
                break;
            case DOWN:
                animation = standDown;
                break;
            case LEFT:
                animation = standLeft;
                break;
            case RIGHT:
                animation = standRight;
                break;
        }
    }

    public void move(Direction direction) {

        //if(steps!=0){System.out.println("Steps before="+steps+" now="+steps * GameLoop.getDeltaTime());}
        //steps *= GameLoop.getDeltaTime();
        switch (direction) {
            case UP:
                if (!checkCollisions(x, y - step)) {
                    y -= getStep();
                    animation = walkUp;
                    currentDirection = Direction.UP;
                    animation.start();
                }
                break;
            case DOWN:
                if (!checkCollisions(x, y + step)) {
                    y += getStep();
                    animation = walkDown;
                    currentDirection = Direction.DOWN;
                    animation.start();
                }
                break;
            case LEFT:
                if (!checkCollisions(x - step, y)) {
                    x -= getStep();
                    animation = walkLeft;
                    currentDirection = Direction.LEFT;
                    animation.start();
                }
                break;
            case RIGHT:
                if (!checkCollisions(x + step, y)) {
                    x += getStep();
                    animation = walkRight;
                    currentDirection = Direction.RIGHT;
                    animation.start();
                }
                break;
            default:
                animation = standRight;
                animation.reset();

        }
    }

    public SpriteAnimation getAnimation() {
        return animation;
    }
}

