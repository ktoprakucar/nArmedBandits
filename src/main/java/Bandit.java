import Entity.Bag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toprak on 3/12/2017.
 */
public class Bandit {
    List<Double> estimateValues = new ArrayList<Double>();
    List<Bag> t = new ArrayList<Bag>();

    public int greedySelection(){
        Double bagWithGreatestEstimate = estimateValues.get(0);

        for(Double estimate : estimateValues){
            if(estimateValues.indexOf(estimate) == 0){
                bagWithGreatestEstimate = estimate;
                continue;
            }
            if(estimate > bagWithGreatestEstimate)
                bagWithGreatestEstimate = estimate;
        }
        return estimateValues.indexOf(bagWithGreatestEstimate);
    }
}
