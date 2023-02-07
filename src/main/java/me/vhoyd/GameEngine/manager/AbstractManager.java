package me.vhoyd.GameEngine.manager;

import java.util.HashSet;
import java.util.Set;

import me.vhoyd.GameEngine.model.GamePanel;

public class AbstractManager<T> {
  
  protected Set<T> contents;
  protected GamePanel panel;

  protected AbstractManager(GamePanel panel) {
    contents = new HashSet<T>();
    this.panel = panel;
  }

  public void add(T object) {
    contents.add(object);
  }

  public void remove(T object) {
    contents.remove(object);
  }
  
  public Set<T> getContents() {
	  return contents;
  }

  public int size() {
    return contents.size();
  }

  public boolean contains(T object) {
    return contents.contains(object);
  }

  public GamePanel getPanel() {
    return panel;
  }

}