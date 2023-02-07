package me.vhoyd.GameEngine;

import java.io.File;

import me.vhoyd.GameEngine.entity.Entity;
import me.vhoyd.GameEngine.entity.Player;
import me.vhoyd.GameEngine.model.GameModel;
import me.vhoyd.GameEngine.physics.Vector;

class Main {
  private static GameModel model;
  
  public static void main(String[] args) throws Exception {
    model = new GameModel();
    Player player = new Player(new File("images\\unknown.png"), model.getPanel());
    player.setX(10);
    player.setY(10);
    Entity test = new Entity(new File("images\\unknown.png"), model.getPanel());
    player.setName("player");
    test.setX(100);
    test.setY(100);
    test.setVelocity(new Vector(2,2));
    player.setPriority(1);
    player.setInAir(true);
    test.setPriority(0);
    test.scalePercent(10, 10);
    player.scalePercent(10, 10);
    // Circle c = new Circle();
    // TextPanel text = new TextPanel("test", 100, 100);
    // text.setPriority(3);
    // while (true) {
    //   text.setText("X:"+((int)(player.getX()*10)/10)+"  Y:"+((int)(player.getY()*10)/10));
    // }
    model.setRunning(true);
  }
}