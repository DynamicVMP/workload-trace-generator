import generator.CloudService;
import generator.CloudTraceGenerator;
import generator.configurations.ConfigurationManager;
import taxonomy.HorizontalElasticity;
import taxonomy.OverbookingDistribution;
import taxonomy.VerticalElasticity;
import utils.PrintTraceUtils;

import java.util.List;

/**
 * Created by augusto on 9/17/16.
 */
public class CloudWorkloadTraceGenerator {

    public static void main(String[] args) {
        ConfigurationManager configurationManagerInstance = ConfigurationManager.getInstance();

        HorizontalElasticity horizontalElasticity = new HorizontalElasticity(
                configurationManagerInstance.getHorizontalElasticityConfiguration()
        );

        VerticalElasticity verticalElasticity = new VerticalElasticity(
                configurationManagerInstance.getVerticalElasticityConfiguration()
        );

        OverbookingDistribution serverOverbooking = new OverbookingDistribution(
                configurationManagerInstance.getServerOverbookingConfiguration()
        );

        OverbookingDistribution networkOverbooking = new OverbookingDistribution(
                configurationManagerInstance.getNetworkOverbookingConfiguration()
        );

        List<CloudService> cloudServiceList = CloudTraceGenerator.generateVirtualMachinesRequestsArrivals(
                horizontalElasticity,
                verticalElasticity,
                serverOverbooking,
                networkOverbooking,
                configurationManagerInstance.getNumberOfServices(),
                configurationManagerInstance.getScenarioStartTime(),
                configurationManagerInstance.getScenarioEndTime()
        );

        List<String> formattedVMList = PrintTraceUtils.formatVMListOutput(
                cloudServiceList,
                configurationManagerInstance.getScenarioStartTime(),
                configurationManagerInstance.getScenarioEndTime()
        );

        PrintTraceUtils.printCloudTraceToFile(
                configurationManagerInstance.getOutputFileLocation(),
                formattedVMList
        );
    }
}