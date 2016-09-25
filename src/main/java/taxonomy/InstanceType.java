package taxonomy;

/**
 * Created by augusto on 9/18/16.
 */
public class InstanceType {

    private String instanceName;
    private Float vCPU;
    private Float cpu;
    private Float ram;
    private Float net;
    private Float revenueCPU;
    private Float revenueRAM;
    private Float revenueNET;

    public InstanceType(String instanceName, Float vCPU, Float cpu, Float ram, Float net, Float revenueCPU, Float revenueRAM, Float revenueNET) {
        this.instanceName = instanceName;
        this.vCPU = vCPU;
        this.cpu = cpu;
        this.ram = ram;
        this.net = net;
        this.revenueCPU = revenueCPU;
        this.revenueRAM = revenueRAM;
        this.revenueNET = revenueNET;
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

    public Float getRevenueCPU() {
        return revenueCPU;
    }

    public void setRevenueCPU(Float revenueCPU) {
        this.revenueCPU = revenueCPU;
    }

    public Float getRevenueRAM() {
        return revenueRAM;
    }

    public void setRevenueRAM(Float revenueRAM) {
        this.revenueRAM = revenueRAM;
    }

    public Float getRevenueNET() {
        return revenueNET;
    }

    public void setRevenueNET(Float revenueNET) {
        this.revenueNET = revenueNET;
    }
}
