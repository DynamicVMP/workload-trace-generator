package generator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by augusto on 9/18/16.
 */
public class CloudService {

    private final Integer id;
    private final Set<VirtualMachine> virtualMachineList;
    private final Integer startTime;
    private final Integer endTime;
    private Integer virtualMachineNextId;

    public CloudService(Integer id, Integer startTime, Integer endTime){
        Random random = new Random();
        Integer timeDiff = endTime - startTime;
        this.virtualMachineList = new HashSet<>();

        this.id = id;

        this.startTime = random.nextInt(timeDiff) + startTime;
        Integer endTimeHolder = random.nextInt(timeDiff) + startTime;
        while (endTimeHolder <= this.startTime) {
            endTimeHolder = random.nextInt(timeDiff) + startTime;
        }
        this.endTime = endTimeHolder;
        this.virtualMachineNextId = 0;
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

    public Integer getVirtualMachineNextId() {
        return virtualMachineNextId++;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " ID: " + this.getId();
    }

    public Set<VirtualMachine> getVirtualMachineList() {
        return virtualMachineList;
    }

    public Set<VirtualMachine> getLivingVirtualMachines(Integer actualTime) {
        return virtualMachineList.parallelStream()
                .filter(virtualMachine -> actualTime >= virtualMachine.getStartTime() && actualTime <= virtualMachine.getEndTime())
                .collect(Collectors.toSet());
    }
}
