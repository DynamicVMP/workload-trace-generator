package generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by augusto on 9/18/16.
 */
public class InstanceManager {
    private static InstanceManager instance = null;
    private List<InstanceType> instanceTypeList;
    private Random random;

    public static InstanceManager getInstance() {
        if (instance == null) {
            instance = new InstanceManager();
        }

        return instance;
    }

    protected InstanceManager() {
        instanceTypeList = new ArrayList<>();
        random = new Random();

        try {
            Stream<String> stream = Files.lines(Paths.get(ConfigurationManager.getInputFileLocation()));

            stream.forEach(line -> {
                String[] splittedLine = line.split(";");

                // Headers must not be processed
                if ( !"name".equals(splittedLine[0]) ) {
                    String instanceName = splittedLine[0];
                    Float vCPU = Float.parseFloat(splittedLine[1]);
                    Float ecu = Float.parseFloat(splittedLine[2]);
                    Float ram = Float.parseFloat(splittedLine[3]);
                    Float net = Float.parseFloat(splittedLine[4]);
                    Float revenue = Float.parseFloat(splittedLine[5]);

                    instanceTypeList.add(new InstanceType(instanceName, vCPU, ecu, ram, net, revenue));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public InstanceType getRandomInstanceType(){
        return instanceTypeList.get(random.nextInt(instanceTypeList.size()));
    }
}
