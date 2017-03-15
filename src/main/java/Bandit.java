import Entity.Bag;
import Entity.EstimateValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by toprak on 3/12/2017.
 */
public class Bandit {
  List<EstimateValue> estimateValues = new ArrayList<EstimateValue>();
  Random generator = new Random();
  List<Bag> bags = new ArrayList<Bag>();
  double alpha = 0.0;

  public int greedySelection() {
    List<EstimateValue> maxValues = new ArrayList<EstimateValue>();

    for (EstimateValue estimate : estimateValues) {
      if (estimateValues.indexOf(estimate) == 0) {
        maxValues.add(estimateValues.get(0));
        continue;
      } else if (estimate.estimateValue == maxValues.get(0).estimateValue)
        maxValues.add(estimate);
      else if (estimate.estimateValue > maxValues.get(0).estimateValue) {
        maxValues.clear();
        maxValues.add(estimate);
      }
    }
    if (maxValues.size() == 1)
      return estimateValues.indexOf(maxValues.get(0));
    else {
      int randomEstimateIndex = generator.nextInt((maxValues.size() - 1) - 0 + 1) + 0;
      return estimateValues.indexOf(maxValues.get(randomEstimateIndex));
    }
  }

  public void updateEstimateValue(int bagId, double reward) {
    EstimateValue estimateValue = estimateValues.get(bagId);
    estimateValue.updateEstimateValue(reward);

  }
}
