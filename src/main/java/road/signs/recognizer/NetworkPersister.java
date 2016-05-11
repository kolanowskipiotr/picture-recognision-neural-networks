package road.signs.recognizer;

import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.util.obj.SerializeObject;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkPersister {

    private static final String FILENAME = "encogexample.ser";

    public static void persistNetwork(BasicNetwork network, MLDataSet trainingSet){
        double e = network.calculateError(trainingSet);
        System.out.println("Network traiined to error: " + e);
        System.out.println("Saving network");
        try {
            SerializeObject.save(new File(FILENAME), network);
        } catch (IOException e1) {
            System.out.println("Saving network FAILED!");
            e1.printStackTrace();
        }
    }

    public static Optional<BasicNetwork> loadAndEvaluate(String networkFilePath) {
        System.out.println("Loading network");
        Optional<BasicNetwork> network = Optional.empty();
        try {
            network = Optional.ofNullable((BasicNetwork) SerializeObject.load(new File(networkFilePath)));
        } catch (IOException e) {
            System.out.println("Restoring network FAILED!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Restoring network FAILED!");
            e.printStackTrace();
        }

        return network;
    }
}
