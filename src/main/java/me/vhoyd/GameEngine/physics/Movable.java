3package me.vhoyd.GameEngine.physics;

public interface Movable {
	public void setVelocity(Vector velocity);
	public Vector getVelocity();
	public double getX();
	public void setX(double x);
	public double getY();
	public void setY(double y);
	
	public default void move() {
		setX(getX() + getVelocity().getX());
		setY(getY() + getVelocity().getY());
	}
}
