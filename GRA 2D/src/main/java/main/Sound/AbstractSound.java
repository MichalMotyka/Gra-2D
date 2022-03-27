package main.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSound implements Sound {

    protected Clip clip;
    protected List<String> soundList = new ArrayList<String>();

    @Override
    public void setFile(int i) {
    String path = soundList.get(i);
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audio);
        }catch (Exception e){
            System.out.println("Error: "+ e);
        }
    }

    @Override
    public void play() {
        clip.start();
    }

    @Override
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void stop() {
        clip.stop();
    }
}
