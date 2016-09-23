package distributions;

import cern.jet.random.Poisson;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by augusto on 9/17/16.
 */
public class PoissonDistribution implements DistributionInterface {

    private Float lambda;
    private int timeFrame;
    private List<Integer> series;

    public PoissonDistribution(float lambda, int timeFrame) {
        this.series = new ArrayList<>();
        this.lambda = lambda;
        this.timeFrame = timeFrame;

        this.series = generatePoissonSeries();
    }

    public PoissonDistribution(float lambda) {
        this.series = new ArrayList<>();
        this.lambda = lambda;
        this.timeFrame = 10;

        this.series = generatePoissonSeries();
    }

    private List<Integer> generatePoissonSeries() {
        List<Integer> series = new ArrayList<>();
        RandomEngine engine = new DRand();
        Poisson poisson = new Poisson(lambda, engine);

        for (int i = 0; i < timeFrame; i++) {
            series.add(poisson.nextInt());
        }

        return series;
    }

    public List<Integer> getSeries(){
        return series;
    }

    public Float getLambda() {
        return lambda;
    }

    public void setLambda(Float lambda) {
        this.lambda = lambda;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }
}
