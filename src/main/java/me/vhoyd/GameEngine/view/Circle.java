package me.vhoyd.GameEngine.view;

import java.awt.Graphics2D;
import java.awt.Color;

public class Circle extends ShapeObject {
  
  private double angle = 0;
  private double radius = 50;
  
  public void draw(Graphics2D g) {
    g.setColor(Color.GREEN);
    double x = Math.cos(angle)*radius;
    double y = Math.sin(angle)*radius;
    g.drawLine(300, 100, 300+(int)x, 100+(int)y);
    angle += Math.PI/180;
    angle = angle%(2*Math.PI);
    // System.out.println(Math.cos(angle));
    // System.out.println(Math.sin(angle));
    // System.out.println(Math.toDegrees(angle));
    // System.out.println();
  }

  public Circle() {
    super();
  }
}