package generator.configurations;

/**
 * <p>Contains most of the configuration parameter to run the generator</p>
 * @author Augusto Amarilla
 * @since 9/18/16
 * @version 1.0
 */
public class ConfigurationManager {
    private static ConfigurationManager instance = null;
    private final Integer MAX_VMS_PER_SERVICE = 10;

    private final UsageConfiguration usageConfiguration;
    private final Integer scenarioStartTime;
    private final Integer scenarioEndTime;
    private final Integer numberOfServices;
    private final String inputFileLocation;
    private final String outputFileLocation;

    private final Float revenueCPU;
    private final Float revenueRAM;
    private final Float revenueNET;
    private final String locationPrefix;
    private ElasticityConfiguration horizontalElasticityConfiguration;
    private ElasticityConfiguration verticalElasticityConfiguration;
    private ElasticityConfiguration serverOverbookingConfiguration;
    private ElasticityConfiguration networkOverbookingConfiguration;

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }

        return instance;
    }

    protected ConfigurationManager() {
        scenarioStartTime = 1;
        scenarioEndTime = 100;
        numberOfServices = 100;
        usageConfiguration = new UsageConfiguration(20, 100);

        revenueCPU = 0.1F;
        revenueRAM = 0.05F;
        revenueNET = 0.001F;

        locationPrefix = "./";
        inputFileLocation = "input/input_V2.csv";
        outputFileLocation = "output/output.out";

        // Important the ceiling of the Horizontal Elasticity Distribution must not exceed the Maximum number of VMs per Service
        horizontalElasticityConfiguration = new ElasticityConfiguration(2, 10); // Testing: a Uniform distribution with parameters floor: 2 and ceiling: 10
        verticalElasticityConfiguration = new ElasticityConfiguration(5F); // Testing: a Uniform distribution with parameters floor: 2 and ceiling: 10

        serverOverbookingConfiguration = new ElasticityConfiguration(0, 100);
        networkOverbookingConfiguration = new ElasticityConfiguration(0, 100);
    }

    public Float getRevenueCPU() {
        return revenueCPU;
    }

    public Float getRevenueRAM() {
        return revenueRAM;
    }

    public Float getRevenueNET() {
        return revenueNET;
    }

    public Integer getTraceDuration() {
        return scenarioEndTime - scenarioStartTime + 1;
    }

    public Integer getMaxVmsPerService() {
        return MAX_VMS_PER_SERVICE;
    }

    public UsageConfiguration getUsageConfiguration() {
        return usageConfiguration;
    }

    public Integer getScenarioStartTime() {
        return scenarioStartTime;
    }

    public Integer getScenarioEndTime() {
        return scenarioEndTime;
    }

    public Integer getNumberOfServices() {
        return numberOfServices;
    }

    public String getInputFileLocation() {
        return locationPrefix + inputFileLocation;
    }

    public String getOutputFileLocation() {
        return locationPrefix + outputFileLocation;
    }

    public ElasticityConfiguration getHorizontalElasticityConfiguration() {
        return horizontalElasticityConfiguration;
    }

    public ElasticityConfiguration getVerticalElasticityConfiguration() {
        return verticalElasticityConfiguration;
    }

    public ElasticityConfiguration getServerOverbookingConfiguration() {
        return serverOverbookingConfiguration;
    }

    public ElasticityConfiguration getNetworkOverbookingConfiguration() {
        return networkOverbookingConfiguration;
    }
}
