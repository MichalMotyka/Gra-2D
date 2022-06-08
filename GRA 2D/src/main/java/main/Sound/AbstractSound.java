package main.Sound;

import main.Config;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSound implements Sound {

    protected Clip clip;
    protected List<String> soundList = new ArrayList<String>();
    protected double volume;

    @Override
    public void setFile(int i) {
    String path = soundList.get(i);
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audio);
            setVol(volume, clip);
        }catch (Exception e){
            System.out.println("Error: "+ e);
        }
    }

    private static void setVol(double volume, Clip clip) {
        FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10) * 20);
        gain.setValue(dB);
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
