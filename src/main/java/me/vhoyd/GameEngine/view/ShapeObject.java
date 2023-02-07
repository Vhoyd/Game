package me.vhoyd.GameEngine.view;

import java.awt.Graphics2D;

import me.vhoyd.GameEngine.model.StaticGame;

public abstract class ShapeObject implements Drawable {
  private int priority = 0;

  public abstract void draw(Graphics2D g);

  public ShapeObject() {
    StaticGame.getModel().getDrawingManager().add(this);
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int amount) {
    priority = amount;
    if (StaticGame.getModel().getDrawingManager().getMaxPriority() < priority) {
      StaticGame.getModel().getDrawingManager().setMaxPriority(priority);
    }
  }
}