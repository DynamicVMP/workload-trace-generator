package generator;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by augusto on 9/18/16.
 */
public class PrintTraceService {
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

                    row.append(timeIndex).append(" ");                          // TIME
                    row.append(vm.getCloudService().getId()).append(" ");       // S_ID
                    row.append("0 ");                                           // DC_ID
                    row.append(vm.getId()).append(" ");                         // VM_ID
                    row.append(vm.getInstanceType().getCpu()).append(" ");      // CPU
                    row.append(vm.getInstanceType().getRam()).append(" ");      // RAM
                    row.append(vm.getInstanceType().getNet()).append(" ");      // NET
                    row.append(vm.getUsageDistribution().getSeries().get(timeIndex - vm.getInitTime())).append(" "); // U_CPU
                    row.append(vm.getUsageDistribution().getSeries().get(timeIndex - vm.getInitTime())).append(" "); // U_RAM
                    row.append(vm.getUsageDistribution().getSeries().get(timeIndex - vm.getInitTime())).append(" "); // U_NET
                    row.append(vm.getInstanceType().getRevenue()).append(" ");  // REVENUE
                    row.append(vm.getSla()).append(" ");                        // SLA
                    row.append(vm.getInitTime()).append(" ");                   // INIT_TIME
                    row.append(vm.getEndTime());                                // END_TIME

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
            Files.write(pathToOutputFile, "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            for ( String formattedVM : formattedVMList ) {
                Files.write(pathToOutputFile, formattedVMList, StandardOpenOption.APPEND);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
