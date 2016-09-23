package generator;

import distributions.UniformDistribution;
import taxonomy.HorizontalElasticity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by augusto on 9/17/16.
 */
public class CloudTraceGenerator {

    private static List<CloudService> cloudServiceList;

    public static List<CloudService> generateVirtualMachinesRequestsArrivals(
            HorizontalElasticity horElast,
            int numberOfServices,
            int initTime,
            int endTime
    ) {
        initializeCloudServiceList(numberOfServices);
        UniformDistribution slaSeries = new UniformDistribution(1, 4, endTime - initTime);

        for (int actualTime = initTime; actualTime < endTime; actualTime++){
            int numberOfVMs = horElast.getDistribution().getSeries().get(actualTime);

            for (int i = 0; i < numberOfVMs; i++) {
                CloudService cloudService = getRandomCloudService();

                while (cloudService.getVirtualMachineList().size() >= ConfigurationManager.getMaxVmsPerService()) {
                    cloudService = getRandomCloudService();
                }

                VirtualMachine vm = new VirtualMachine(
                        i,
                        actualTime,
                        endTime,
                        slaSeries.getSeries().get(actualTime-1),
                        cloudService
                );

                cloudService.addVirtualMachine(vm);
            }
        }

        return cloudServiceList;
    }

    public static void initializeCloudServiceList(int numberOfServices) {
        cloudServiceList = new ArrayList<>();

        for (int i = 0; i < numberOfServices; i++) {
            cloudServiceList.add(new CloudService(i));
        }
    }

    public static CloudService getRandomCloudService() {
        Random random = new Random();

        int randomServiceIndex = random.nextInt(cloudServiceList.size());

        return cloudServiceList.get(randomServiceIndex);
    }

}
