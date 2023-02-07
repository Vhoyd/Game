package me.vhoyd.GameEngine.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import me.vhoyd.GameEngine.control.KeyHandler;
import me.vhoyd.GameEngine.control.MouseHandler;

  /**
  Class for managing visual parts of the game.
  */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private KeyHandler keyHandler = new KeyHandler();
	private MouseHandler mouseHandler = new MouseHandler();
	private GameModel model;

  public GamePanel(GameModel model) {
    addKeyListener(keyHandler);
    addMouseListener(mouseHandler);
    addMouseWheelListener(mouseHandler);
    addMouseMotionListener(mouseHandler);
    setFocusable(true);
    setBackground(Color.DARK_GRAY);
    this.model = model;
  }

  /**
  For internal use only.
  */
  @Override
  @Deprecated
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    model.getDrawingManager().repaintAll(g2);
    g2.scale(10,10);
  }
  
  /**
  Repaints all drawable objects onto the given Graphics2D component.
  @param g - the Graphics2D surface to paint to.
  */
  public void paint(Graphics2D g) {
    super.paint(g);
    model.getDrawingManager().repaintAll(g);
  }

  public GameModel getModel() {
    return model;
  }
}