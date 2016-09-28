package main;

import generator.CloudService;
import generator.CloudTraceGenerator;
import generator.configurations.ConfigurationManager;
import org.apache.log4j.Logger;
import taxonomy.HorizontalTaxonomy;
import taxonomy.OverbookingTaxonomy;
import taxonomy.VerticalTaxonomy;
import utils.PrintTraceUtils;

import java.util.List;

/**
 * Created by augusto on 9/17/16.
 */
public class CloudWorkloadTraceGenerator {

    private static Logger logger = Logger.getLogger(CloudWorkloadTraceGenerator.class);

    private CloudWorkloadTraceGenerator() {}

    public static void main(String[] args) {
        String configFile = args[0];
        ConfigurationManager configurationManagerInstance = ConfigurationManager.getInstance();
        if ( configFile != null && configFile.length() > 0 ) {
            configurationManagerInstance.initializeFromFile(configFile);
            logger.info("Generating Trace with input file:" + configurationManagerInstance.getInputFilePath());
        } else {
            logger.info("Generating Trace with default parameters");
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

        logger.debug("Finished generating Trace structure");

        List<String> formattedVMList = PrintTraceUtils.formatVMListOutput(
                cloudServiceList,
                configurationManagerInstance.getScenarioStartTime(),
                configurationManagerInstance.getScenarioEndTime()
        );

        logger.debug("Finished the format of the Trace structure");

        PrintTraceUtils.printCloudTraceToFile(
                configurationManagerInstance.getOutputFileLocation(),
                formattedVMList
        );

        logger.info("Finished generation of Workload Trace");
    }
}