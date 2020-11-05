package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;


import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;
import uet.oop.bomberman.scenes.Sandbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExplodeBomb extends Entity {

    boolean friendly = true;

    List<ExplodeBomb> spanExplosionHorizontal = new ArrayList<>();
    List<ExplodeBomb> spanExplosionVertical = new ArrayList<>();

    int timerDurationInMillis = 1000;
    RectBoundedBox entityBoundary;


    public final Image[] explodingBomb = {Sprite.bomb_exploded.getFxImage(), Sprite.bomb_exploded1.getFxImage(), Sprite.bomb_exploded2.getFxImage()};
    public final SpriteAnimation explodeBomb = new SpriteAnimation(explodingBomb, 5);

    public final Image[] explodingHorizontal = {Sprite.explosion_horizontal.getFxImage(), Sprite.explosion_horizontal.getFxImage(), Sprite.explosion_horizontal.getFxImage()};
    public final SpriteAnimation explodeHorizontal = new SpriteAnimation(explodingHorizontal, 5);

    public final Image[] explodingVertical = {Sprite.explosion_vertical.getFxImage(), Sprite.explosion_vertical.getFxImage(), Sprite.explosion_vertical.getFxImage()};
    public final SpriteAnimation explodeVertical = new SpriteAnimation(explodingVertical, 5);

//TODO make flame after explosion
//   public void initExplosionFlame() {
//        for (int i = -explodeLength; i <= explodeLength; i++) {
//            if (i == 0) continue;
//            ExplodeBomb newExplosion1 = new ExplodeBomb((x + i) * Sprite.SCALED_SIZE, (y + i) * Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage());
//            spanExplosionHorizontal.add(newExplosion1);
//            ExplodeBomb newExplosion2 = new ExplodeBomb(x + i, y + i, Sprite.explosion_vertical.getFxImage());
//            spanExplosionVertical.add(newExplosion1);
//        }
//    }

    public SpriteAnimation getExplodeBomb() {
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

    public ExplodeBomb(int x, int y, Image img) {
        super(x, y, img);
        entityBoundary = new RectBoundedBox(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        addedDate = new Date();
        bombState = STATE.ACTIVE;
//        initExplosionFlame();
    }

    public boolean isExploding() {
        STATE s = checkBombState();
        return s != STATE.EXPLODING;
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
        explodeBomb.update();
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
        gc.drawImage(explodeBomb.getSprite(), x, y);
    }
}
