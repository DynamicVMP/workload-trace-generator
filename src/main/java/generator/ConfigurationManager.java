package generator;

/**
 * Created by augusto on 9/18/16.
 */
public class ConfigurationManager {

    private static final Integer MAX_VMS_PER_SERVICE = 10;

    private static UsageConfiguration usageConfiguration;
    private static Integer scenarioStartTime;
    private static Integer scenarioEndTime;
    private static Integer numberOfServices;
    private static String inputFileLocation;

    public static UsageConfiguration getUsageConfiguration() {
        return usageConfiguration;
    }

    public static void setUsageConfiguration(UsageConfiguration usageConfiguration) {
        ConfigurationManager.usageConfiguration = usageConfiguration;
    }

    public static Integer getScenarioStartTime() {
        return scenarioStartTime;
    }

    public static void setScenarioStartTime(Integer scenarioStartTime) {
        ConfigurationManager.scenarioStartTime = scenarioStartTime;
    }

    public static Integer getScenarioEndTime() {
        return scenarioEndTime;
    }

    public static void setScenarioEndTime(Integer scenarioEndTime) {
        ConfigurationManager.scenarioEndTime = scenarioEndTime;
    }

    public static Integer getTraceDuration() {
        return scenarioEndTime - scenarioStartTime + 1;
    }

    public static void setNumberOfServices(Integer numberOfServices) {
        ConfigurationManager.numberOfServices = numberOfServices;
    }

    public static Integer getNumberOfServices() {
        return numberOfServices;
    }

    public static int getMaxVmsPerService() {
        return MAX_VMS_PER_SERVICE;
    }

    public static String getInputFileLocation() {
        return inputFileLocation;
    }

    public static void setInputFileLocation(String inputFileLocation) {
        ConfigurationManager.inputFileLocation = inputFileLocation;
    }
}
