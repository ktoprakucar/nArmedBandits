package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toprak on 21-Mar-17.
 */
public class PreferenceValue {
  public List<Double> valueList;
  public double value;
  public double beta;

  public PreferenceValue(double beta) {
    valueList = new ArrayList<Double>();
    this.beta = beta;
  }

  public void updatePreferenceValue(double referenceReward, double reward){
    valueList.add(value);
    value =+ value + beta*(reward-referenceReward);
  }
}
