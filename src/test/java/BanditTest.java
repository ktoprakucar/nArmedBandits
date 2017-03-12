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
        bandit.estimateValues.add(4.0);
        bandit.estimateValues.add(5.0);
        bandit.estimateValues.add(6.0);
        bandit.estimateValues.add(7.0);
    }

    @Test
    public void test_choose_highest_rewarded_bag() {
        int index = bandit.greedySelection();
        assertEquals(3, index);
    }
}
