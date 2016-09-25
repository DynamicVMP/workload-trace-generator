package generator.configurations;

import utils.Constants;

/**
 * Created by augusto on 9/24/16.
 */
public class ElasticityConfiguration {

    private String distributionType;
    private Integer floor;
    private Integer ceiling;
    private Float lambda;

    public ElasticityConfiguration(Integer floor, Integer ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;

        this.distributionType = Constants.UNIFORM;
    }

    public ElasticityConfiguration(Float lambda) {
        this.lambda = lambda;

        this.distributionType = Constants.POISSON;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public Integer getFloor() {
        return floor;
    }

    public Integer getCeiling() {
        return ceiling;
    }

    public Float getLambda() {
        return lambda;
    }
}
