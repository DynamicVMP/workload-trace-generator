package generator.configurations;

import utils.Constants;

/**
 * Created by augusto on 9/18/16.
 */
public class UsageConfiguration {

    private Integer floor;
    private Integer ceiling;
    private Float lambda;
    private String distributionType;

    public UsageConfiguration(Integer floor, Integer ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
        this.distributionType = Constants.UNIFORM;
    }

    public UsageConfiguration(float lambda) {
        this.lambda = lambda;
        this.distributionType = Constants.POISSON;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getCeiling() {
        return ceiling;
    }

    public void setCeiling(Integer ceiling) {
        this.ceiling = ceiling;
    }

    public Float getLambda() {
        return lambda;
    }

    public void setLambda(Float lambda) {
        this.lambda = lambda;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
    }
}
