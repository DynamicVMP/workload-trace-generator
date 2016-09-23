package taxonomy;

import distributions.DistributionInterface;

/**
 * Created by augusto on 9/17/16.
 */
public abstract class TaxonomyVariations {
    protected DistributionInterface distribution;
    protected String distributionType;

    public DistributionInterface getDistribution() {
        return distribution;
    }

    public void setDistribution(DistributionInterface distribution) {
        this.distribution = distribution;
    }

    public abstract String toString();
}
