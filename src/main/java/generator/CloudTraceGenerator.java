package generator;

import taxonomy.HorizontalElasticity;
import taxonomy.OverbookingDistribution;
import taxonomy.VerticalElasticity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by augusto on 9/17/16.
 */
public class CloudTraceGenerator {

    private static List<CloudService> cloudServiceList;

    public static List<CloudService> generateVirtualMachinesRequestsArrivals(
            HorizontalElasticity horizontalElasticity,
            VerticalElasticity verticalElasticity,
            OverbookingDistribution serverOverbooking,
            OverbookingDistribution networkOverbooking,
            int numberOfServices,
            int initTime,
            int endTime
    ) {
        initializeCloudServiceList(numberOfServices, initTime, endTime);

        for (int actualTime = initTime; actualTime < endTime; actualTime++){
            final int finalActualTime = actualTime;
            List<CloudService> livingCloudServices = cloudServiceList.stream()
                    .filter(cloudService -> finalActualTime >= cloudService.getStartTime() && finalActualTime <= cloudService.getEndTime())
                    .collect(Collectors.toList());

            livingCloudServices.forEach(cloudService -> {
                List<VirtualMachine> virtualMachineList;
                Integer vmQuantity = horizontalElasticity.getNextVmQuantity();

                generateVirtualMachinesForSingleService(finalActualTime, vmQuantity, cloudService, serverOverbooking, networkOverbooking, verticalElasticity);
            });
        }

        return cloudServiceList;
    }

    private static void generateVirtualMachinesForSingleService(
            Integer actualTime,
            Integer vmQuantity,
            CloudService cloudService,
            OverbookingDistribution serverOverbooking,
            OverbookingDistribution networkOverbooking,
            VerticalElasticity verticalElasticity
    ) {
        Random random = new Random();

        Set<VirtualMachine> livingVirtualMachineList = cloudService.getLivingVirtualMachines(actualTime);

        if (livingVirtualMachineList.size() > vmQuantity) {
            Integer livingVirtualMachinesListSize = livingVirtualMachineList.size();
            Set<VirtualMachine> vmsToKill = new HashSet<>();

            while ( livingVirtualMachinesListSize > vmQuantity ) {
                Integer vmRandomIndex = random.nextInt(livingVirtualMachineList.size());
                Object[] livingVirtualMachineListArray = livingVirtualMachineList.toArray();

                if (vmsToKill.add((VirtualMachine) livingVirtualMachineListArray[vmRandomIndex])) {
                    livingVirtualMachinesListSize--;
                }
            }

            vmsToKill.parallelStream().forEach(oldVm -> oldVm.setEndTime(actualTime - 1));

        } else if (livingVirtualMachineList.size() < vmQuantity) {
            while (livingVirtualMachineList.size() < vmQuantity) {
                livingVirtualMachineList.add(
                        new VirtualMachine(
                                cloudService.getVirtualMachineNextId(),
                                cloudService,
                                actualTime
                        )
                );
            }

            cloudService.getVirtualMachineList().addAll(livingVirtualMachineList);
        }

        livingVirtualMachineList = cloudService.getLivingVirtualMachines(actualTime);

        livingVirtualMachineList.forEach(virtualMachine -> virtualMachine.addStateSnapshot(
                serverOverbooking.getNextUsagePercentage(),
                networkOverbooking.getNextUsagePercentage(),
                verticalElasticity.getNextInstanceType(),
                actualTime
        ));
    }

    private static void initializeCloudServiceList(Integer numberOfServices, Integer initTime, Integer endTime) {
        cloudServiceList = new ArrayList<>();

        for (int i = 0; i < numberOfServices; i++) {
            cloudServiceList.add(new CloudService(i, initTime, endTime));
        }
    }
}
