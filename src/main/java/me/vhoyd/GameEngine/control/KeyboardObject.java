package me.vhoyd.GameEngine.control;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
Static class for tracking information about the user's actions involving the ketboard. Changing values about this class does not affect anything physically about the keyboard, just leads to innacurate information until the next time the user does something with the keyboard.
*/
public class KeyboardObject {
  private static Set<Integer> keysPressed = new HashSet<Integer>();

  private KeyboardObject() {}

  /**
  @return - a Set of all keyboard keys currently being held down.
  */
  public static Set<Integer> getKeysPressed() {
    return keysPressed;
  }

  /**
  Adds a new key to be read as being pressed. NOTE - KeyEvent key integer values are NOT the same as the key's ASCII value.
  @param key - the key that is being "pressed".
  */
  public static void addKey(int key) {
    keysPressed.add(key);
  }

  /**
  Removes a key from the Set of pressed keys. NOTE - KeyEvent key integer values are NOT the same as the key's ASCII value.
  @param key - the key that was "released".
  */
  public static void removeKey(int key) {
    keysPressed.remove(key);
  }

  /**
  @return - Whether the given key was pressed or not. @param key - the key to evaluate the state of. NOTE - KeyEvent key integer values are NOT the same as the key's ASCII values.
  
  */
  public static boolean isKeyPressed(int key) {
    return keysPressed.contains(key);
  }

  /**
  @param key - the unicode character representing the key being tested.
  @return - whether the given character is being pressed down.
  */
  public static boolean isKeyPressed(char key) {
    return keysPressed.contains(KeyEvent.getExtendedKeyCodeForChar(key));
  }

  /**
  Checks if a "special" key, such as Shift, Enter, or Tab, is being pressed down.
  @param key - the {@link SpecialKey} being tested.
  @return - whether the given special key is being pressed down.
  */
  public static boolean isSpecialKeyPressed(SpecialKey key) {
    return isKeyPressed(key.getCode());
  }
  
  public static boolean areKeysPressed(char... keys) {
	  for (char k : keys) {
		  if (!isKeyPressed(k)) return false;
	  }
	  return true;
  }

  /**
  Enum for storing convenience values for special keys.
  */
  public static enum SpecialKey {
    SHIFT(KeyEvent.VK_SHIFT),
    ALT(KeyEvent.VK_ALT),
    CONTROL(KeyEvent.VK_CONTROL),
    TAB(KeyEvent.VK_TAB),
    ESCAPE(KeyEvent.VK_ESCAPE),
    BACKSPACE(KeyEvent.VK_BACK_SPACE),
    ENTER(KeyEvent.VK_ENTER),
    SPACE(KeyEvent.VK_SPACE),
    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT),
    ;//add function keys
    private int code;

    private SpecialKey(int value) {
      code = value;
    } 

    private int getCode() {
      return code;
    }
  }
}