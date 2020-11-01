package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;

public class Testtttttt {
    public static void main(String[] args) {
        Image[] walkingUp = {Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage(), Sprite.player_up.getFxImage()};

        SpriteAnimation walkUp = new SpriteAnimation(walkingUp, 5);
        SpriteAnimation walkLeft = new SpriteAnimation(walkingUp, 5);

        SpriteAnimation animation1 = walkUp;

        System.out.println(animation1.getTotalFrames());

        animation1 = walkLeft;

        System.out.println(animation1.getTotalFrames());

    }
}
