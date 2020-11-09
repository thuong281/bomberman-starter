package uet.oop.bomberman;


import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.explodebomb.ExplodeBomb;
import uet.oop.bomberman.scenes.Sandbox;

import java.io.File;

public class Testtttttt {
    public static void main(String[] args) {
        File file = new File("./res/sprites");
        for(String fileNames : file.list()) System.out.println(fileNames);
    }

}

