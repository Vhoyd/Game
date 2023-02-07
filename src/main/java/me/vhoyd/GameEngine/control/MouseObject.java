package me.vhoyd.GameEngine.control;

import java.awt.MouseInfo;

import me.vhoyd.GameEngine.physics.Point;

/**
Static class for tracking information about the user's actions involving the mouse. Changing values about this class does not affect anything physically about the mouse, just leads to innacurate information until the next time the user does something with the mouse.
*/
public class MouseObject {
  private static boolean currentlyPressed;
  private static int buttonNumber;
  private static double x;
  private static double y;
  private static int maxButtons = MouseInfo.getNumberOfButtons();
  private static int wheelRotation;

  private MouseObject() {}

  /**
  Changes the state of whether any button the mouse is "pressed" or not.
  @param pressed - whether the mouse is pressed.
  */
  public static void setPressed(boolean pressed) {
    currentlyPressed = pressed;
  }

  /**
  @return - whether any button on the mouse is currently being pressed or not.
  */
  public static boolean isPressed() {
    return currentlyPressed;
  }

  /**
  @return - the number corresponding to which button is being pressed, or 0 if no buttons are pressed.
  */
  public static int getButton() {
    return buttonNumber;
  }

  /**
  Changes which mouse button is currently "pressed".
  @param number - the button number being pressed.
  */
  public static void setButton(int number) {
    buttonNumber = number;
  }

  public static double getX() {
    return x;
  }

  public static double getY() {
    return y;
  }
  
  public static Point getPoint() {
	  return new Point(x,y);
  }

  public static void setX(double X) {
    x = X;
  }

  public static void setY(double Y) {
    y = Y;
  }

  /**
  @return - the number of buttons on the user's mouse.
  */
  public static int getNumberOfButtons() {
    return maxButtons;
  }

  /**
  @return - the number of scrolling units most recently scrolled by the user.
  */
  public static int getWheelRotation() {
    return wheelRotation;
  }

  /**
  Changes the amount of units the user has "scrolled".
  @param amount - the amount of units the user has scrolled.
  */
  public static void setWheelRotation(int amount) {
    wheelRotation = amount;
  }
}