import entity.Bag;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by toprak on 3/12/2017.
 */
public class BagTest {
    Bag bag;

    @Before
    public void setUp() {
    }

    @Test
    public void test_generate_bag(){
        bag = new Bag(10,20,30);
        assertEquals(60, bag.balls.size());
    }

}
