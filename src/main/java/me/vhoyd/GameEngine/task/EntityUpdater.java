package me.vhoyd.GameEngine.task;

import java.awt.event.WindowEvent;

import me.vhoyd.GameEngine.control.KeyboardObject;
import me.vhoyd.GameEngine.control.MouseObject;
import me.vhoyd.GameEngine.control.KeyboardObject.SpecialKey;
import me.vhoyd.GameEngine.entity.Entity;
import me.vhoyd.GameEngine.entity.Player;
import me.vhoyd.GameEngine.physics.Point;
import me.vhoyd.GameEngine.physics.Vector;
import me.vhoyd.GameEngine.physics.BoundingBox.Side;
import me.vhoyd.GameEngine.physics.Vector.Gravity;
import me.vhoyd.GameEngine.sound.Sound;

/**
Static class for updating information about a Player.  
*/
public class EntityUpdater {

  private static double speed = 15;
  private static Point oldPlayerPos;
  private static boolean wasHeld;

  private EntityUpdater() {}

  /**
  Uses data from the {@link KeyboardObject} and {@link MouseObject} static classes to modify things about the {@link Player}.
  @param p - the Player to modify.
  */
  public static void UpdateEntity(Entity e) {
    if (e == null) {
      return;
    }
    boolean isPlayer = e instanceof Player;
    if (isPlayer) {
	    if (!(MouseObject.isPressed())) {
	      wasHeld = false;
	      if (KeyboardObject.areKeysPressed('c','d','=')) {
	    	  e.getPanel().getModel().getFrame().dispatchEvent(new WindowEvent(e.getPanel().getModel().getFrame(), WindowEvent.WINDOW_CLOSING));
	    	  return;
	      }
	      if (KeyboardObject.isKeyPressed('w')) {
	    	  if (Vector.getGravity() == Gravity.RIGHT || Vector.getGravity() == Gravity.LEFT) {    		  
	    		  if (Math.abs(e.getVelocity().getY()) < speed) {
	        		e.setVelocity(e.getVelocity().add(0, -0.1));
	    		  }
	    	  }
	      }
	      if (KeyboardObject.isKeyPressed('s')) {
	    	  if (Vector.getGravity() == Gravity.RIGHT || Vector.getGravity() == Gravity.LEFT) {    		  
	    		  if (Math.abs(e.getVelocity().getY()) < speed) {
	        		e.setVelocity(e.getVelocity().add(0, 0.1));
	    		  }
	    	  }
	      }
	      if (KeyboardObject.isKeyPressed('a')) {
	    	  if (Vector.getGravity() == Gravity.DOWN || Vector.getGravity() == Gravity.UP) {    		  
	    		  if (Math.abs(e.getVelocity().getX()) < speed) {
	        		e.setVelocity(e.getVelocity().add(-0.1, 0));
	    		  }
	    	  }
	      }
	      if (KeyboardObject.isKeyPressed('d')) {
	    	  if (Vector.getGravity() == Gravity.DOWN || Vector.getGravity() == Gravity.UP) {    		  
	    		  if (Math.abs(e.getVelocity().getX()) < speed) {
	        		e.setVelocity(e.getVelocity().add(0.1, 0));
	    		  }
	    	  }
	      }
	      if (KeyboardObject.isSpecialKeyPressed(SpecialKey.SPACE) && !e.isInAir()) {
	    	  boolean vertical =Vector.getGravity().getX() == 0; 
	    	  double degree = (vertical ?  e.getVelocity().getY() : e.getVelocity().getX());
	    	  double gravityDegree = (vertical? Vector.getGravity().getY() : Vector.getGravity().getX());
	    	  System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	    		if (degree > -0.1*gravityDegree/Math.abs(gravityDegree)) {
	        	e.setVelocity(e.getVelocity().add(0, -4.5*gravityDegree/Math.abs(gravityDegree)));
	        	try {
					Sound jump = e.getPanel().getModel().getSoundManager().getSound("jump");
					jump.play();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	        }
	      }
	      if (KeyboardObject.isSpecialKeyPressed(SpecialKey.UP)) {
	    	  Vector.setGravity(Gravity.UP);
	      }
	      if (KeyboardObject.isSpecialKeyPressed(SpecialKey.DOWN)) {
	    	  Vector.setGravity(Gravity.DOWN);
	      }
	      if (KeyboardObject.isSpecialKeyPressed(SpecialKey.RIGHT)) {
	    	  Vector.setGravity(Gravity.RIGHT);
	      }
	      if (KeyboardObject.isSpecialKeyPressed(SpecialKey.LEFT)) {
	    	  Vector.setGravity(Gravity.LEFT);
	      }
	    } else {
	      oldPlayerPos = new Point(e.getX(), e.getY());
	      e.setX(MouseObject.getX()-e.getBoundingBox().getWidth()/2);
	      e.setY(MouseObject.getY()-e.getBoundingBox().getHeight()/2);
	      double dX = e.getX()-oldPlayerPos.getX();
	      if (Math.abs(dX) < 1) dX = e.getVelocity().getX();
	      double dY = e.getY()-oldPlayerPos.getY();
	      if (Math.abs(dY) < 1) dY = e.getVelocity().getY();
	      if (wasHeld) {
	        e.setVelocity(new Vector(dX,dY));
	      } else {
	        wasHeld = true;
	      }
	      e.updateBoundingBox();
	      e.setInAir(true);
	    }
    }
    Side collision;
    if (e.getBoundingBox().getCoordPosition(Side.DOWN) > e.getPanel().getHeight()) {
    	collision = Side.DOWN;
    	e.setY(e.getPanel().getHeight()-e.getBoundingBox().getHeight());
    }
    else if (e.getBoundingBox().getCoordPosition(Side.LEFT) < 0) {
    	collision = Side.LEFT;
    	e.setX(0);
    	e.updateBoundingBox();
    }
    else if (e.getBoundingBox().getCoordPosition(Side.RIGHT) > e.getPanel().getWidth()) {
    	collision = Side.RIGHT;
    	e.setX(e.getPanel().getWidth()-e.getBoundingBox().getWidth());
    	e.updateBoundingBox();
    	
    }
    else if (e.getBoundingBox().getCoordPosition(Side.UP) < 0) {
    	collision = Side.UP;
    	e.setY(0);
    } else {
    	collision = Side.NULL;
    }
    if (!isPlayer) {
    	System.out.println(e.getBoundingBox().getCoordPosition(Side.DOWN));    	
    }
    double degree;
	boolean horizontal = false;
	if (collision == Side.LEFT || collision == Side.RIGHT) {
		degree = e.getVelocity().getX();
		horizontal = true;
	} else {
		degree = e.getVelocity().getY();
	}
	System.out.print(e.getName() + " ");
	System.out.print(isPlayer + " ");
	System.out.println(collision.toString());
    if (collision != Side.NULL && (!MouseObject.isPressed() || !isPlayer)) {    	
    	e.getPanel().getModel().getSoundManager().getSound("hit").play();
    	System.out.println("sound hit played");
    	
    	degree *= -1;
    	if (collision.toString().equals(Vector.getGravity().toString())) {
        	degree = (Math.abs(degree) < 5 ? 0 : degree*0.5);
    	} 
    	if (horizontal) {
			e.setVelocity(new Vector(degree, e.getVelocity().getY()));
		} else {
			e.setVelocity(new Vector(e.getVelocity().getX(), degree));    			
		}
		
    }
    e.setInAir(degree != 0 && !collision.toString().equals(Vector.getGravity().toString()));
    if (e.isInAir()) {
    	e.setVelocity(e.getVelocity().add(Vector.getGravity()));
    	
    }

    if (Vector.getGravity() == Gravity.DOWN || Vector.getGravity() == Gravity.UP) {
    	if (Math.abs(e.getVelocity().getX()) > 0) {
    		e.setVelocity(new Vector(e.getVelocity().getX()*0.98, e.getVelocity().getY()));
    	}
    } else {
    	if (Math.abs(e.getVelocity().getY()) > 0) {
    		e.setVelocity(new Vector(e.getVelocity().getX(), e.getVelocity().getY()*0.98));
    	}
    }
    if (!MouseObject.isPressed() || !isPlayer) e.move();
  }  
}