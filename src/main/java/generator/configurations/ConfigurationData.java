package generator.configurations;

import java.util.Optional;

/**
 * Created by augusto on 9/27/16.
 */
public class ConfigurationData {
    private Integer scenarioStartTime;
    private Integer scenarioEndTime;
    private Integer numberOfServices;
    private Integer maxNumberOfVMPerService;
    private String instanceTypesFileLocation;
    private String outputFileLocation;

    private Float revenueCPU;
    private Float revenueRAM;
    private Float revenueNET;

    private Boolean staticServiceGeneration;

    private ElasticityConfiguration horizontalElasticityConfiguration;
    private ElasticityConfiguration verticalElasticityConfiguration;
    private ElasticityConfiguration serverOverbookingConfiguration;
    private ElasticityConfiguration networkOverbookingConfiguration;

    public ConfigurationData() {
        // This empty constructor is requested by the GSON library
    }

    public Optional<Integer> getScenarioStartTime() {
        return Optional.ofNullable(scenarioStartTime);
    }

    public void setScenarioStartTime(Integer scenarioStartTime) {
        this.scenarioStartTime = scenarioStartTime;
    }

    public Optional<Integer> getScenarioEndTime() {
        return Optional.ofNullable(scenarioEndTime);
    }

    public void setScenarioEndTime(Integer scenarioEndTime) {
        this.scenarioEndTime = scenarioEndTime;
    }

    public Optional<Integer> getNumberOfServices() {
        return Optional.ofNullable(numberOfServices);
    }

    public void setNumberOfServices(Integer numberOfServices) {
        this.numberOfServices = numberOfServices;
    }

    public Optional<Integer> getMaxNumberOfVMPerService() {
        return Optional.of(maxNumberOfVMPerService);
    }

    public void setMaxNumberOfVMPerService(Integer maxNumberOfVMPerService) {
        this.maxNumberOfVMPerService = maxNumberOfVMPerService;
    }

    public Optional<String> getInstanceTypesFileLocation() {
        return Optional.ofNullable(instanceTypesFileLocation);
    }

    public void setInstanceTypesFileLocation(String instanceTypesFileLocation) {
        this.instanceTypesFileLocation = instanceTypesFileLocation;
    }

    public Optional<String> getOutputFileLocation() {
        return Optional.ofNullable(outputFileLocation);
    }

    public void setOutputFileLocation(String outputFileLocation) {
        this.outputFileLocation = outputFileLocation;
    }

    public Optional<Float> getRevenueCPU() {
        return Optional.ofNullable(revenueCPU);
    }

    public void setRevenueCPU(Float revenueCPU) {
        this.revenueCPU = revenueCPU;
    }

    public Optional<Float> getRevenueRAM() {
        return Optional.ofNullable(revenueRAM);
    }

    public void setRevenueRAM(Float revenueRAM) {
        this.revenueRAM = revenueRAM;
    }

    public Optional<Float> getRevenueNET() {
        return Optional.ofNullable(revenueNET);
    }

    public void setRevenueNET(Float revenueNET) {
        this.revenueNET = revenueNET;
    }

    public Optional<Boolean> isStaticServiceGeneration() {
        return Optional.ofNullable(staticServiceGeneration);
    }

    public void setStaticServiceGeneration(Boolean staticServiceGeneration) {
        this.staticServiceGeneration = staticServiceGeneration;
    }

    public Optional<ElasticityConfiguration> getHorizontalElasticityConfiguration() {
        return Optional.ofNullable(horizontalElasticityConfiguration);
    }

    public void setHorizontalElasticityConfiguration(ElasticityConfiguration horizontalElasticityConfiguration) {
        this.horizontalElasticityConfiguration = horizontalElasticityConfiguration;
    }

    public Optional<ElasticityConfiguration> getVerticalElasticityConfiguration() {
        return Optional.ofNullable(verticalElasticityConfiguration);
    }

    public void setVerticalElasticityConfiguration(ElasticityConfiguration verticalElasticityConfiguration) {
        this.verticalElasticityConfiguration = verticalElasticityConfiguration;
    }

    public Optional<ElasticityConfiguration> getServerOverbookingConfiguration() {
        return Optional.ofNullable(serverOverbookingConfiguration);
    }

    public void setServerOverbookingConfiguration(ElasticityConfiguration serverOverbookingConfiguration) {
        this.serverOverbookingConfiguration = serverOverbookingConfiguration;
    }

    public Optional<ElasticityConfiguration> getNetworkOverbookingConfiguration() {
        return Optional.ofNullable(networkOverbookingConfiguration);
    }

    public void setNetworkOverbookingConfiguration(ElasticityConfiguration networkOverbookingConfiguration) {
        this.networkOverbookingConfiguration = networkOverbookingConfiguration;
    }
}
