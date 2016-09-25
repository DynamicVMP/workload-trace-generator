package taxonomy;

import generator.configurations.ElasticityConfiguration;

/**
 * Created by augusto on 9/24/16.
 */
public class OverbookingDistribution extends ElasticityAbstract{

    public OverbookingDistribution(ElasticityConfiguration configuration) {
        super(configuration);
    }

    public Integer getNextUsagePercentage() {
        return this.distribution.getNextValue();
    }
}
