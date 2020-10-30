package uet.oop.bomberman;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MapInfo {
     static List<String> tmpLines;

     static {
        try {
            tmpLines = Files.readAllLines(Paths.get("res/levels/Level1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static String[] lines = tmpLines.toArray(new String[0]);
    static String[] mapLines = new String [lines.length-1];
    static String[] parts = lines[0].split(" ");

    static int  level = Integer.parseInt(parts[0]);
    static int rows = Integer.parseInt(parts[1]);
    static int cols = Integer.parseInt(parts[2]);

    public static int getLevel() {
        return level;
    }

    public static String[] getMapLines() {
        for (int i=1; i<lines.length; i++) {
            mapLines[i-1] =lines[i];
        }
        return mapLines;
    }

    public static int getRows() {
        return rows;
    }

    public static int getCols() {
        return cols;
    }

}
