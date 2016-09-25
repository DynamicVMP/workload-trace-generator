package taxonomy;

import distributions.PoissonDistribution;
import generator.configurations.ElasticityConfiguration;
import intanceTypes.InstanceManager;
import intanceTypes.InstanceType;
import utils.Constants;

/**
 * Created by augusto on 9/24/16.
 */
public class VerticalTaxonomy extends TaxonomyAbstract {

    /**
     * <p>The lambda value cannot be bigger than the number of instance types the generator supports</p>
     * <p>Currently the generator supports: 11</p>
     * @param configuration
     */
    public VerticalTaxonomy(ElasticityConfiguration configuration) {
        super(configuration);

        if (Constants.POISSON.equals(configuration.getDistributionType()) && configuration.getLambda().compareTo(10F) > 0) {
            distribution = new PoissonDistribution(10F);
        }
    }

    public InstanceType getNextInstanceType() {
        Integer index = this.distribution.getNextValue();
        while (index >= InstanceManager.getInstance().getInstanceTypeQuantity()){
            index = this.distribution.getNextValue();
        }
        return InstanceManager.getInstance().getInstanceType(index);
    }
}
