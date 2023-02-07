package me.vhoyd.GameEngine.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import me.vhoyd.GameEngine.model.GamePanel;
import me.vhoyd.GameEngine.physics.BoundingBox;
import me.vhoyd.GameEngine.physics.Collidable;
import me.vhoyd.GameEngine.physics.Vector;
import me.vhoyd.GameEngine.view.Drawable;

public class Entity implements Drawable, Collidable {
	  protected BufferedImage visual;
	  protected double mass = 1;
	  protected double x = 0;
	  protected double y = 0;
	  protected int priority;
	  protected GamePanel p;
	  protected Image render;
	  protected BoundingBox hitBox;
	  protected double imageYscale;
	  protected double imageXscale;
	  protected String displayName = "None";
	  protected Vector velocity;
	  protected boolean inAir;

	  public Entity(BufferedImage image, GamePanel panel) {
	    visual = image;
	    panel.getModel().getDrawingManager().add(this);
	    p = panel;
	    render = visual;
	    hitBox = new BoundingBox(this, x, y, x+render.getWidth(null), y+render.getHeight(null));
	    velocity = new Vector();
	    panel.getModel().getEntityManager().add(this);
	  }

	  public Entity(File image, GamePanel panel) throws IOException {
	    visual = ImageIO.read(image);
	    panel.getModel().getDrawingManager().add(this);
	    p = panel;
	    render = visual;
	    hitBox = new BoundingBox(this, x, y, x+render.getWidth(null), y+render.getHeight(null));
	    velocity = new Vector();
	    panel.getModel().getEntityManager().add(this);
	  }

	  public Entity(URL url, GamePanel panel) throws IOException {
	    visual = ImageIO.read(url);
	    panel.getModel().getDrawingManager().add(this);
	    p = panel;
	    render = visual;
	    hitBox = new BoundingBox(this, x, y, x+render.getWidth(null), y+render.getHeight(null));
	    velocity = new Vector();
	    panel.getModel().getEntityManager().add(this);
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
	    resizeHitBox();
	  }

	  public void scale(double x, double y) {
	    render = visual.getScaledInstance((int)(visual.getWidth()*x), (int)(visual.getHeight()*y), 0);
	    resizeHitBox();
	  }

	  public BoundingBox getBoundingBox() {
	    return hitBox;
	  }
	  
	  public void updateBoundingBox() {
		  hitBox = hitBox.move(x, y);
		  System.out.println(getName() + " moved");
	  }
	  
	  public void collide(Collidable other) {
		  if (hitBox.overlaps(other.getBoundingBox())) {
			  //x velocity
			  double totalMomentumX = getXMomentum() + other.getXMomentum();
			  double thisVelocityX = (getMass()-other.getMass())/(getMass()+other.getMass());
			  thisVelocityX *= velocity.getX();
			  thisVelocityX += (2*other.getMass())/(getMass()+other.getMass()) * other.getVelocity().getX();
			  double thisNewMomentumX = getMass()*thisVelocityX;
			  double otherNewMomentumX = totalMomentumX - thisNewMomentumX;
			  double otherVelocityX = otherNewMomentumX / other.getMass();
			  
			  //y velocity
			  double totalMomentumY = getYMomentum() + other.getYMomentum();
			  double thisVelocityY = (getMass()-other.getMass())/(getMass()+other.getMass());
			  thisVelocityY *= velocity.getY();
			  thisVelocityY += (2*other.getMass())/(getMass()+other.getMass()) * other.getVelocity().getY();
			  double thisNewMomentumY = getMass()*thisVelocityY;
			  double otherNewMomentumY = totalMomentumY - thisNewMomentumY;
			  double otherVelocityY = otherNewMomentumY / other.getMass();
			  
			  //combine values
			  velocity = new Vector(thisVelocityX, thisVelocityY);
			  other.setVelocity(new Vector(otherVelocityX, otherVelocityY));
		  }
	  }
	  
	  public Vector getVelocity() {
		  return velocity;
	  }
	  
	  public void setVelocity(Vector velocity) {
		  this.velocity = velocity; 
	  }
	  
	  private void resizeHitBox() {
		  hitBox = new BoundingBox(this, x, y, x+render.getWidth(null), y+render.getHeight(null));
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
	}

	@Override
	public double getMass() {
		return mass;
	}

	@Override
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public boolean isInAir() {
	    return inAir;
	}
	
	public void setInAir(boolean inAir) {
	    this.inAir = inAir;
	}

	@Override
	public void addToCollisionManager() {
		p.getModel().getCollisionManager().add(this);
	}
}
