package me.vhoyd.GameEngine.physics;

public interface Collidable extends Movable, Weighable{
	public BoundingBox getBoundingBox();
	public void updateBoundingBox();
	public void collide(Collidable other);
	
	public default double getXMomentum() {
		return getMass()*getVelocity().getX();
	}
	
	public default double getYMomentum() {
		return getMass()*getVelocity().getY();
	}
	
	public default void move() {
		setX(getX() + getVelocity().getX());
		setY(getY() + getVelocity().getY());
		updateBoundingBox();
		System.out.println("moved");
	}
	
	public void addToCollisionManager();
}
