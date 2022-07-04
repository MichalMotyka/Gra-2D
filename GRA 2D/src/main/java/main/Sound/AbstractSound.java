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

    public static Clip clip;
    public static double volume;
    public List<String> soundList = new ArrayList<String>();
    public FloatControl gain;

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

    public void setVol(double volume, Clip clip) {
        gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10) * 20);
        gain.setValue(dB);
    }

    @Override
    public void play() {
        clip.start();
        System.out.println(volume);

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
