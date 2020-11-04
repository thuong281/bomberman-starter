package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;


import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
import uet.oop.bomberman.scenes.Sandbox;

import java.util.Date;

public class Bomb extends Entity {

    boolean friendly =true;

    int timerDurationInMillis = 3500;
    RectBoundedBox entityBoundary;
    public final Image[] puttingBomb = {Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb_2.getFxImage()};
    public final SpriteAnimation putBomb = new SpriteAnimation(puttingBomb, 5);

    public SpriteAnimation getPutBomb() {
        return putBomb;
    }

    enum STATE {
        INACTIVE,   //INACTIVE when bomb's timer hasnt yet started
        ACTIVE,     //Active when bomb's timer has started and it will explode soon
        EXPLODING,  //when bomb is exploding
        DEAD;   //when the bomb has already exploded
    }

    Date addedDate;
    STATE bombState;
    public void setFriendly (boolean b) {
        friendly = b;
    }

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        entityBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        addedDate = new Date();
        bombState = STATE.ACTIVE;
    }

    public boolean isAlive() {
        STATE s = checkBombState();
        if (s == STATE.DEAD) {
            return false;
        } else {
            if (s == STATE.ACTIVE || s == STATE.INACTIVE) {
                return true;
            }
            return true;
        }
    }

    public STATE checkBombState() {
        if (new Date().getTime() > timerDurationInMillis + addedDate.getTime()) {
            return STATE.DEAD;
        } else {
            return STATE.ACTIVE;
        }
    }

    public void changeFriendlyState() {
        if (!isColliding(Sandbox.getBomber()))
            setFriendly(false);
    }

    @Override
    public void update() {
        putBomb.update();

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
        return friendly;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(putBomb.getSprite(), x, y);
    }
}
