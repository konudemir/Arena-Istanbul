package m;

import java.io.*;

import javax.sound.sampled.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicPlayer {
    private Clip clip;
    private FloatControl volumeControl;
    public static int setMusicVolume = 100;
    public static boolean currentlyClosed = false;
    public Timer repeatTimer;
    public MusicPlayer() {
        // Set up a timer that calls x() every 5000 ms
        repeatTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.checkIfMusicShouldPlay();
            }
        });
        repeatTimer.start();
    }
    private int pausePosition = 0;

public void pauseMusic() {
    currentlyClosed = true;
    if (clip != null && clip.isRunning()) {
        pausePosition = clip.getFramePosition();
        clip.stop();
    }
}

public void resumeMusic() {
    currentlyClosed = false;
    if (clip != null && !clip.isRunning()) {
        clip.setFramePosition(pausePosition);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}


    public void playMusic(String filePath) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getVolumePercent() {
    if (volumeControl != null) {
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        float current = volumeControl.getValue();
        return Math.round((current - min) / (max - min) * 100f);
    }
    return -1; // Return -1 if volumeControl is not initialized
    }

    public void setVolume(int volumePercent) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float gain = min + (max - min) * (volumePercent / 100f);
            volumeControl.setValue(gain);
        }
    }

    public void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}

