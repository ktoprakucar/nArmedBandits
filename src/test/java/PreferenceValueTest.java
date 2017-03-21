import entity.PreferenceValue;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by toprak on 21-Mar-17.
 */
public class PreferenceValueTest {
  double beta = 0.5;
  PreferenceValue preferenceValue = new PreferenceValue(beta);

  @Test
  public void test_update_preference_value(){
    preferenceValue.updatePreferenceValue(0.6, 1);
    assertEquals(0.2, preferenceValue.value);
  }
}
