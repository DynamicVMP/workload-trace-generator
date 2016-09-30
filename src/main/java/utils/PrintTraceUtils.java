package utils;

import generator.CloudService;
import generator.VirtualMachine;
import generator.configurations.ConfigurationManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by augusto on 9/18/16.
 */
public class PrintTraceUtils {
    private static final Logger logger = Logger.getLogger(PrintTraceUtils.class);
    private static final String DELIMITER = "\t";

    private PrintTraceUtils() {}

    public static List<String> formatVMListOutput(List<CloudService> cloudServiceList, Integer scenarioStartTime, Integer scenarioEndTime) {
        logger.debug("Start to format the virtual machines traces to snapshots");
        List<String> cloudGeneratedTrace = new ArrayList<>();

        Integer timeIndex = scenarioStartTime;
        for (;timeIndex < scenarioEndTime; timeIndex++){
            for (CloudService cloudService : cloudServiceList) {
                if (timeIndex >= cloudService.getStartTime() && timeIndex <= cloudService.getEndTime()) {
                    Integer finalTimeIndex = timeIndex;
                    cloudService.getLivingVirtualMachines(timeIndex).forEach(vm -> cloudGeneratedTrace.add(printSingleSnapshot(finalTimeIndex, vm)));
                }
            }
        }

        return cloudGeneratedTrace;
    }

    private static String printSingleSnapshot(Integer finalTimeIndex, VirtualMachine vm) {
        StringBuilder row = new StringBuilder();

        row.append(finalTimeIndex).append(DELIMITER);                                               // TIME
        row.append(vm.getCloudService().getId()).append(DELIMITER);                                 // S_ID
        row.append("0").append(DELIMITER);                                                          // DC_ID
        row.append(vm.getId()).append(DELIMITER);                                                   // VM_ID
        row.append(vm.getInstanceTypeMap().get(finalTimeIndex).getCpu()).append(DELIMITER);         // CPU
        row.append(vm.getInstanceTypeMap().get(finalTimeIndex).getRam()).append(DELIMITER);         // RAM
        row.append(vm.getInstanceTypeMap().get(finalTimeIndex).getNet()).append(DELIMITER);         // NET
        row.append(vm.getServerUsageMap().get(finalTimeIndex)).append(DELIMITER);                   // U_CPU
        row.append(vm.getServerUsageMap().get(finalTimeIndex)).append(DELIMITER);                   // U_RAM
        row.append(vm.getNetworkUsageMap().get(finalTimeIndex)).append(DELIMITER);                  // U_NET
        row.append(vm.getInstanceTypeMap().get(finalTimeIndex).getRevenueCPU()).append(DELIMITER);  // REVENUE_CPU
        row.append(vm.getInstanceTypeMap().get(finalTimeIndex).getRevenueRAM()).append(DELIMITER);  // REVENUE_RAM
        row.append(vm.getInstanceTypeMap().get(finalTimeIndex).getRevenueNET()).append(DELIMITER);  // REVENUE_NET
        row.append(vm.getSla()).append(DELIMITER);                                                  // SLA
        row.append(vm.getStartTime()).append(DELIMITER);                                            // INIT_TIME
        row.append(vm.getEndTime());                                                                // END_TIME

        return row.toString();
    }

    public static void printCloudTraceToFile(String outputFileLocation, List<String> formattedVMList) {
        logger.debug("Start to print the snapshots to the output file");
        Path pathToOutputFile = Paths.get(outputFileLocation);

        try {
            Files.write(pathToOutputFile, "".getBytes(), StandardOpenOption.CREATE);
            Files.write(pathToOutputFile, formattedVMList, StandardOpenOption.TRUNCATE_EXISTING);

            logger.debug("Printed trace into file: " + outputFileLocation);
        } catch (FileNotFoundException e) {
            logger.error("Input file not found: ["+ ConfigurationManager.getInstance().getInstanceTypesFileLocation() +"]", e);
            throw new PrintTraceUtilsException("Input file not found: ["+ ConfigurationManager.getInstance().getInstanceTypesFileLocation() +"]");
        } catch (IOException e) {
            logger.error("Error printing the output file", e);
            throw new PrintTraceUtilsException("Unknown error");
        }
    }
}
