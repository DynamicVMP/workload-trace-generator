package distributions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by augusto on 9/17/16.
 */
public class UniformDistribution implements DistributionInterface {

    private Integer floor;
    private Integer ceiling;
    private int timeFrame;
    private List<Integer> series;

    public UniformDistribution(Integer floor, Integer ceiling, int timeFrame) {
        this.floor = floor;
        this.ceiling = ceiling;
        this.timeFrame = timeFrame;

        this.series = generateUniformSeries();
    }

    public UniformDistribution(Integer floor, Integer ceiling ) {
        this.floor = floor;
        this.ceiling = ceiling;
        this.timeFrame = 10;

        this.series = generateUniformSeries();
    }

    private List<Integer> generateUniformSeries() {
        List<Integer> series = new ArrayList<>();
        Random random = new Random();

        for ( int i = 0; i < timeFrame; i++ ) {
            if (ceiling.equals(floor) && ceiling > 0) {
                series.add(ceiling);
            } else {
                series.add(random.nextInt(ceiling));
            }
        }

        return series;
    }

    public List<Integer> getSeries() {
        return series;
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

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }
}
