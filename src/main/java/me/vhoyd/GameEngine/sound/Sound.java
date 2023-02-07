package me.vhoyd.GameEngine.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import me.vhoyd.GameEngine.manager.SoundManager;

public class Sound {
	private File cloneFile;
	private Clip clip;
	private AudioInputStream ais;
	private long frame;
	private String name;
	private SoundManager manager;
	
	public Sound(String filepath, String name, SoundManager soundManager) throws Exception {
		this.name = name;
		manager = soundManager;
		File soundFile = new File(filepath);
		cloneFile = soundFile;
		ais = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(ais);
		updateSoundBoost();
	}
	
	public Sound(File file, String name, SoundManager soundManager) throws Exception {
		this.name = name;
		manager = soundManager;
		File soundFile = file;
		cloneFile = file;
		ais = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(ais);
		updateSoundBoost();
	}
	
	private Sound(File f, long fr, String n, SoundManager soundManager) throws Exception {
		name = n;
		manager = soundManager;
		File soundFile = f;
		cloneFile = f;
		ais = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(ais);
		updateSoundBoost();
	}
	
	public void play() {
		clip.start();
	}
	
	public void stop() {
		frame = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public void reset() {
		clip.stop();
		clip.setMicrosecondPosition(0);
	}
	
	public void resume() {
		clip.setMicrosecondPosition(frame);
		clip.start();
	}
	
	public void skipTo(long microseconds) {
		clip.stop();
		clip.setMicrosecondPosition(microseconds);
		clip.start();
	}
	
	public String getName() {
		return name;
	}
	
	public Sound clone() {
		try {
			return new Sound(cloneFile, frame, name, manager);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateSoundBoost() {
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(manager.getVolumeBoost());
	}
}
