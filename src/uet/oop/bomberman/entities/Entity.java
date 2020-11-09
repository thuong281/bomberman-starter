package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scenes.Sandbox;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Entity(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        gc.drawImage(img, x, y);
    }

    public abstract void update();
    public abstract boolean isColliding(Entity b);
    public abstract RectBoundedBox getBoundingBox();
    public abstract boolean isPlayerCollisionFriendly();

    public boolean isCollideWithFlame() {
        for (Entity e : Sandbox.getExplodingBomb()) {
            if (isColliding(e)) return true;
        }
        return false;
    }

    public boolean isCollideWithWalls() {
        for (Entity e : Sandbox.getWalls()) {
            if (isColliding(e)) return true;
        }
        return false;
    }

    public boolean isCollideWithBricks() {
        for (Entity e : Sandbox.getBricks()) {
            if (isColliding(e)) return true;
        }
        return false;
    }


}
