import entity.Bag;
import entity.Ball;
import entity.EstimateValue;
import entity.PreferenceValue;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertNotEquals;
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
  public void test_selection_with_epsilon_greedy_with_0(){
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

    int index = bandit.epsilonGreedySelection(0);
    assertEquals(index, 3);

  }

  @Test
  public void test_selection_with_epsilon_greedy_with_1(){
    bandit.isFirst = false;
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

    int index = bandit.epsilonGreedySelection(1);
    assertNotEquals(index, 3);
    System.out.println(index);

  }

  @Test
  public void test_random(){
    Random randomizer = new Random();
    double number = randomizer.nextDouble();
    System.out.println(number);
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

  @Test
  public void test_initialize_preference_values(){
    Bag bag1 = new Bag(50000, 10, 10);
    Bag bag2 = new Bag(10, 30, 30);
    Bag bag3 = new Bag(50, 10, 10);
    Bag bag4 = new Bag(2000, 20, 20);

    PreferenceValue value1 = new PreferenceValue(0.5);
    PreferenceValue value2 = new PreferenceValue(0.5);
    PreferenceValue value3 = new PreferenceValue(0.5);
    PreferenceValue value4 = new PreferenceValue(0.5);

    bandit.preferenceValues.add(value1);
    bandit.preferenceValues.add(value2);
    bandit.preferenceValues.add(value3);
    bandit.preferenceValues.add(value4);

    double alpha = 0.0001;

    bandit.bags.add(bag1);
    bandit.bags.add(bag2);
    bandit.bags.add(bag3);
    bandit.bags.add(bag4);

    bandit.initializePreferenceValues();
    for(PreferenceValue value : bandit.preferenceValues){
      assertEquals(value.value, 0.25);
    }
  }

  @Test
  public void test_update_reference_reward(){
    double alpha = 0.5;
    double reward = 1.0;
    bandit.updateReferenceReward(alpha, reward);
    System.out.println(bandit.referenceReward);
  }

  @Test
  public void test_reinforcement_comparison_selection(){
    PreferenceValue value1 = new PreferenceValue(0.5);
    value1.value = 0.2;
    PreferenceValue value2 = new PreferenceValue(0.5);
    value2.value = 0.3;
    PreferenceValue value3 = new PreferenceValue(0.5);
    value3.value = 0.4;
    PreferenceValue value4 = new PreferenceValue(0.5);
    value4.value = 0.5;

    bandit.preferenceValues.add(value1);
    bandit.preferenceValues.add(value2);
    bandit.preferenceValues.add(value3);
    bandit.preferenceValues.add(value4);

    int index = bandit.reinforcementComparisonSelection();
    assertEquals(3, index);

  }

}
