import entity.Bag;
import entity.Ball;
import entity.EstimateValue;
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
    Bag bag1 = new Bag(5000, 10, 10);
    Bag bag2 = new Bag(10, 30, 30);
    Bag bag3 = new Bag(50, 10, 10);
    Bag bag4 = new Bag(2000, 20, 20);

    double alpha = 0.0001;

    EstimateValue value1 = new EstimateValue(alpha);
    EstimateValue value2 = new EstimateValue(alpha);
    EstimateValue value3 = new EstimateValue(alpha);
    EstimateValue value4 = new EstimateValue(alpha);

    bandit.bags.add(bag1);
    bandit.bags.add(bag2);
    bandit.bags.add(bag3);
    bandit.bags.add(bag4);

    bandit.estimateValues.add(value1);
    bandit.estimateValues.add(value2);
    bandit.estimateValues.add(value3);
    bandit.estimateValues.add(value4);

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

  @Test
  public void test_epsilon_greedy_simulator() {
    for (int i = 0; i < 5000; i++) {
      bagNumber = bandit.epsilonGreedySelection(0.5);
      Ball pickedBall = bandit.selectBallRandomly(bagNumber);
      reward = bandit.useNoiseForRewarding(pickedBall.color);
      bandit.estimateValues.get(bagNumber).updateEstimateValue(reward);
      counter(bagNumber);
      //System.out.println("Bag Number: " + bagNumber + " ----- EstimateValue: " + bandit.estimateValues.get(bagNumber).estimatedValue);
      //System.out.println(toString());
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
}
