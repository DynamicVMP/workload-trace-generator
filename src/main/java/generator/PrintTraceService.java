package generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by augusto on 9/18/16.
 */
public class PrintTraceService {

    public static final String DELIMITATOR = "\t";

    public static List<String> formatVMListOutput(List<CloudService> cloudServiceList) {
        List<String> cloudGeneratedTrace = new ArrayList<>();

        List<VirtualMachine> completeVMList = joinVMLists(cloudServiceList);
        completeVMList = completeVMList.stream()
                .sorted((VirtualMachine vm1, VirtualMachine vm2)-> vm1.getInitTime()-vm2.getInitTime())
                .collect(Collectors.toList());

        Integer timeIndex = ConfigurationManager.getScenarioStartTime();
        for (;timeIndex < ConfigurationManager.getScenarioEndTime(); timeIndex++){
            for (VirtualMachine vm : completeVMList) {
                if (timeIndex >= vm.getInitTime() && timeIndex <= vm.getEndTime()) {
                    StringBuilder row = new StringBuilder();

                    row.append(timeIndex).append(DELIMITATOR);                          // TIME
                    row.append(vm.getCloudService().getId()).append(DELIMITATOR);       // S_ID
                    row.append("0").append(DELIMITATOR);                                // DC_ID
                    row.append(vm.getId()).append(DELIMITATOR);                         // VM_ID
                    row.append(vm.getInstanceType().getCpu()).append(DELIMITATOR);      // CPU
                    row.append(vm.getInstanceType().getRam()).append(DELIMITATOR);      // RAM
                    row.append(vm.getInstanceType().getNet()).append(DELIMITATOR);      // NET
                    row.append(vm.getServerUsageDistribution().getSeries().get(timeIndex - vm.getInitTime())).append(DELIMITATOR);  // U_CPU
                    row.append(vm.getServerUsageDistribution().getSeries().get(timeIndex - vm.getInitTime())).append(DELIMITATOR);  // U_RAM
                    row.append(vm.getNetUsageDistribution().getSeries().get(timeIndex - vm.getInitTime())).append(DELIMITATOR);     // U_NET
                    row.append(vm.getInstanceType().getRevenue()).append(DELIMITATOR);  // REVENUE
                    row.append(vm.getSla()).append(DELIMITATOR);                        // SLA
                    row.append(vm.getInitTime()).append(DELIMITATOR);                   // INIT_TIME
                    row.append(vm.getEndTime());                                        // END_TIME

                    cloudGeneratedTrace.add(row.toString());
                }
            }
        }

        return cloudGeneratedTrace;
    }

    private static List<VirtualMachine> joinVMLists(List<CloudService> cloudServiceList) {
        List<VirtualMachine> joinedVMList = new ArrayList<>();

        cloudServiceList.forEach(cloudService->joinedVMList.addAll(cloudService.getVirtualMachineList()));

        return joinedVMList;
    }

    public static void printCloudTraceToFile(String outputFileLocation, List<String> formattedVMList) {
        Path pathToOutputFile = Paths.get(outputFileLocation);

        try {
            Files.write(pathToOutputFile, "".getBytes(), StandardOpenOption.CREATE);
            Files.write(pathToOutputFile, formattedVMList, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error printing the output");
            e.printStackTrace();
        }
    }
}
