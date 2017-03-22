import entity.Bag;
import entity.Ball;
import entity.EstimateValue;
import entity.PreferenceValue;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by toprak on 21-Mar-17.
 */
public class SimulatorTest {
  Bandit bandit;
  int bagNumber;
  double reward;
  int bag1Counter;
  int bag2Counter;
  int bag3Counter;
  int bag4Counter;

  @Before
  public void setUp() {
    bandit = new Bandit();
    Bag bag1 = new Bag(30, 10, 10);
    Bag bag2 = new Bag(1000, 30, 30);
    Bag bag3 = new Bag(20, 10, 10);
    Bag bag4 = new Bag(30, 20, 20);

    double alpha = 0.0001;

    EstimateValue value1 = new EstimateValue(alpha);
    EstimateValue value2 = new EstimateValue(alpha);
    EstimateValue value3 = new EstimateValue(alpha);
    EstimateValue value4 = new EstimateValue(alpha);

    PreferenceValue p1 = new PreferenceValue(0.5);
    p1.value = 0.25;
    PreferenceValue p2 = new PreferenceValue(0.5);
    p2.value = 0.25;
    PreferenceValue p3 = new PreferenceValue(0.5);
    p3.value = 0.25;
    PreferenceValue p4 = new PreferenceValue(0.5);
    p4.value = 0.25;


    bandit.bags.add(bag1);
    bandit.bags.add(bag2);
    bandit.bags.add(bag3);
    bandit.bags.add(bag4);

    bandit.estimateValues.add(value1);
    bandit.estimateValues.add(value2);
    bandit.estimateValues.add(value3);
    bandit.estimateValues.add(value4);

    bandit.preferenceValues.add(p1);
    bandit.preferenceValues.add(p2);
    bandit.preferenceValues.add(p3);
    bandit.preferenceValues.add(p4);

  }


  @Test
  public void test_simulator_with_epsilon_greedy() {
    double epsilon = 0.5;
    for (int i = 0; i < 5000; i++) {
      bagNumber = bandit.epsilonGreedySelection(epsilon);
      Ball pickedBall = bandit.selectBallRandomly(bagNumber);
      reward = bandit.useNoiseForRewarding(pickedBall.color);
      bandit.updateEstimateValue(bagNumber, reward);
      counter(bagNumber);
      //System.out.println(toString());
      //System.out.println(bandit.estimateValues.get(0).estimatedValue + "  " + bandit.estimateValues.get(1).estimatedValue + "  " + bandit.estimateValues.get(2).estimatedValue + "  " +bandit.estimateValues.get(3).estimatedValue);
      if(epsilon > 0.003)
        epsilon -= 0.00001;
    }
  }

  @Test
  public void test_simulator_optimal_with_initial_values() {
    assignInitialOptimalValues(5.0);
    double epsilon = 0.5;
    for (int i = 0; i < 5000; i++) {
      bagNumber = bandit.epsilonGreedySelection(epsilon);
      Ball pickedBall = bandit.selectBallRandomly(bagNumber);
      reward = bandit.useNoiseForRewarding(pickedBall.color);
      bandit.estimateValues.get(bagNumber).updateEstimateValue(reward);
      counter(bagNumber);
      //System.out.println("Bag Number: " + bagNumber + " ----- EstimateValue: " + bandit.estimateValues.get(bagNumber).estimatedValue);
      //System.out.println(toString());
      if(epsilon > 0.003)
        epsilon -= 0.00001;
    }
  }

  @Test
  public void test_simulator_with_reinforcement_comparison() {
    double epsilon = 0.35;
    for (int i = 0; i < 5000; i++) {
      bagNumber = bandit.epsilonReinforcementComparison(epsilon);
      Ball pickedBall = bandit.selectBallRandomly(bagNumber);
      reward = bandit.useNoiseForRewarding(pickedBall.color);
      bandit.preferenceValues.get(bagNumber).updatePreferenceValue(bandit.referenceReward, reward);
      bandit.updateReferenceReward(0.1, reward);
      counter(bagNumber);
      //System.out.println("Bag Number: " + bagNumber + " ----- PreferenceValue: " + bandit.preferenceValues.get(bagNumber).value + "---- epsilon: " + epsilon);
      //System.out.println(toString());
      if (epsilon > 0.003)
        epsilon -= 0.0000001;
    }
  }

  @Test
  public void test_simulator_with_pursuit_methods(){
    double epsilon = 0.35;
    double beta = 0.01;
    for(int i=0; i<5000;i++){
      bagNumber = bandit.epsilonReinforcementComparison(epsilon);
      Ball pickedBall = bandit.selectBallRandomly(bagNumber);
      reward = bandit.useNoiseForRewarding(pickedBall.color);
      bandit.estimateValues.get(bagNumber).updateEstimateValueForPursuitMethods(reward);
      int greatestBagId = bandit.greedySelection();
      bandit.updateActionPreferenceForPursuitMethods(greatestBagId, beta, reward);
      counter(bagNumber);
      //System.out.println("Bag Number: " + bagNumber + " ----- PreferenceValue: " + bandit.preferenceValues.get(bagNumber).value + "---- EstimateValue: " + bandit.estimateValues.get(bagNumber).estimatedValue);
      //System.out.println(toString());
      if(epsilon < 0.003)
        epsilon -= 0.0001;
    }
  }

  private void assignInitialOptimalValues(double estimateValue) {
    for (EstimateValue value : bandit.estimateValues) {
      value.estimatedValue = estimateValue;
    }
  }

  private void counter(int index) {
    if (index == 0)
      bag1Counter++;
    else if (index == 1)
      bag2Counter++;
    else if (index == 2)
      bag3Counter++;
    else if (index == 3)
      bag4Counter++;
  }


  @Override
  public String toString() {
    return "SimulatorTest{" +
            "bag1Counter=" + bag1Counter +
            ", bag2Counter=" + bag2Counter +
            ", bag3Counter=" + bag3Counter +
            ", bag4Counter=" + bag4Counter +
            '}';
  }
}
