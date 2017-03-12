import Entity.EstimateValue;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by toprak on 3/12/2017.
 */
public class BanditTest {
    Bandit bandit;

    @Before
    public void setUp() {
        bandit = new Bandit();
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
    }

    @Test
    public void test_choose_highest_rewarded_bag() {
        int index = bandit.greedySelection();
        assertEquals(3, index);
    }
}
