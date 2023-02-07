package me.vhoyd.GameEngine.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import me.vhoyd.GameEngine.model.GamePanel;
import me.vhoyd.GameEngine.physics.Movable;
import me.vhoyd.GameEngine.physics.Vector;
import me.vhoyd.GameEngine.view.Drawable;

/**
  Class for representing an in-game object that can be drawn and moved around.  
*/
public class Sprite implements Drawable, Movable{
  protected BufferedImage visual;
  protected double x = 0;
  protected double y = 0;
  protected int priority;
  protected GamePanel p;
  protected Image render;
  protected double imageYscale;
  protected double imageXscale;
  protected String displayName = "None";
  protected Vector velocity;

  public Sprite(BufferedImage image, GamePanel panel) {
    visual = image;
    panel.getModel().getSpriteManager().add(this);
    p = panel;
    render = visual;
    velocity = new Vector();
  }

  public Sprite(File image, GamePanel panel) throws IOException {
    visual = ImageIO.read(image);
    panel.getModel().getSpriteManager().add(this);
    p = panel;
    render = visual;
    velocity = new Vector();
  }

  public Sprite(URL url, GamePanel panel) throws IOException {
    visual = ImageIO.read(url);
    panel.getModel().getSpriteManager().add(this);
    p = panel;
    render = visual;
    velocity = new Vector();
  }

  /**
  @return - the BufferedImage associated with this Sprite.
  */
  public BufferedImage getImage() {
    return visual;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  /**
  Changes the image this Sprite uses to render.
  @param image - the new image for this Sprite to use.
  */
  public void setImage(BufferedImage image) {
    visual = image;
  }

  /**
  Changes the image this Sprite uses to render.
  @param image - the new image for this Sprite to use.
  */
  public void setImage(File image) throws IOException {
    visual = ImageIO.read(image);
  }

  /**
  Changes the image this Sprite uses to render.
  @param image - the new image for this Sprite to use.
  */
  public void setImage(URL image) throws IOException {
    visual = ImageIO.read(image);
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setCoords(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
  Draws this Sprite's BufferedImage on the given Graphics2D object.
  @param g - the Graphics2D surface to draw this Sprite's BufferedImage on.
  */
  public void draw(Graphics2D g) {
    g.drawImage(render, (int)x, (int)y, null);
  }

  public void setPriority(int amount) {
    priority = amount;
    if (p.getModel().getDrawingManager().getMaxPriority() < priority) {
      p.getModel().getDrawingManager().setMaxPriority(priority);
    }
  }

  public int getPriority() {
    return priority;
  }

  public GamePanel getPanel() {
    return p;
  }

  public void scalePercent(double x, double y) {
    render = visual.getScaledInstance((int)(visual.getWidth()*x/100), (int)(visual.getHeight()*y/100), 0);
  }

  public void scale(double x, double y) {
    render = visual.getScaledInstance((int)(visual.getWidth()*x), (int)(visual.getHeight()*y), 0);
  }
  
  public Vector getVelocity() {
	  return velocity;
  }
  
  public void setVelocity(Vector velocity) {
	  this.velocity = velocity; 
  }

  
  public void setName(String name) {
	  displayName = name;
  }
  
  public String getName() {
	  return displayName;
  }

  public void move() {
	  x += velocity.getX();
	  y += velocity.getY();
	  velocity = new Vector();
  }
}