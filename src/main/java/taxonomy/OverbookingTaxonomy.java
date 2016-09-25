package taxonomy;

import generator.configurations.ElasticityConfiguration;

/**
 * Created by augusto on 9/24/16.
 */
public class OverbookingTaxonomy extends TaxonomyAbstract {

    public OverbookingTaxonomy(ElasticityConfiguration configuration) {
        super(configuration);
    }

    public Integer getNextUsagePercentage() {
        return this.distribution.getNextValue();
    }
}
