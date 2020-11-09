package uet.oop.bomberman.entities;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;
import uet.oop.bomberman.entities.powerup.AddBomb;
import uet.oop.bomberman.entities.powerup.Flame;
import uet.oop.bomberman.entities.powerup.Speed;
import uet.oop.bomberman.graphics.Animation.BomberManAnimation;

import static uet.oop.bomberman.graphics.Animation.BomberManAnimation.*;

import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
import uet.oop.bomberman.scenes.Sandbox;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {

    Direction currentDirection;
    public int bombCount = 10;

    int explodeLength = 1;

    boolean isAlive = true;

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    private int step = 1;
    RectBoundedBox playerBoundary;

    public int getExplodeLength() {
        return explodeLength;
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        playerBoundary = new RectBoundedBox(x, y, (int) (Sprite.DEFAULT_SIZE * 1.5), (int) (Sprite.DEFAULT_SIZE * 1.5));
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
        if (isAlive) {
            BomberManAnimation.animation.update();
        }
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
        for (Entity e : Sandbox.getBomb()) {
            if (isColliding(e) && !e.isPlayerCollisionFriendly()) {
                playerBoundary.setPosition(x, y);
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
                    getPowerUp();
                    y -= getStep();
                    animation = walkUp;
                    currentDirection = Direction.UP;
                    animation.start();
                }
                break;
            case DOWN:
                if (!checkCollisions(x, y + step)) {
                    getPowerUp();
                    y += getStep();
                    animation = walkDown;
                    currentDirection = Direction.DOWN;
                    animation.start();
                }
                break;
            case LEFT:
                if (!checkCollisions(x - step, y)) {
                    getPowerUp();
                    x -= getStep();
                    animation = walkLeft;
                    currentDirection = Direction.LEFT;
                    animation.start();
                }
                break;
            case RIGHT:
                if (!checkCollisions(x + step, y)) {
                    getPowerUp();
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

    public void getPowerUp() {
        if (Sandbox.powerUps.size() > 0) {
            List<Entity> tmp = new ArrayList<>(Sandbox.powerUps);

            for (int i = 0; i < tmp.size(); i++) {
                if (isColliding(tmp.get(i)) && tmp.get(i) instanceof Speed) {
                    Sandbox.powerUps.remove(Sandbox.powerUps.get(i));
                    setStep(getStep() + 1);
                    System.out.println("speed up");
                }
                if (isColliding(tmp.get(i)) && tmp.get(i) instanceof Flame) {
                    Sandbox.powerUps.remove(Sandbox.powerUps.get(i));
                    Sandbox.getBomber().explodeLength++;
                    System.out.println("longer explosion");
                }
                if (isColliding(tmp.get(i)) && tmp.get(i) instanceof AddBomb) {
                    Sandbox.powerUps.remove(Sandbox.powerUps.get(i));
                    bombCount++;
                    System.out.println("add bomb");
                }
            }
        }
    }

    public SpriteAnimation getAnimation() {
        return animation;
    }

    public boolean hasMoreBombs() {
        return bombCount > 0;
    }

    public void incrementBombCount() {
        ++bombCount;
    }

    public void decrementBombCount() {
        --bombCount;
    }

    public void startAnimation() {
        animation.start();
    }

    public void stopAnimation() {
        animation.stop();
    }

    public void setAnimation(SpriteAnimation animation) {
        BomberManAnimation.animation = animation;
    }

    public boolean isDead() {
        for (Entity e : Sandbox.getExplodingBomb()) {
            if (isColliding(e)) {
                return true;
            }
        }
        for (Entity e : Sandbox.getEnemies()) {
            if (isColliding(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWinner() {
        for (Entity e : Sandbox.getGates()) {
            if (isColliding(e)) {
                return true;
            }
        }
        return false;
    }
}

