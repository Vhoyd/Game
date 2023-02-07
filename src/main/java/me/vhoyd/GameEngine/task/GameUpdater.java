package me.vhoyd.GameEngine.task;

import me.vhoyd.GameEngine.entity.Entity;
import me.vhoyd.GameEngine.model.GamePanel;

/**
Class for managing update triggers to various components of the game engine.  
*/
public class GameUpdater extends Thread {
  private static final long GRAPHICS_FPS = 60L;
  private static final long GRAPHICS_TICK = 1000L/GRAPHICS_FPS;
//  private static final long PHYSICS_FPS = 60L;
//  private static final long PHYSICS_TICK = 1000L/PHYSICS_FPS;

  private GamePanel panel;
  private volatile boolean running = true;

  public GameUpdater(GamePanel panel) {
    this.panel = panel;
  }
  
  /**
  For internal use only.
  */
  @Override
  @Deprecated
  public void run() {
    long lastGraphicsUpdate = System.currentTimeMillis();
    while (this.running) {
      long currentTime = System.currentTimeMillis();
      long timeSinceLastGraphicsUpdate = currentTime - lastGraphicsUpdate;
      if (timeSinceLastGraphicsUpdate > GRAPHICS_TICK) {
        for (Entity e : panel.getModel().getEntityManager().getContents()) {
        	EntityUpdater.UpdateEntity(e);        	
        }
        
        panel.repaint();
        lastGraphicsUpdate = currentTime;
        timeSinceLastGraphicsUpdate = 0L;
      }
      long timeUntilNextGraphicsUpdate = (long) GRAPHICS_TICK - timeSinceLastGraphicsUpdate;
      try {
				Thread.sleep(timeUntilNextGraphicsUpdate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    }
  }
}