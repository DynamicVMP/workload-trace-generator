package taxonomy;

import distributions.DistributionInterface;
import distributions.PoissonDistribution;
import distributions.UniformDistribution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by augusto on 9/17/16.
 */
public class HorizontalElasticity extends TaxonomyVariations {
    private int serviceNumber;

    public HorizontalElasticity(Integer floor, Integer ceiling, int numberOfServices, int timeFrame) {
        this.serviceNumber = numberOfServices;

        this.distribution = new UniformDistribution(floor, ceiling, timeFrame);

        this.distributionType = "UNIFORM";
    }

    public HorizontalElasticity(float lambda, int serviceNumber, int timeframe) {
        this.distribution = new PoissonDistribution(lambda, timeframe);

        this.distributionType = "POISSON";
    }

    public String toString() {
        if ("UNIFORM".equals(distributionType)) {
            return "HorzElast[" + distributionType + "] - m: " + serviceNumber +
                    " - (floor: " + ((UniformDistribution)distribution).getFloor() +
                    ", ceiling: " + ((UniformDistribution)distribution).getCeiling() + ")";
        } else if ("POISSON".equals(distributionType)) {
            return "HorzElast[" + distributionType + "] - m: " + serviceNumber +
                    " - (lambda: " + ((PoissonDistribution)distribution).getLambda() + ")";
        } else {
            return "HorzElast[UNKNOWN DISTRIBUTION TYPE] - m: " + serviceNumber;
        }
    }

}
