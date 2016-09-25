package intanceTypes;

/**
 * Created by Augusto on 25/09/2016.
 */
public class Revenue {
    private final Float cpu;
    private final Float ram;
    private final Float net;

    public Revenue(Float cpu, Float ram, Float net) {
        this.cpu = cpu;
        this.ram = ram;
        this.net = net;
    }

    public Float getCpu() {
        return cpu;
    }

    public Float getRam() {
        return ram;
    }

    public Float getNet() {
        return net;
    }
}
