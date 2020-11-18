/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.sound;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author phamn
 */
public class Sounds {
    public static final String bomb_bang = "res/sounds/bomb_bang.wav";
    public static final String  itemsd = "res/sounds/item.wav";
    public static final String monstersd ="res/sounds/monster_die.wav";
    public static final String bomber_die= "res/sounds/bomber_die.wav";
    public static final String losesd = "res/sounds/lose.wav";
    public static final String winsd= "res/sounds/win.wav";
    public static final String  newbom= "res/sounds/newbomb.wav";
    public static Sounds instance;
    private Clip clip;

    public  Sounds(File path){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels()*2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        }catch(Exception e){}
    }

    public void play(){
        if(clip !=null){
            stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void stop(){
        if(clip.isRunning()) clip.stop();
    }

    public void close(){
        clip.close();
    }
    public static Sounds getIstance(String name) {
        if (name == bomb_bang) {
            instance = new Sounds(new File(bomb_bang));
        }if (name == bomber_die) {
            instance = new Sounds(new File(bomber_die));
        }if (name == itemsd) {
            instance = new Sounds(new File(itemsd));
        }if (name == losesd) {
            instance = new Sounds(new File(losesd));
        }if (name == winsd) {
            instance = new Sounds(new File(winsd));
        }if (name == newbom) {
            instance = new Sounds(new File(newbom));
        }if (name == monstersd) {
            instance = new Sounds(new File(monstersd));
        }
        return instance;
    }
}
