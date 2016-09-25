package taxonomy;

import distributions.DistributionInterface;
import distributions.PoissonDistribution;
import distributions.UniformDistribution;
import generator.configurations.ElasticityConfiguration;
import utils.Constants;

/**
 * Created by augusto on 9/24/16.
 */
abstract class TaxonomyAbstract {
    protected DistributionInterface distribution;
    protected String distributionType;

    protected TaxonomyAbstract(ElasticityConfiguration configuration) {
        this.distributionType = configuration.getDistributionType();
        if (Constants.UNIFORM.equals(distributionType)) {
            this.distribution = new UniformDistribution(configuration.getFloor(), configuration.getCeiling());
        } else if (Constants.POISSON.equals(distributionType)) {
            this.distribution = new PoissonDistribution(configuration.getLambda());
        }
    }

    @Override
    public String toString() {
        if (Constants.UNIFORM.equals(distributionType)) {
            return this.getClass().getSimpleName() + "[" + distributionType + "]" +
                    " - (floor: " + ((UniformDistribution)distribution).getFloor() +
                    ", ceiling: " + ((UniformDistribution)distribution).getCeiling() + ")";
        } else if (Constants.POISSON.equals(distributionType)) {
            return this.getClass().getSimpleName() + "[" + distributionType + "]" +
                    " - (lambda: " + ((PoissonDistribution)distribution).getLambda() + ")";
        } else {
            return this.getClass().getSimpleName() + "[UNKNOWN DISTRIBUTION TYPE] - m:";
        }
    }
}
