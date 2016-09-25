package intanceTypes;

/**
 * Created by augusto on 9/18/16.
 */
public class InstanceType {

    private final String instanceName;
    private final Float vCPU;
    private final Float cpu;
    private final Float ram;
    private final Float net;
    private final Float revenueCPU;
    private final Float revenueRAM;
    private final Float revenueNET;

    public InstanceType(String instanceName, Float vCPU, Float cpu, Float ram, Float net, Revenue revenue) {
        this.instanceName = instanceName;
        this.vCPU = vCPU;
        this.cpu = cpu;
        this.ram = ram;
        this.net = net;
        this.revenueCPU = revenue.getCpu();
        this.revenueRAM = revenue.getRam();
        this.revenueNET = revenue.getNet();
    }

    public String getInstanceName() {
        return instanceName;
    }

    public Float getvCPU() {
        return vCPU;
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

    public Float getRevenueCPU() {
        return revenueCPU;
    }

    public Float getRevenueRAM() {
        return revenueRAM;
    }

    public Float getRevenueNET() {
        return revenueNET;
    }
}
