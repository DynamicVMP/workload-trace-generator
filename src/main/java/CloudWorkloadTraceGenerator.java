import generator.*;
import taxonomy.HorizontalElasticity;

import java.util.List;

/**
 * Created by augusto on 9/17/16.
 */
public class CloudWorkloadTraceGenerator {

    public static void main(String[] args) {
        ConfigurationManager.setScenarioStartTime(1);
        ConfigurationManager.setScenarioEndTime(100);
        ConfigurationManager.setNumberOfServices(100);
        UsageConfiguration usageConfiguration = new UsageConfiguration(20, 100);
        ConfigurationManager.setUsageConfiguration(usageConfiguration);
        ConfigurationManager.setInputFileLocation("/home/augusto/Dropbox/tesis/repositories/cloud-workload-trace-generator/input/input.csv");

        HorizontalElasticity horElast = new HorizontalElasticity(1, 10, ConfigurationManager.getNumberOfServices(), ConfigurationManager.getTraceDuration());

        List<CloudService> cloudServiceList = CloudTraceGenerator.generateVirtualMachinesRequestsArrivals(
                horElast,
                ConfigurationManager.getNumberOfServices(),
                ConfigurationManager.getScenarioStartTime(),
                ConfigurationManager.getScenarioEndTime()
        );

        List<String> formattedVMList = PrintTraceService.formatVMListOutput(cloudServiceList);
        PrintTraceService.printCloudTraceToFile("/home/augusto/Dropbox/tesis/repositories/cloud-workload-trace-generator/output/output.out", formattedVMList);
    }
}