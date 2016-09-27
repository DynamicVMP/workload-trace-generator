package main;

import generator.CloudService;
import generator.CloudTraceGenerator;
import generator.configurations.ConfigurationManager;
import taxonomy.HorizontalTaxonomy;
import taxonomy.OverbookingTaxonomy;
import taxonomy.VerticalTaxonomy;
import utils.PrintTraceUtils;

import java.util.List;

/**
 * Created by augusto on 9/17/16.
 */
public class CloudWorkloadTraceGenerator {

    private CloudWorkloadTraceGenerator() {}

    public static void main(String[] args) {
        String configFile = args[0];
        ConfigurationManager configurationManagerInstance = ConfigurationManager.getInstance();
        if ( configFile != null && configFile.length() > 0 ) {
            configurationManagerInstance.initializeFromFile(configFile);
        }

        HorizontalTaxonomy horizontalElasticity = new HorizontalTaxonomy(
                configurationManagerInstance.getHorizontalElasticityConfiguration()
        );

        VerticalTaxonomy verticalElasticity = new VerticalTaxonomy(
                configurationManagerInstance.getVerticalElasticityConfiguration()
        );

        OverbookingTaxonomy serverOverbooking = new OverbookingTaxonomy(
                configurationManagerInstance.getServerOverbookingConfiguration()
        );

        OverbookingTaxonomy networkOverbooking = new OverbookingTaxonomy(
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