package me.vhoyd.GameEngine.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
Class for handling keyboard inputs. For internal use.
*/
public class KeyHandler implements KeyListener {

  public KeyHandler() {}

  public void keyPressed(KeyEvent e) {
    KeyboardObject.addKey(e.getExtendedKeyCode());
  }

  public void keyReleased(KeyEvent e) {
    KeyboardObject.removeKey(e.getExtendedKeyCode());
  }

  public void keyTyped(KeyEvent e) {}

}