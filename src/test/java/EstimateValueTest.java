import entity.EstimateValue;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by toprak on 20-Mar-17.
 */
public class EstimateValueTest {
  double alpha = 0.5;
  EstimateValue value = new EstimateValue(alpha);

  @Test
  public void test_update_estimate_value(){
    value.updateEstimateValue(1);
    assertEquals(0.5, value.estimatedValue);
    assertEquals(1, value.estimateValueList.size());
  }
}
