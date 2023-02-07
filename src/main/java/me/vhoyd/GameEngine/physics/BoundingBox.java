package me.vhoyd.GameEngine.physics;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import me.vhoyd.GameEngine.entity.Sprite;
import me.vhoyd.GameEngine.view.Drawable;

public class BoundingBox implements Drawable {
  private Point TL;
  private Point BR;
  private Collidable holder;

  public BoundingBox(Collidable holder, Point topLeft, Point bottomRight) {
    TL = topLeft;
    BR = bottomRight;
    this.holder = holder;
  }

  public BoundingBox(Collidable holder, double x1, double y1, double x2, double y2) {
    TL = new Point(x1, y1);
    BR = new Point(x2, y2);
    this.holder = holder;
  }

  public enum Corner {
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT,
    ;
  }

  public enum Side {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    NULL,
    ;
  }

  public Point getCorner(Corner type) {
    if (type == Corner.UP_LEFT) {
      return TL.clone();
    } else if (type == Corner.DOWN_RIGHT) {
      return BR.clone();
    } else if (type == Corner.DOWN_LEFT) {
      return new Point(TL.getX(), BR.getY());
    } else {
      return new Point(BR.getX(), TL.getY());
    }
  }

  public double getCoordPosition(Side side) {
    if (side == Side.LEFT) {
      return TL.getX();
    } else if (side == Side.RIGHT) {
      return BR.getX();
    } else if (side == Side.UP) {
      return TL.getY();
    } else {
      return BR.getY();
    }
  }

  public List<Point> getCorners() {
    List<Point> points = new ArrayList<Point>();
    points.add(TL.clone());
    points.add(BR.clone());
    points.add(new Point(TL.getX(), BR.getY()));
    points.add(new Point(BR.getX(), TL.getY()));
    return points;
  }

  public boolean contains(Point point) {
    boolean right = point.getX() > TL.getX();
    boolean left = point.getX() < BR.getX();
    boolean up = point.getY() > TL.getX();
    boolean down = point.getY() < BR.getY();
    return (right && left && up && down);
  }

  public boolean overlaps(BoundingBox other) {
    boolean cornerStates[] = {false, false, false, false};
    int i = 0;
    for (Point point : other.getCorners()) {
      cornerStates[i] = contains(point);
      i++;
    }
    return cornerStates[0] || cornerStates[1] || cornerStates[2] || cornerStates[3];
  }

  public double getWidth() {
    return Math.abs(BR.getX() - TL.getX());
  }

  public double getHeight() {
    return Math.abs(BR.getY() - TL.getY());
  }

  public BoundingBox scale(double x, double y) {
    return new BoundingBox(holder, TL.getX(), TL.getY(), BR.getX()*x, BR.getY()*y);
  }

  public BoundingBox shift(double x, double y) {
    return new BoundingBox(holder, TL.getX()+x, TL.getY()+y, BR.getX()+x, BR.getY()+y);
  }
  
  public BoundingBox move(double x, double y) {
	  return new BoundingBox(holder, x,y,x+getWidth(),y+getHeight());
  }

  	public void draw(Graphics2D g) {
		g.drawLine((int)TL.getX(), (int)TL.getY(), (int)BR.getX(), (int)TL.getY());
		g.drawLine((int)TL.getX(), (int)TL.getY(), (int)TL.getX(), (int)BR.getY());
		g.drawLine((int)BR.getX(), (int)TL.getY(), (int)BR.getX(), (int)BR.getY());
		g.drawLine((int)TL.getX(), (int)BR.getY(), (int)BR.getX(), (int)BR.getY());
		g.drawLine((int)TL.getX(), (int)TL.getY(), (int)BR.getX(), (int)BR.getY());
		g.drawLine((int)TL.getX(), (int)BR.getY(), (int)BR.getX(), (int)TL.getY());
		if (holder instanceof Sprite) {
			Sprite s = ((Sprite)holder);
			g.drawLine((int)TL.getX(), (int)TL.getY(), (int)s.getX(), (int)s.getY());
  		}
  	}
  	
  	public Collidable getHolder() {
  		return holder;
  		
  	}

	public int getPriority() {
		return 4;
	}
	
	public void setPriority(int amount) {
		// TODO Auto-generated method stub
		
	}
	
	public Point getCenter() {
		return new Point((BR.getX()+TL.getX())/2, (BR.getY()+TL.getY())/2);
	}
}