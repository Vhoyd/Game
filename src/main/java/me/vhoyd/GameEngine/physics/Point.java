package me.vhoyd.GameEngine.physics;

public class Point {
	  private double x;
	  private double y;

	  public Point(double x, double y) {
	    this.x = x;
	    this.y = y;
	  }

	  public Point() {
	    this(0, 0);
	  }

	  public double getX() {
	    return x;
	  }

	  public double getY() {
	    return y;
	  }

	  public void setX(double x) {
	    this.x = x;
	  }

	  public void setY(double y) {
	    this.y = y;
	  }

	  public Point clone() {
	    return new Point(x, y);
	  }
	}