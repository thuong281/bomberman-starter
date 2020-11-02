package uet.oop.bomberman.entities;


import javafx.animation.Animation;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.MapInfo;
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

    private int velX = 0;
    private int velY = 0;
    private static int step = 2;
    RectBoundedBox playerBoundary;


    public static int getStep() {
        return step;
    }

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
        playerBoundary = new RectBoundedBox(x, y, Sprite.DEFAULT_SIZE, Sprite.DEFAULT_SIZE);
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

    public boolean checkCollisions(int newX, int newY) {
        playerBoundary.setPosition(newX, newY);
        if (x>=100) {
                return true;
            }

        playerBoundary.setPosition(x, y);
        return false;
    }



}
