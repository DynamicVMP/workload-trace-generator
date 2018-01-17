package generator.configurations;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <p>Contains most of the configuration parameter to run the generator</p>
 * @author Augusto Amarilla
 * @since 9/18/16
 * @version 1.0
 */
public class ConfigurationManager {
    private static final Logger logger = Logger.getLogger(ConfigurationManager.class);

    private static ConfigurationManager instance = null;
    private static Integer maxNumberOfVMPerService = 10;

    private Integer scenarioStartTime;
    private Integer scenarioEndTime;
    private Integer numberOfServices;
    private String instanceTypesFileLocation;
    private String outputFileLocation;

    private Float revenueCPU;
    private Float revenueRAM;
    private Float revenueNET;

    private boolean staticServiceGeneration;
    private ElasticityConfiguration horizontalElasticityConfiguration;
    private ElasticityConfiguration verticalElasticityConfiguration;
    private ElasticityConfiguration serverOverbookingConfiguration;
    private ElasticityConfiguration networkOverbookingConfiguration;
    private String inputFilePath;

    /**
     * Setting up default values to the Configuration Manager
     */
    protected ConfigurationManager() {
        scenarioStartTime = 1;
        scenarioEndTime = 100;
        numberOfServices = 100;

        revenueCPU = 0.01F;
        revenueRAM = 0.002F;
        revenueNET = 0.0004F;

        instanceTypesFileLocation = "input/instanceTypes.csv";
        outputFileLocation = "output/output.csv";

        staticServiceGeneration = false;

        // Important the ceiling of the Horizontal Elasticity Distribution must not exceed the Maximum number of VMs per Service
        horizontalElasticityConfiguration = new ElasticityConfiguration(1, 11);
        verticalElasticityConfiguration = new ElasticityConfiguration(1,11);

        serverOverbookingConfiguration = new ElasticityConfiguration(70F);
        networkOverbookingConfiguration = new ElasticityConfiguration(70F);
    }

    public void initializeFromFile(String filePath) {
        Path pathToConfigFile = Paths.get(filePath);

        try {
            if (Files.isReadable(pathToConfigFile)) {
                this.inputFilePath = filePath;

                Stream<String> stream = Files.lines(pathToConfigFile);
                String jsonConfigInput = stream.reduce("", String::concat);
                Gson gson = new Gson();
                ConfigurationData configurationData = gson.fromJson(jsonConfigInput, ConfigurationData.class);

                scenarioStartTime = configurationData.getScenarioStartTime().orElse(scenarioStartTime);
                scenarioEndTime = configurationData.getScenarioEndTime().orElse(scenarioEndTime);
                numberOfServices = configurationData.getNumberOfServices().orElse(numberOfServices);
                maxNumberOfVMPerService = configurationData.getMaxNumberOfVMPerService().orElse(maxNumberOfVMPerService);
                instanceTypesFileLocation = configurationData.getInstanceTypesFileLocation().orElse(instanceTypesFileLocation);
                outputFileLocation = configurationData.getOutputFileLocation().orElse(outputFileLocation);

                revenueCPU = configurationData.getRevenueCPU().orElse(revenueCPU);
                revenueRAM = configurationData.getRevenueRAM().orElse(revenueRAM);
                revenueNET = configurationData.getRevenueNET().orElse(revenueNET);

                staticServiceGeneration = configurationData.isStaticServiceGeneration().orElse(false);
                horizontalElasticityConfiguration = configurationData.getHorizontalElasticityConfiguration().orElse(horizontalElasticityConfiguration);
                verticalElasticityConfiguration = configurationData.getVerticalElasticityConfiguration().orElse(verticalElasticityConfiguration);
                serverOverbookingConfiguration = configurationData.getServerOverbookingConfiguration().orElse(serverOverbookingConfiguration);
                networkOverbookingConfiguration = configurationData.getNetworkOverbookingConfiguration().orElse(networkOverbookingConfiguration);
            } else {
                throw new ConfigurationManagerException("The configuration file " + filePath + " is not readable");
            }
        } catch (FileNotFoundException e) {
            logger.error("Input file not found: ["+ inputFilePath +"]", e);
            throw new ConfigurationManagerException("");
        } catch (IOException e) {
            logger.error("Error reading the input file " + filePath, e);
            throw new ConfigurationManagerException("");
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }

        return instance;
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

    public Integer getMaxVmsPerService() {
        return maxNumberOfVMPerService;
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

    public String getInstanceTypesFileLocation() {
        return instanceTypesFileLocation;
    }

    public String getOutputFileLocation() {
        return outputFileLocation;
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

    public String getInputFilePath() {
        return inputFilePath;
    }

    public boolean isServiceGenerationStatic() {
        return staticServiceGeneration;
    }
}
