package me.vhoyd.GameEngine.manager;

import java.io.File;

import me.vhoyd.GameEngine.model.GamePanel;
import me.vhoyd.GameEngine.sound.Sound;

public class SoundManager extends AbstractManager<Sound>{
	private float volumeBoost = 6f;
	public SoundManager(GamePanel panel) {
		super(panel);
		File audioFolder = new File("audio");
		if (audioFolder.isDirectory()) {
			for (File sound: audioFolder.listFiles()) {
				try {
					add(new Sound(sound, sound.getName(), this));
				} catch (Exception e) {
					System.err.println("Error loading sound "+sound.getName());
					e.printStackTrace();
				}
			}
		} else {
			Exception e = new Exception("Sound folder could not be located");
			e.printStackTrace();
		}
	}
	
	public Sound getSound(String name) {
		for (Sound s : contents) {
			if (s.getName().equals(name+".wav")) {
				return s.clone();
			}
		}
		return null;
	}
	
	public float getVolumeBoost() {
		return volumeBoost;
	}
	
	public void setVolumeBoost(float volume) {
		volumeBoost = volume;
		for (Sound s : contents) {
			s.updateSoundBoost();
		}
	}
}
