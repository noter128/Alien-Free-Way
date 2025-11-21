package com.tg04.alienfreeway.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoundPlayer {
    private static final Logger LOGGER = Logger.getLogger(SoundPlayer.class.getName());
    private static Clip backgroundClip;

    public static void playSound(String filePath) {
        try {
            URL soundFile = SoundPlayer.class.getClassLoader().getResource(filePath);
            if (soundFile == null) {
                LOGGER.warning("Sound file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.addLineListener(event -> {
                if (Objects.equals(event.getType(), LineEvent.Type.STOP)) {
                    clip.close();
                }
            });

            clip.start();

            while (clip.isRunning()) {
                Thread.sleep(50);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while playing sound: " + filePath, e);
        }
    }

    public static void playBackgroundMusic(String filePath) {
        try {
            URL musicFile = SoundPlayer.class.getClassLoader().getResource(filePath);
            if (musicFile == null) {
                LOGGER.warning("Background music file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();
            LOGGER.info("Background music started: " + filePath);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Error playing background music: " + filePath, e);
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
            System.out.println("Background music stopped.");
        }
    }
}
