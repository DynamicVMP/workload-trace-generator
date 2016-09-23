package generator;

/**
 * Created by augusto on 9/18/16.
 */
public class InstanceType {

    private String instanceName;
    private Float vCPU;
    private Float cpu;
    private Float ram;
    private Float net;
    private Float revenue;

    public InstanceType(String instanceName, Float vCPU, Float cpu, Float ram, Float net, float revenue) {
        this.instanceName = instanceName;
        this.vCPU = vCPU;
        this.cpu = cpu;
        this.ram = ram;
        this.net = net;
        this.revenue = revenue;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Float getvCPU() {
        return vCPU;
    }

    public void setvCPU(Float vCPU) {
        this.vCPU = vCPU;
    }

    public Float getCpu() {
        return cpu;
    }

    public void setCpu(Float cpu) {
        this.cpu = cpu;
    }

    public Float getRam() {
        return ram;
    }

    public void setRam(Float ram) {
        this.ram = ram;
    }

    public Float getNet() {
        return net;
    }

    public void setNet(Float net) {
        this.net = net;
    }

    public Float getRevenue() {
        return revenue;
    }

    public void setRevenue(Float revenue) {
        this.revenue = revenue;
    }
}
