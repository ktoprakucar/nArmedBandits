package entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by toprak on 3/12/2017.
 */
public class Bag {
  public List<Ball> balls = new ArrayList<Ball>();

  public Bag(int redBalls, int yellowBalls, int blueBalls) {
    this.balls.addAll(addBall(redBalls, Color.RED));
    this.balls.addAll(addBall(yellowBalls, Color.YELLOW));
    this.balls.addAll(addBall(blueBalls, Color.BLUE));
  }

  private List<Ball> addBall(int amount, Color color) {
    List<Ball> newBalls = new ArrayList<Ball>();
    for(int i = 0; i < amount; i++){
      newBalls.add(new Ball(color));
    }
    return newBalls;
  }
}
