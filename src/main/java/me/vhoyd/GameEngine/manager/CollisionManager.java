package me.vhoyd.GameEngine.manager;

import me.vhoyd.GameEngine.model.GamePanel;
import me.vhoyd.GameEngine.physics.Collidable;

public class CollisionManager extends AbstractManager<Collidable>{

	public CollisionManager(GamePanel panel) {
		super(panel);
	}
	
	public void updateCollisions() {
		for (Collidable c1 : contents) {
			for (Collidable c2: contents) {
				c1.collide(c2);
			}
		}
	}

}
