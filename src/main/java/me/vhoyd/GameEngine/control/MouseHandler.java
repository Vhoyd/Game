package me.vhoyd.GameEngine.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputListener;

/**
Class for handling mouse inputs. For internal use.
*/
public class MouseHandler implements MouseInputListener, MouseWheelListener {

  public MouseHandler() {}

  public void mouseDragged(MouseEvent e) {
    mouseMoved(e);
    mousePressed(e);
  }

  public void mouseMoved(MouseEvent e) {
    MouseObject.setX(e.getPoint().getX());
    MouseObject.setY(e.getPoint().getY());
  }

  public void mouseExited(MouseEvent e) {}

  public void mouseEntered(MouseEvent e) {}

  public void mouseClicked(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {
    MouseObject.setPressed(false);
    MouseObject.setButton(0);
  }

  public void mousePressed(MouseEvent e) {
    MouseObject.setPressed(true);
    MouseObject.setButton(e.getButton());
  }

  public void mouseWheelMoved(MouseWheelEvent e) {
    if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
      MouseObject.setWheelRotation(e.getScrollAmount());
    } else {
      MouseObject.setWheelRotation(1);
    }
  }
}