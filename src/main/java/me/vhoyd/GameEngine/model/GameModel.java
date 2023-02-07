package me.vhoyd.GameEngine.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import me.vhoyd.GameEngine.entity.Player;
import me.vhoyd.GameEngine.manager.CollisionManager;
import me.vhoyd.GameEngine.manager.DrawingManager;
import me.vhoyd.GameEngine.manager.EntityManager;
import me.vhoyd.GameEngine.manager.SoundManager;
import me.vhoyd.GameEngine.manager.SpriteManager;
import me.vhoyd.GameEngine.task.GameUpdater;

public class GameModel {
  private GameFrame frame = new GameFrame("Game", this);
  private GamePanel panel = frame.getPanel();
  private DrawingManager dm = new DrawingManager(panel);
  private SpriteManager spm = new SpriteManager(panel);
  private SoundManager som = new SoundManager(panel);
  private GameUpdater updater = new GameUpdater(panel);
  private EntityManager em = new EntityManager(panel);
  private CollisionManager cm = new CollisionManager(panel);
  
  public GameModel() {
    StaticGame.setGameModel(this);
    frame.setSize(1080,540);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	try {
		Image icon = ImageIO.read(new File("images\\icon.png"));
		frame.setIconImage(icon);
	} catch (IOException e) {
		e.printStackTrace();
	}
       
  }

  public GameFrame getFrame() {
    return frame;
  }

  public GamePanel getPanel() {
    return panel;
  }

  public DrawingManager getDrawingManager() {
    return dm;
  }

  public SpriteManager getSpriteManager() {
	  return spm;
  }
  
  public SoundManager getSoundManager() {
	  return som;
  }
  
  public EntityManager getEntityManager() {
	  return em;
  }
  
  public CollisionManager getCollisionManager() {
	  return cm;
  }
  
  public Player getPlayer() {
	  return StaticGame.getPlayer();
  }
  
 public void setRunning(boolean running) {
	 if (running) {
		 if (!updater.isAlive()) {
			 updater.start();
		 }
	 } else {
		 if (updater.isAlive()) {
			 updater.interrupt();
		 }
	 }
 }

} 