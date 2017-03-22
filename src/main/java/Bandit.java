import entity.Bag;
import entity.Ball;
import entity.EstimateValue;
import entity.PreferenceValue;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by toprak on 3/12/2017.
 */
public class Bandit {
  List<EstimateValue> estimateValues = new ArrayList<EstimateValue>();
  List<PreferenceValue> preferenceValues = new ArrayList<PreferenceValue>();
  List<Double> rewards = new ArrayList<Double>();
  Random generator = new Random();
  List<Bag> bags = new ArrayList<Bag>();
  double noise = 0.0;
  double referenceReward = 0.0;
  boolean isFirst = true;

  public int greedySelection() {
    List<EstimateValue> maxValues = new ArrayList<EstimateValue>();

    for (EstimateValue estimate : estimateValues) {
      if (estimateValues.indexOf(estimate) == 0) {
        maxValues.add(estimateValues.get(0));
        continue;
      } else if (estimate.estimatedValue == maxValues.get(0).estimatedValue)
        maxValues.add(estimate);
      else if (estimate.estimatedValue > maxValues.get(0).estimatedValue) {
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

  public int epsilonGreedySelection(double epsilon) {
    int indexOfGreatestValue = greedySelection();
    Random randomizer = new Random();
    double number = randomizer.nextDouble();
    if (number < 1 - epsilon)
      return indexOfGreatestValue;
    else {
      List<EstimateValue> exploratoryValues = new ArrayList<EstimateValue>();
      for (EstimateValue value : estimateValues) {
        if (value.estimatedValue < estimateValues.get(indexOfGreatestValue).estimatedValue || areAllEqual("estimate"))
          exploratoryValues.add(value);
      }
      isFirst = false;
      int randomEstimateIndex = generator.nextInt((exploratoryValues.size() - 1) - 0 + 1) + 0;
      return estimateValues.indexOf(exploratoryValues.get(randomEstimateIndex));
    }
  }

  public int reinforcementComparisonSelection() {
    List<PreferenceValue> maxValues = new ArrayList<PreferenceValue>();

      for (PreferenceValue preferenceValue : preferenceValues) {
        if (preferenceValues.indexOf(preferenceValue) == 0) {
          maxValues.add(preferenceValue);
          continue;
        } else if (Math.pow(Math.E, preferenceValue.value)  == Math.pow(Math.E, maxValues.get(0).value) )
          maxValues.add(preferenceValue);
        else if (Math.pow(Math.E, preferenceValue.value)  > Math.pow(Math.E, maxValues.get(0).value) ) {
          maxValues.clear();
          maxValues.add(preferenceValue);
        }
      }

      if (maxValues.size() == 1)
        return preferenceValues.indexOf(maxValues.get(0));
      else {
        int randomEstimateIndex = generator.nextInt((maxValues.size() - 1) - 0 + 1) + 0;
        return preferenceValues.indexOf(maxValues.get(randomEstimateIndex));
      }
  }

  public int epsilonReinforcementComparison(double epsilon) {
    int indexOfGreatestValue = reinforcementComparisonSelection();
    Random randomizer = new Random();
    double number = randomizer.nextDouble();
    if (number < 1 - epsilon)
      return indexOfGreatestValue;
    else {
      List<PreferenceValue> exploratoryValues = new ArrayList<PreferenceValue>();
      for (PreferenceValue preferenceValue : preferenceValues) {
        if (preferenceValue.value < preferenceValues.get(indexOfGreatestValue).value || areAllEqual("preference"))
          exploratoryValues.add(preferenceValue);
      }
      isFirst = false;
      int randomEstimateIndex = generator.nextInt((exploratoryValues.size() - 1) - 0 + 1) + 0;
      return preferenceValues.indexOf(exploratoryValues.get(randomEstimateIndex));
    }
  }

  private boolean areAllEqual(String valueName) {
    if("preference".equalsIgnoreCase(valueName)) {
      PreferenceValue firstValue = preferenceValues.get(0);
      for (PreferenceValue preferenceValue : preferenceValues) {
        if (preferenceValue.value != firstValue.value)
          return false;
      }
      return true;
    } else {
      EstimateValue firstValue = estimateValues.get(0);
      for (EstimateValue estimateValue : estimateValues) {
        if (estimateValue.estimatedValue != firstValue.estimatedValue)
          return false;
      }
      return true;
    }
  }
  public void updateEstimateValue(int bagId, double reward) {
    EstimateValue estimateValue = estimateValues.get(bagId);
    estimateValue.updateEstimateValue(reward);
  }

  public Ball selectBallRandomly(int i) {
    Bag bag = bags.get(i);
    int randomBallIndex = generator.nextInt((bag.balls.size() - 1) - 0 + 1) + 0;
    return bag.balls.get(randomBallIndex);
  }

  public double useNoiseForRewarding(Color color) {
    if (color.equals(Color.RED)) {
      rewards.add(1 - noise);
      return 1 - noise;
    }
    rewards.add(noise);
    return noise;
  }

  public void initializePreferenceValues() {
    double initalValue = 1.00 / bags.size();
    for (PreferenceValue value : preferenceValues)
      value.value = initalValue;
  }

  public void updateReferenceReward(double alpha, double reward) {
    referenceReward = +referenceReward + alpha * (reward - referenceReward);
  }

  public void updateActionPreferenceForPursuitMethods(int bagNumber, double beta, double reward) {
  for(PreferenceValue preferenceValue: preferenceValues){
    if(preferenceValues.indexOf(preferenceValue)==bagNumber)
      preferenceValue.value += beta * (reward - preferenceValue.value);
    else
      preferenceValue.value += beta * (0 - preferenceValue.value);
  }
  }
}
