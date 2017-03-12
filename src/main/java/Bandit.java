import Entity.Bag;
import Entity.EstimateValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toprak on 3/12/2017.
 */
public class Bandit {
    List<EstimateValue> estimateValues = new ArrayList<EstimateValue>();
    List<Bag> bags = new ArrayList<Bag>();
    double alpha = 0.0;

    public int greedySelection(){
        EstimateValue bagWithGreatestEstimate = estimateValues.get(0);

        for(EstimateValue estimate : estimateValues){
            if(estimateValues.indexOf(estimate) == 0){
                bagWithGreatestEstimate = estimate;
                continue;
            }
            if(estimate.estimateValue > bagWithGreatestEstimate.estimateValue)
                bagWithGreatestEstimate = estimate;
        }
        return estimateValues.indexOf(bagWithGreatestEstimate);
    }

    public void updateEstimateValue(){

    }
}
