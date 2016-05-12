package picture.recognizer;

import org.encog.neural.networks.BasicNetwork;
import org.encog.util.obj.SerializeObject;
import picture.recognizer.config.ApplicationConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkPersister {

    private static final String FILENAME = "encogexample.ser";

    public static void persist(ApplicationConfiguration appConfig, BasicNetwork network) {
        if(appConfig.isSaveNetwork()) {

            NetworkPersister.saveNetwork(network);
        } else {

            System.out.println("Network was not saved");
        }
    }

    public static Optional<BasicNetwork> loadNetwork(String networkFilePath) {
        System.out.println("Loading network");
        Optional<BasicNetwork> network = Optional.empty();
        try {
            network = Optional.ofNullable((BasicNetwork) SerializeObject.load(new File(networkFilePath)));
        } catch (IOException e) {
            System.out.println("[Error] Restoring network FAILED!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("[Error] Restoring network FAILED!");
            e.printStackTrace();
        }

        return network;
    }

    private static void saveNetwork(BasicNetwork network){
        System.out.println("Saving network");
        try {
            SerializeObject.save(new File(FILENAME), network);
        } catch (IOException e1) {
            System.out.println("\n[Error] Saving network FAILED!");
            e1.printStackTrace();
        }
    }
}
