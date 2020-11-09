package uet.oop.bomberman.graphics.Animation;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteAnimation;

public class BomberManAnimation {

    //right
    public static Image[] walkingRight = {Sprite.player_right_1.getFxImagePlayer(), Sprite.player_right_2.getFxImagePlayer()};
    public static SpriteAnimation walkRight = new SpriteAnimation(walkingRight, 5);

    public static final Image[] standingRight = {Sprite.player_right.getFxImagePlayer()};
    public static final SpriteAnimation standRight = new SpriteAnimation(standingRight, 5);

    //left
    public static Image[] walkingLeft = {Sprite.player_left_1.getFxImagePlayer(), Sprite.player_left_2.getFxImagePlayer()};
    public static SpriteAnimation walkLeft = new SpriteAnimation(walkingLeft, 5);

    public static Image[] standingLeft = {Sprite.player_left.getFxImagePlayer()};
    public static SpriteAnimation standLeft = new SpriteAnimation(standingLeft, 5);

    //up
    public static Image[] walkingUp = {Sprite.player_up_1.getFxImagePlayer(), Sprite.player_up_2.getFxImagePlayer()};
    public static SpriteAnimation walkUp = new SpriteAnimation(walkingUp, 5);

    public static final Image[] standingUp = {Sprite.player_up.getFxImagePlayer()};
    public static final SpriteAnimation standUp = new SpriteAnimation(standingUp, 5);

    //down
    public static Image[] walkingDown = {Sprite.player_down_1.getFxImagePlayer(), Sprite.player_down_2.getFxImagePlayer()};
    public static SpriteAnimation walkDown = new SpriteAnimation(walkingDown, 5);

    public static final Image[] standingDown = {Sprite.player_down.getFxImagePlayer()};
    public static final SpriteAnimation standDown = new SpriteAnimation(standingDown, 5);

    //die
    public static Image[] dying = {Sprite.player_dead1.getFxImagePlayer(), Sprite.player_dead2.getFxImagePlayer(),
            Sprite.player_dead3.getFxImagePlayer()};
    public static SpriteAnimation die = new SpriteAnimation(dying, 30);

    //initial sprite
    public static SpriteAnimation animation = standRight;

}
