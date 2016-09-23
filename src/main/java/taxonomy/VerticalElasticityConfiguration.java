package taxonomy;

/**
 * Created by augusto on 9/17/16.
 */
public class VerticalElasticityConfiguration {
    private Integer floor;
    private Integer ceiling;
    private float lambda;
    private String distributionType;

    public VerticalElasticityConfiguration(Integer floor, Integer ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
        this.distributionType = "UNIFORM";
    }

    public VerticalElasticityConfiguration(float lambda) {
        this.lambda = lambda;
        this.distributionType = "POISSON";
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

    public float getLambda() {
        return lambda;
    }

    public void setLambda(float lambda) {
        this.lambda = lambda;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
    }
}
