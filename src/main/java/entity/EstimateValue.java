package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toprak on 3/12/2017.
 */
public class EstimateValue {
    public List<Double> estimateValueList;
    public double estimatedValue;
    public double alpha;

    public EstimateValue(double alpha) {
        this.alpha = alpha;
        estimateValueList = new ArrayList<Double>();
    }

    public void updateEstimateValue(double reward){
        estimateValueList.add(reward);
        estimatedValue += alpha * ( reward - estimatedValue);
    }

}
