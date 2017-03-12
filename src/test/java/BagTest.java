import Entity.Bag;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by toprak on 3/12/2017.
 */
public class BagTest {

    @Before
    public void setUp() {
    }

    @Test
    public void test_add_balls() {
        Bag bag = new Bag(2, 3, 4);
        assertEquals(2, bag.redBalls);
        assertEquals(3, bag.yellowBalls);
        assertEquals(4, bag.blueBalls);
        assertEquals(9, bag.totalBalls);

    }
}
