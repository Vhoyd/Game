package me.vhoyd.GameEngine.manager;

import java.awt.Graphics2D;

import me.vhoyd.GameEngine.model.GamePanel;
import me.vhoyd.GameEngine.model.StaticGame;
import me.vhoyd.GameEngine.physics.Collidable;
import me.vhoyd.GameEngine.view.Drawable;


/**
Static class for tracking and managing all existing {@link Drawable} objects.  
*/
public final class DrawingManager extends AbstractManager<Drawable>{
  private int mp;

  public DrawingManager(GamePanel panel) {
    super(panel);
  }
  
  /**
  Draws every drawable currently registered onto the inputted Graphics2D object.
  @param g - the Graphics2D object the drawables will draw to.
  */
  @SuppressWarnings("unused")
public void repaintAll(Graphics2D g) {
    for (int i = 0; i <= mp; i++)
    for (Drawable d : contents) {
      if (d.getPriority() == i) {
        d.draw(g);
        if (d instanceof Collidable && StaticGame.renderBoundingBoxes) {
        	((Collidable)d).getBoundingBox().draw(g);
        }
      }
    }
  }

  /**
  Registers the given Drawable to the manager. Sprites automatically call this when they are created.
  @param d - the drawable to register.
  */

  /**
  Unregisters the given Drawable from the manager.
  @param d - the drawable to unregister.
  */

  public int getMaxPriority() {
    return mp;
  }

  public void setMaxPriority(int amount) {
    mp = amount;
  }
}