package me.vhoyd.GameEngine.view;

import java.awt.Font;
import java.awt.Graphics2D;

import me.vhoyd.GameEngine.model.StaticGame;
import me.vhoyd.GameEngine.physics.Point;
import me.vhoyd.GameEngine.task.TextPanelUpdater;

public class TextPanel implements Drawable {
  private int priority = 0;
  private Point topLeft;
  private String text;
  private Font font;
  private TextPanelUpdater updater;

  public TextPanel(String text, double x, double y, Font font, TextPanelUpdater updater) {
    this(text, new Point(x,y), font, updater);
  }

  private TextPanel(String text, Point coords, Font font, TextPanelUpdater updater) {
    topLeft = coords;
    this.text = text;
    this.font = font;
    this.updater = updater;
    StaticGame.getModel().getDrawingManager().add(this);
  }

  public TextPanel(TextPanelUpdater updater) {
    this("", 0, 0, updater);
  }

  public TextPanel(String text, double x, double y, TextPanelUpdater updater) {
    this(text, x, y, "Arial", updater);
  }

  public TextPanel(String text, double x, double y, String fontName, TextPanelUpdater updater) {
    this(text, new Point(x, y), Font.getFont(fontName), updater);
  }

  public void draw(Graphics2D g) {
    g.setFont(font);
    g.drawString(text, (float)topLeft.getX(), (float)topLeft.getY());
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int amount) {
    priority = amount;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setX(double x) {
    topLeft.setX(x);
  }

  public void setY(double y) {
    topLeft.setY(y);
  }

  public double getX() {
    return topLeft.getX();
  }

  public double getY() {
    return topLeft.getY();
  }
  
  public TextPanelUpdater getUpdater() {
	  return updater;
  }
}