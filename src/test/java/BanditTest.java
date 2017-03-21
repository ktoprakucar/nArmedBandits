import entity.Bag;
import entity.Ball;
import entity.EstimateValue;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertThat;

/**
 * Created by toprak on 3/12/2017.
 */
public class BanditTest {
  Bandit bandit;

  @Before
  public void setUp() {
    bandit = new Bandit();
  }

  @Test
  public void test_choose_highest_rewarded_bag_with_different_values() {
    EstimateValue e1 = new EstimateValue(0.0);
    e1.estimatedValue = 4.0;

    EstimateValue e2 = new EstimateValue(0.0);
    e2.estimatedValue = 5.0;

    EstimateValue e3 = new EstimateValue(0.0);
    e3.estimatedValue = 6.0;

    EstimateValue e4 = new EstimateValue(0.0);
    e4.estimatedValue = 7.0;

    bandit.estimateValues.add(e1);
    bandit.estimateValues.add(e2);
    bandit.estimateValues.add(e3);
    bandit.estimateValues.add(e4);

    int index = bandit.greedySelection();
    assertEquals(3, index);
  }

  @Test
  public void test_choose_highest_rewarded_bag_with_replicated_values() {
    EstimateValue e1 = new EstimateValue(0.0);
    e1.estimatedValue = 4.0;

    EstimateValue e2 = new EstimateValue(0.0);
    e2.estimatedValue = 4.0;

    EstimateValue e3 = new EstimateValue(0.0);
    e3.estimatedValue = 4.0;

    EstimateValue e4 = new EstimateValue(0.0);
    e4.estimatedValue = 7.0;

    EstimateValue e5 = new EstimateValue(0.0);
    e5.estimatedValue = 7.0;

    EstimateValue e6 = new EstimateValue(0.0);
    e6.estimatedValue = 7.0;

    EstimateValue e7 = new EstimateValue(0.0);
    e7.estimatedValue = 6.0;

    bandit.estimateValues.add(e1);
    bandit.estimateValues.add(e2);
    bandit.estimateValues.add(e3);
    bandit.estimateValues.add(e4);
    bandit.estimateValues.add(e5);
    bandit.estimateValues.add(e6);
    bandit.estimateValues.add(e7);

    int index = bandit.greedySelection();
    assertThat(index, anyOf(is(3), is(4), is(5)));
  }

  @Test
  public void test_select_ball_from_bag_randomly() {
    Bag bag = new Bag(10, 20, 30);
    bandit.bags.add(bag);
    Ball ball = bandit.selectBallRandomly(0);
    System.out.println(ball.color);
  }

  @Test
  public void test_use_noise_for_red_color() {
    bandit.noise = 0.4;
    double reward = bandit.useNoiseForRewarding(Color.red);
    assertEquals(0.6, reward);
  }

  @Test
  public void test_use_noise_for_other_colors() {
    bandit.noise = 0.4;
    double reward = bandit.useNoiseForRewarding(Color.blue);
    assertEquals(0.4, reward);
  }


}
