package me.vhoyd.GameEngine.model;

import javax.swing.JFrame;

  /**
  Class for managing the window of the game.
  */
public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GamePanel panel;

	public GameFrame(String name, GameModel model) {
		super(name);
		panel = new GamePanel(model);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public GamePanel getPanel() {
		return panel;
	}
	
	}