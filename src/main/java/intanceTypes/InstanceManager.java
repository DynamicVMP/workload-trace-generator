package intanceTypes;

import generator.configurations.ConfigurationManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by augusto on 9/18/16.
 */
public class InstanceManager {
    private static final Logger logger = Logger.getLogger(InstanceManager.class);

    private static InstanceManager instance = null;
    private List<InstanceType> instanceTypeList;

    protected InstanceManager() {
        instanceTypeList = new ArrayList<>();
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();

        try {
            Stream<String> stream = Files.lines(Paths.get(ConfigurationManager.getInstance().getInstanceTypesFileLocation()));

            stream.forEach(line -> {
                String[] splitLine = line.split(";");

                // Headers must not be processed
                if (!"name".equals(splitLine[0])) {
                    String instanceName = splitLine[0];
                    Float vCPU = Float.parseFloat(splitLine[1]);
                    Float ecu = Float.parseFloat(splitLine[2]);
                    Float ram = Float.parseFloat(splitLine[3]);
                    Float net = Float.parseFloat(splitLine[4]);
                    Float revenueCPU = ecu * configurationManager.getRevenueCPU();
                    Float revenueRAM = ram * configurationManager.getRevenueRAM();
                    Float revenueNET = net * configurationManager.getRevenueNET();
                    Revenue revenue = new Revenue(revenueCPU, revenueRAM, revenueNET);

                    instanceTypeList.add(new InstanceType(instanceName, vCPU, ecu, ram, net, revenue));
                }
            });
        } catch (FileNotFoundException e) {
            logger.error("Input file not found: ["+ ConfigurationManager.getInstance().getInstanceTypesFileLocation() +"]", e);
            throw new InstanceManagerException();
        } catch (IOException e) {
            logger.error(e);
            throw new InstanceManagerException();
        }
    }

    public static InstanceManager getInstance() {
        if (instance == null) {
            instance = new InstanceManager();
        }

        return instance;
    }

    public InstanceType getInstanceType(int instanceTypeIndex) {
        return instanceTypeList.get(instanceTypeIndex);
    }

    public Integer getInstanceTypeQuantity() {
        return instanceTypeList.size();
    }
}
