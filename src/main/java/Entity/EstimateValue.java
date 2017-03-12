package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toprak on 3/12/2017.
 */
public class EstimateValue {
    public List<Double> estimateValueList = new ArrayList<Double>();
    public double estimateValue = 0.0;
    public double alpha;

    public EstimateValue(double alpha) {
        this.alpha = alpha;
    }

    public void updateEstimateValue(double reward){
        estimateValueList.add(reward);
        estimateValue += alpha * ( reward - estimateValue);
    }

}
