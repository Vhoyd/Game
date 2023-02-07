package me.vhoyd.GameEngine.manager;

import me.vhoyd.GameEngine.entity.Sprite;
import me.vhoyd.GameEngine.model.GamePanel;

/**
Static class for tracking and managing all existing {@link Sprite} objects.  
*/
public final class SpriteManager extends AbstractManager<Sprite>{

  public SpriteManager(GamePanel panel) {
    super(panel);
  }
  /**
  Registers the given Sprite to the manager. Sprites automatically call this when they are created.
  @param sprite - the sprite to register.
  */

  @Override
  public void add(Sprite sprite) {
    super.add(sprite);
    panel.getModel().getDrawingManager().add(sprite);
  }

  /**
  Unregisters the given Sprite from the manager.
  @param sprite - the sprite to unregister.
  */
  
  @Override
  public void remove(Sprite sprite) {
    super.remove(sprite);
    panel.getModel().getDrawingManager().remove(sprite);
  }

  /**
  Fetches the single {@link Player} object registered to the manager.
  @return - the Player object, or null if none exists yet.
  */
//  public Player getPlayer() {
//    for (Sprite sprite : contents) {
//      if (sprite instanceof Player) {
//        return (Player)sprite;
//      }
//    }
//    return null;
//  }
}