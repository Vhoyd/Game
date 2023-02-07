package me.vhoyd.GameEngine.physics;

public class Vector {
	private static Gravity g = Gravity.DOWN;
	  private double x;
	  private double y;

	  public Vector() {
	    this(0d,0d);
	  }

	  public Vector(double x, double y) {
	    this.x = x;
	    this.y = y;
	  }
	  
	  public Vector clone() {
	    return new Vector(x,y);
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

	  public void normalize() {
	    divide(length());
	  }

	  public Vector normalized() {
	    return new Vector(x/length(), y/length());
	  }

	  public double sin() {
	    return y/length();
	  }

	  public double cos() {
	    return x/length();
	  }

	  public double length() {
	    return Math.sqrt((Math.pow(x,2))+(Math.pow(y,2)));
	  }

	  public double angleTo(Vector other) {
			return Math.atan2(other.y - y, other.x - x);
		}

	  public double dotProduct(Vector other) {
	    return x*other.x+y*other.y;
	  }

	  public Vector divide(double scale) {
	    return new Vector(x/scale,y/scale);
	  }

	  public Vector multiply(double scale) {
	    return new Vector(x*scale,y*scale);
	  }

	  public Vector add(Vector other) {
	    return new Vector(x+other.x,y+other.y);
	  }

	  public Vector subtract(Vector other) {
	    return new Vector(x-other.x,y-other.y);
	  }

	  public Vector add(double x, double y) {
	      return new Vector(x+this.x,y+this.y);
	  }

	  public Vector subtract(double x, double y) {
	    return new Vector(x-this.x,y-this.y);
	  }

	  public double hypotenuse(Vector other) {
			return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
		}
	  
	  public static Vector vectorBetween(Point p1, Point p2) {
		  return new Vector(p2.getX()-p1.getX(), p2.getY()-p1.getY());
	  }
	  
	  public static class Gravity extends Vector{
		  public static final Gravity UP = new Gravity(0,-0.1, "UP");
		  public static final Gravity DOWN = new Gravity(0,0.1, "DOWN");
		  public static final Gravity RIGHT = new Gravity(0.1, 0, "RIGHT");
		  public static final Gravity LEFT = new Gravity(-0.1, 0, "LEFT");
		  
		  private String name;
		  
		  private Gravity(double x, double y, String name) {
			  super(x,y);
			  this.name = name;
		  }
		  
		  public Gravity(double x, double y) {
			  this(x, y, "CUSTOM");
		  }
		  
		  @Override
		  public String toString() {
			  return name;
		  }
	  }
	  
	  public static Gravity getGravity() {
		  return g;
	  }
	  
	  public static void setGravity(Gravity gravity) {
		  g = gravity;
	  }
	  
	  
	}