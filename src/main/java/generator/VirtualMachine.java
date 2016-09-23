package generator;

import distributions.UsageDistribution;

/**
 * Created by augusto on 9/18/16.
 */
public class VirtualMachine {

    private int sla;
    private CloudService cloudService;
    private Integer id;
    private int initTime;
    private int endTime;
    private UsageDistribution serverUsageDistribution;
    private UsageDistribution netUsageDistribution;
    private InstanceType instanceType;

    public VirtualMachine(Integer id, int actualTime, int endTime, int sla, CloudService cloudService) {
        this.id = id;
        this.initTime = actualTime;
        this.endTime = endTime;
        this.sla = sla;
        this.cloudService = cloudService;
        this.instanceType = InstanceManager.getInstance().getRandomInstanceType();

        this.serverUsageDistribution = new UsageDistribution(ConfigurationManager.getUsageConfiguration(), endTime - initTime);
        this.netUsageDistribution = new UsageDistribution(ConfigurationManager.getUsageConfiguration(), endTime - initTime);
    }

    public int getInitTime() {
        return initTime;
    }

    public void setInitTime(int initTime) {
        this.initTime = initTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public UsageDistribution getServerUsageDistribution() {
        return serverUsageDistribution;
    }

    public void setServerUsageDistribution(UsageDistribution serverUsageDistribution) {
        this.serverUsageDistribution = serverUsageDistribution;
    }

    public InstanceType getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(InstanceType instanceType) {
        this.instanceType = instanceType;
    }

    public CloudService getCloudService() {
        return cloudService;
    }

    public void setCloudService(CloudService cloudService) {
        this.cloudService = cloudService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSla() {
        return sla;
    }

    public void setSla(int sla) {
        this.sla = sla;
    }

    public UsageDistribution getNetUsageDistribution() {
        return netUsageDistribution;
    }

    public void setNetUsageDistribution(UsageDistribution netUsageDistribution) {
        this.netUsageDistribution = netUsageDistribution;
    }
}
