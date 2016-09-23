package generator;

import distributions.UsageDistribution;

import java.util.List;
import java.util.UUID;

/**
 * Created by augusto on 9/18/16.
 */
public class VirtualMachine {

    private int sla;
    private CloudService cloudService;
    private Integer id;
    private int initTime;
    private int endTime;
    private UsageDistribution usageDistribution;
    private InstanceType instanceType;

    public VirtualMachine(Integer id, int actualTime, int endTime, int sla, CloudService cloudService) {
        this.id = id;
        this.initTime = actualTime;
        this.endTime = endTime;
        this.sla = sla;
        this.cloudService = cloudService;
        this.instanceType = InstanceManager.getInstance().getRandomInstanceType();

        this.usageDistribution = new UsageDistribution(ConfigurationManager.getUsageConfiguration(), endTime - initTime);
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

    public UsageDistribution getUsageDistribution() {
        return usageDistribution;
    }

    public void setUsageDistribution(UsageDistribution usageDistribution) {
        this.usageDistribution = usageDistribution;
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
}
