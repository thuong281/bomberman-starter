package uet.oop.bomberman.graphics;

import javafx.scene.image.Image;

public class BomberSprite {
    public static Image[] walkingRight = {Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage()};
    public static SpriteAnimation walkRight = new SpriteAnimation(walkingRight, 5);

//    public static final Image[] standingRight = {Sprite.balloom_left2.getFxImage()};
//    public static final SpriteAnimation standRight = new SpriteAnimation(standingRight, 5);

    public static Image[] walkingLeft = {Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage()};
    public static SpriteAnimation walkLeft = new SpriteAnimation(walkingLeft, 5);

    public static Image[] standingLeft = {Sprite.player_left.getFxImage()};
    public static SpriteAnimation standLeft = new SpriteAnimation(standingLeft, 5);

    public static Image[] walkingUp = {Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage()};
    public static SpriteAnimation walkUp = new SpriteAnimation(walkingUp, 5);

    public static Image[] walkingDown = {Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage()};
    public static SpriteAnimation walkDown = new SpriteAnimation(walkingDown, 5);

    public static SpriteAnimation animation = standLeft;
}
