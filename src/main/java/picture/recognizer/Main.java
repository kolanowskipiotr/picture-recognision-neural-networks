package picture.recognizer;

import org.apache.commons.lang3.StringUtils;
import org.encog.Encog;
import org.encog.neural.networks.BasicNetwork;
import picture.recognizer.build.NetworkBuilder;
import picture.recognizer.config.dto.ApplicationConfiguration;
import picture.recognizer.config.service.ConfigurationService;
import picture.recognizer.persist.NetworkPersister;
import picture.recognizer.test.NetworkTester;
import picture.recognizer.train.NetworkTrainer;

import java.util.Optional;

/**
 * CreatedbyBasiaon10.05.16.
 */
public class Main {

    public static void main(String[] args) {

        Optional<ApplicationConfiguration> appConfigOpt = ConfigurationService.loadConfiguration();
        if(appConfigOpt.isPresent()) {

            Optional<BasicNetwork> networkOpt = getNetwork(appConfigOpt.get());
            if(networkOpt.isPresent()) {

                workWithNetwork(appConfigOpt.get(), networkOpt.get());
            } else {

                System.out.println("\n[ERROR] Network load or creation failed!");
            }
        } else{

            System.out.println("\n[ERROR] No configuration file!");
            //ConfigurationService.saveConfiguration(new ApplicationConfiguration());
        }
        
        Encog.getInstance().shutdown();
    }

    private static Optional<BasicNetwork> getNetwork(ApplicationConfiguration appConfig) {
        Optional<BasicNetwork> networkOpt = null;
        if(StringUtils.isNoneBlank(appConfig.getLoadNetworkPath())) {
            networkOpt = NetworkPersister.loadNetwork(appConfig.getLoadNetworkPath());
        } else {
            networkOpt = NetworkBuilder.build(appConfig.getNetworkArchitecture());
        }
        return networkOpt;
    }

    private static void workWithNetwork(ApplicationConfiguration appConfig, BasicNetwork network) {

        NetworkTrainer.train(appConfig, network);
        NetworkPersister.persist(appConfig, network);
        NetworkTester.test(appConfig, network);
    }
}
