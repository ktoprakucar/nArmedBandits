import Entity.EstimateValue;
import org.junit.Before;
import org.junit.Test;

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
    e1.estimateValue = 4.0;

    EstimateValue e2 = new EstimateValue(0.0);
    e2.estimateValue = 5.0;

    EstimateValue e3 = new EstimateValue(0.0);
    e3.estimateValue = 6.0;

    EstimateValue e4 = new EstimateValue(0.0);
    e4.estimateValue = 7.0;

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
    e1.estimateValue = 4.0;

    EstimateValue e2 = new EstimateValue(0.0);
    e2.estimateValue = 4.0;

    EstimateValue e3 = new EstimateValue(0.0);
    e3.estimateValue = 4.0;

    EstimateValue e4 = new EstimateValue(0.0);
    e4.estimateValue = 7.0;

    EstimateValue e5 = new EstimateValue(0.0);
    e5.estimateValue = 7.0;

    EstimateValue e6 = new EstimateValue(0.0);
    e6.estimateValue = 7.0;

    EstimateValue e7 = new EstimateValue(0.0);
    e7.estimateValue = 6.0;

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
}
