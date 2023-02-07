package me.vhoyd.GameEngine.view;

import java.awt.Graphics2D;

public interface Drawable {

  public void draw(Graphics2D g);

  public int getPriority();

  public void setPriority(int amount);
}