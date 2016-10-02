package generator;

import intanceTypes.InstanceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by augusto on 9/18/16.
 */
public class VirtualMachine {

    private static Random randomGenerator = new Random();

    private final Integer id;
    private final CloudService cloudService;
    private final int sla;

    private final Map<Integer, Integer> serverUsageMap;
    private final Map<Integer, Integer> networkUsageMap;
    private final Map<Integer, InstanceType> instanceTypeMap;

    private final Integer startTime;
    private Integer endTime;

    public VirtualMachine(Integer virtualMachineNextId, CloudService cloudService, Integer startTime) {
        this.id = virtualMachineNextId;
        this.cloudService = cloudService;

        this.sla = randomGenerator.nextInt(5);

        this.startTime = startTime;
        this.endTime = cloudService.getEndTime();

        this.serverUsageMap = new HashMap<>();
        this.networkUsageMap = new HashMap<>();
        this.instanceTypeMap = new HashMap<>();
    }

    public void addStateSnapshot(Integer serverUsagePercentage, Integer networkUsagePercentage, InstanceType instanceType, Integer snapshotTime) {
        if (serverUsageMap.isEmpty()){
            this.serverUsageMap.put(snapshotTime, 100);
            this.networkUsageMap.put(snapshotTime, 100);
        } else {
            this.serverUsageMap.put(snapshotTime, serverUsagePercentage);
            this.networkUsageMap.put(snapshotTime, networkUsagePercentage);
        }

        this.instanceTypeMap.put(snapshotTime, instanceType);
    }

    public Map<Integer, Integer> getServerUsageMap() {
        return serverUsageMap;
    }

    public Map<Integer, Integer> getNetworkUsageMap() {
        return networkUsageMap;
    }

    public Map<Integer, InstanceType> getInstanceTypeMap() {
        return instanceTypeMap;
    }

    public int getSla() {
        return sla;
    }

    public CloudService getCloudService() {
        return cloudService;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VirtualMachine that = (VirtualMachine) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " ID: " + this.getId();
    }
}
