package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import uet.oop.bomberman.constants.Dimension;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;

import uet.oop.bomberman.entities.explodebomb.ExplodeBomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
import uet.oop.bomberman.scenes.Sandbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bomb extends Entity {

    boolean friendly = true;

    int timerDurationInMillis = 1500;
    RectBoundedBox entityBoundary;

    ExplodeBomb explodeBomb;
    List<ExplodeBomb> explodeRange = new ArrayList<>();

    public List<ExplodeBomb> getExplodeRange() {
        return explodeRange;
    }

    int explodeLength = Sandbox.getBomber().getExplodeLength();

    public final Image[] puttingBomb = {Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb_2.getFxImage()};
    public final SpriteAnimation putBomb = new SpriteAnimation(puttingBomb, 7);


    public SpriteAnimation getPutBomb() {
        return putBomb;
    }

    public ExplodeBomb getExplodeBomb() {
        return explodeBomb;
    }

    enum STATE {
        INACTIVE,   //INACTIVE when bomb's timer hasnt yet started
        ACTIVE,     //Active when bomb's timer has started and it will explode soon
        EXPLODING,  //when bomb is exploding
        DEAD;   //when the bomb has already exploded
    }

    Date addedDate;
    STATE bombState;

    public void setFriendly(boolean b) {
        friendly = b;
    }

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        entityBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        ExplodeBomb newExplode = new ExplodeBomb(x, y, Sprite.bomb_exploded.getFxImage(), Dimension.CENTER);
        explodeRange.add(newExplode);
        setExplodeBombTop();
        setExplodeBombDown();
        setExplodeBombLeft();
        setExplodeBombRight();

        addedDate = new Date();
        bombState = STATE.ACTIVE;
    }

    public void setExplodeBombTop() {
        for (int i = 1; i <= explodeLength; i++) {
            ExplodeBomb newExplodeVertical = new ExplodeBomb(x, y - i * Sprite.SCALED_SIZE,
                    Sprite.bomb_exploded.getFxImage(), Dimension.VERTICAL);
            if (newExplodeVertical.isCollideWithWalls()) {
                break;
            } else if (newExplodeVertical.isCollideWithBricks()) {
                explodeRange.add(newExplodeVertical);
                break;
            } else {
                explodeRange.add(newExplodeVertical);
            }
        }
    }

    public void setExplodeBombDown() {
        for (int i = 1; i <= explodeLength; i++) {
            ExplodeBomb newExplodeVertical = new ExplodeBomb(x, y + i * Sprite.SCALED_SIZE,
                    Sprite.bomb_exploded.getFxImage(), Dimension.VERTICAL);
            if (newExplodeVertical.isCollideWithWalls()) {
                break;
            } else if (newExplodeVertical.isCollideWithBricks()) {
                explodeRange.add(newExplodeVertical);
                break;
            } else {
                explodeRange.add(newExplodeVertical);
            }
        }
    }


    public void setExplodeBombLeft() {
        for (int i = 1; i <= explodeLength; i++) {
            ExplodeBomb newExplodeHorizontal = new ExplodeBomb(x - i * Sprite.SCALED_SIZE, y,
                    Sprite.bomb_exploded.getFxImage(), Dimension.HORIZONTAL);
            if (newExplodeHorizontal.isCollideWithWalls()) {
                break;
            } else if (newExplodeHorizontal.isCollideWithBricks()) {
                explodeRange.add(newExplodeHorizontal);
                break;
            } else {
                explodeRange.add(newExplodeHorizontal);
            }
        }
    }

    public void setExplodeBombRight() {
        for (int i = 1; i <= explodeLength; i++) {
            ExplodeBomb newExplodeHorizontal = new ExplodeBomb(x + i * Sprite.SCALED_SIZE, y,
                    Sprite.bomb_exploded.getFxImage(), Dimension.HORIZONTAL);
            if (newExplodeHorizontal.isCollideWithWalls()) {
                break;
            } else if (newExplodeHorizontal.isCollideWithBricks()) {
                explodeRange.add(newExplodeHorizontal);
                break;
            } else {
                explodeRange.add(newExplodeHorizontal);
            }
        }
    }

    public boolean isAlive() {
        STATE s = checkBombState();
        if (s == STATE.EXPLODING) {
            return false;
        } else {
            return true;
        }
    }

    public STATE checkBombState() {
        if (new Date().getTime() > timerDurationInMillis + addedDate.getTime()) {
            return STATE.EXPLODING;
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

    public boolean isOnOtherBomb() {
        for (Entity e : Sandbox.getBomb()) {
            if (e != this && isColliding(e)) return true;
        }
        return false;
    }

}
