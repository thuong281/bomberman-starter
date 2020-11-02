package uet.oop.bomberman.entities.boundedbox;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.DEFAULT_SIZE;

public class RectBoundedBox {
    int x;
    int y;
    int width;
    int height;
    Rectangle2D boundary;

    public RectBoundedBox(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        boundary = new Rectangle2D(x, y, width, height);
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }

    public void setBoundary(Rectangle2D boundaryRect) {
        boundary = boundaryRect;
    }

    public boolean checkCollision(RectBoundedBox b) {
        return b.getBoundary().intersects(getBoundary());
    }

    public void setPosition(int x, int y) {
        this.x = x + Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        boundary = new Rectangle2D(this.x, this.y, width, height);
    }
}
