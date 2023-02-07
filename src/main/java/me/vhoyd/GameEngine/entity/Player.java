package me.vhoyd.GameEngine.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import me.vhoyd.GameEngine.model.GamePanel;
import me.vhoyd.GameEngine.model.StaticGame;
import me.vhoyd.GameEngine.physics.Vector;

  /**
  Subclass of the {@link Sprite} class used to specify which one the user is in control of.
  */
public class Player extends Entity {

 

  public Player(BufferedImage image, GamePanel panel) {
    super(image, panel);
    velocity = new Vector(0,0);
    StaticGame.setPlayer(this);
  }

  public Player(File image, GamePanel panel) throws IOException {
    super(image, panel);
    velocity = new Vector(0,0);
    StaticGame.setPlayer(this);
  }

  public Player(URL url, GamePanel panel) throws IOException {
    super(url, panel);
    velocity = new Vector(0,0);
    StaticGame.setPlayer(this);
  }

  

  

  public Vector getVelocity() {
    return velocity.clone();
  }

  public void setVelocity(Vector newVelocity) {
    velocity = newVelocity;
  }

  
}