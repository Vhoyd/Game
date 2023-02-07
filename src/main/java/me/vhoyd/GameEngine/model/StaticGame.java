package me.vhoyd.GameEngine.model;

import me.vhoyd.GameEngine.entity.Player;

public final class StaticGame {
  private static GameModel model;
  private static Player player;
  public static final boolean renderBoundingBoxes = false;
  

  private StaticGame() {}
  
  public static void setGameModel(GameModel model) {
    StaticGame.model = model;
  }

  public static GameModel getModel() {
    return model;
  }


  public static Player getPlayer() {
    return player;
  }
  
  public static void setPlayer(Player player) {
	  StaticGame.player = player;
  }
}