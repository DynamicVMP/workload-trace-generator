package taxonomy;

import distributions.PoissonDistribution;
import distributions.UniformDistribution;

/**
 * Created by augusto on 9/17/16.
 */
public class VerticalElasticity extends TaxonomyVariations {

    public VerticalElasticity(Integer floor, Integer ceiling) {
        this.distribution = new UniformDistribution(floor, ceiling);
        this.distributionType = "UNIFORM";
    }

    public VerticalElasticity(float lambda) {
        this.distribution = new PoissonDistribution(lambda);
        this.distributionType = "POISSON";
    }

    public VerticalElasticity(VerticalElasticityConfiguration verticalElasticityConfiguration) {
        if ( "UNIFORM".equals(verticalElasticityConfiguration.getDistributionType()) ) {
            this.distribution = new UniformDistribution(verticalElasticityConfiguration.getFloor(), verticalElasticityConfiguration.getCeiling());
        } else {
            this.distribution = new PoissonDistribution(verticalElasticityConfiguration.getLambda());
        }
        this.distributionType = verticalElasticityConfiguration.getDistributionType();
    }

    public String toString() {
        if ("UNIFORM".equals(this.distributionType)) {
            return "VertElast[" + this.distributionType + " - (floor: " + ((UniformDistribution)distribution).getFloor() +
                    ", ceiling: " + ((UniformDistribution)distribution).getCeiling() + ")";
        } else if ("POISSON".equals(this.distributionType)) {
            return "VertElast[" + this.distributionType + " - (lambda: " +
                    ((PoissonDistribution)distribution).getLambda() + ")";
        } else {
            return "VertElast[UNKNOWN DISTRIBUTION TYPE]";
        }
    }

}
