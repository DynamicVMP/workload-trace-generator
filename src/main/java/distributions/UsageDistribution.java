package distributions;

import cern.jet.random.Poisson;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;
import generator.UsageConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by augusto on 9/18/16.
 */
public class UsageDistribution implements DistributionInterface{

    private int lifespan;
    private List<Integer> series;
    private UsageConfiguration usageConfiguration;

    public UsageDistribution(UsageConfiguration usageDistConf, int lifespan) {
        this.lifespan = lifespan;
        this.usageConfiguration = usageDistConf;

        if ("UNIFORM".equals(usageDistConf.getDistributionType())) {
            this.series = new UniformDistribution(usageDistConf.getFloor(), usageDistConf.getCeiling(), lifespan).getSeries();
        } else if ("POISSON".equals(usageDistConf.getDistributionType())) {
            this.series = new PoissonDistribution(usageDistConf.getLambda(), lifespan).getSeries();
        }
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    @Override
    public List<Integer> getSeries() {
        return series;
    }

    public void setSeries(List<Integer> series) {
        this.series = series;
    }
}
