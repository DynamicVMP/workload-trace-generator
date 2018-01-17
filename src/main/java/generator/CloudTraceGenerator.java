package generator;

import org.apache.log4j.Logger;
import taxonomy.HorizontalTaxonomy;
import taxonomy.OverbookingTaxonomy;
import taxonomy.VerticalTaxonomy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by augusto on 9/17/16.
 */
public class CloudTraceGenerator {
    private static Logger logger = Logger.getLogger(CloudTraceGenerator.class);

    private static List<CloudService> cloudServiceList;

    private CloudTraceGenerator() {}

    public static List<CloudService> generateVirtualMachinesRequestsArrivals(
            boolean staticServiceGeneration,
            HorizontalTaxonomy horizontalElasticity,
            VerticalTaxonomy verticalElasticity,
            OverbookingTaxonomy serverOverbooking,
            OverbookingTaxonomy networkOverbooking,
            int numberOfServices,
            int initTime,
            int endTime
    ) {
        initializeCloudServiceList(numberOfServices, initTime, endTime, staticServiceGeneration);

        for (int actualTime = initTime; actualTime < endTime; actualTime++){
            final int finalActualTime = actualTime;
            List<CloudService> livingCloudServices = cloudServiceList.stream()
                    .filter(cloudService -> finalActualTime >= cloudService.getStartTime() && finalActualTime <= cloudService.getEndTime())
                    .collect(Collectors.toList());

            livingCloudServices.forEach(cloudService -> {
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
            OverbookingTaxonomy serverOverbooking,
            OverbookingTaxonomy networkOverbooking,
            VerticalTaxonomy verticalElasticity
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

            vmsToKill.forEach(oldVm -> oldVm.setEndTime(actualTime - 1));

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

    private static void initializeCloudServiceList(Integer numberOfServices, Integer initTime, Integer endTime, boolean staticServiceGeneration) {
        logger.debug("Start generation of Cloud Service List");
        cloudServiceList = new ArrayList<>();

        for (int i = 0; i < numberOfServices; i++) {
            CloudService cloudService;

            // If the service is statically generated then it is alive during the entire simulation time
            if ( staticServiceGeneration ) {
                cloudService = new CloudService(i, initTime, endTime);
                cloudService.setStartTime(initTime);
                cloudService.setEndTime(endTime);
            } else {
                cloudService = new CloudService(i, initTime, endTime);
            }

            cloudServiceList.add(cloudService);
        }
    }
}
