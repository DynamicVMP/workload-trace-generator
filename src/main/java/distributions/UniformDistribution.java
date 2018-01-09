package distributions;

import java.util.Random;

/**
 * Created by augusto on 9/17/16.
 */
public class UniformDistribution implements DistributionInterface {

    private final Random uniformGenerator;
    private final Integer floor;
    private final Integer ceiling;

    public UniformDistribution(Integer floor, Integer ceiling) {
        this.uniformGenerator = new Random();
        this.floor = floor;
        this.ceiling = ceiling;
    }

    @Override
    public Integer getNextValue() {
        if ( floor.equals(ceiling) ) {
            return ceiling;
        } else {
            return this.uniformGenerator.nextInt(ceiling - floor) + floor;
        }
    }

    public Integer getFloor() {
        return floor;
    }

    public Integer getCeiling() {
        return ceiling;
    }
}
