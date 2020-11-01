package uet.oop.bomberman.entities;


import javafx.animation.Animation;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.MapInfo;
import uet.oop.bomberman.graphics.BomberSprite;
import static uet.oop.bomberman.graphics.BomberSprite.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;

import java.awt.image.BufferedImage;
import java.util.Map;

import static uet.oop.bomberman.MapInfo.getMapLines;

public class Bomber extends Entity {

    private int velX = 0;
    private int velY = 0;

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public void tick() {
        BomberSprite.animation.update();
        x += velX;
        y += velY;
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(animation.getSprite(), x, y);
    }

}
