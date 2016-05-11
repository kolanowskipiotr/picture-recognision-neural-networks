package road.signs.recognizer;

import org.apache.commons.lang3.StringUtils;
import org.encog.Encog;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import road.signs.recognizer.config.ApplicationConfiguration;

import java.util.Optional;

import static road.signs.recognizer.TrainingSetBuilder.*;

/**
 * CreatedbyBasiaon10.05.16.
 */
public class Main {

    public static void main(String[] args) {

        Optional<ApplicationConfiguration> appConfigOpt = ConfigurationService.loadConfiguration();
        if(appConfigOpt.isPresent()) {
            ApplicationConfiguration appConfig = appConfigOpt.get();

            Optional<BasicNetwork> networkOpt = getNetwork(appConfig);

            if(networkOpt.isPresent()) {
                BasicNetwork network = networkOpt.get();
                MLDataSet trainingSet = TrainingSetBuilder.buildTrainingSet();
                NetworkTrainer.train(network, trainingSet, NetworkTrainer.DEFAULT_ERROR_LEVEL);

                MLDataSet testingSet = TrainingSetBuilder.buildTestingSet();
                testingSet.forEach(queryData -> askNetwork(network, queryData));

                NetworkPersister.persistNetwork(network, trainingSet);
            }
        } else{
            ConfigurationService.saveConfiguration(new ApplicationConfiguration());
        }

        ConfigurationService.saveConfiguration(new ApplicationConfiguration());
        Encog.getInstance().shutdown();
    }

    private static Optional<BasicNetwork> getNetwork(ApplicationConfiguration appConfig) {
        Optional<BasicNetwork> networkOpt = null;
        if(StringUtils.isNoneBlank(appConfig.getLoadNetworkPath())) {
            networkOpt = NetworkPersister.loadAndEvaluate(appConfig.getLoadNetworkPath());
        } else {
            networkOpt = NetworkBuilder.build(appConfig.getNetworkArchitecture());
        }
        return networkOpt;
    }


    private static void askNetwork(BasicNetwork network, MLDataPair queryData) {
        final MLData output = network.compute(queryData.getInput());
        System.out.println("Test: "
                + "\n    network response ["+ signTyoe(output.getData()) +"] = "
                    + round(output.getData(0)) + " "
                    + round(output.getData(1)) + " "
                    + round(output.getData(2)) + " "
                    + round(output.getData(3))
                + "\n    should respond ["+ signTyoe(queryData.getIdeal().getData()) +"] = "
                    + queryData.getIdeal().getData(0) + " "
                    + queryData.getIdeal().getData(1) + " "
                    + queryData.getIdeal().getData(2) + " "
                    + queryData.getIdeal().getData(3));
    }

    private static String signTyoe(double[] data) {
        int indexOfPick = Integer.MIN_VALUE;
        double loclMax = Double.MIN_VALUE;
        for(int i = 0; i < data.length; i++){
            if(loclMax < data[i]){
                loclMax = data[i];
                indexOfPick = i;
            }
        }

        switch (indexOfPick){
            case STOP_OUTPUT_PICK_INDEX:{
                return "Stop";
            }
            case NO_ENER_FOR_TRUCKS_OUTPUT_PICK_INDEX:{
                return "Zakaz wjazdu dla ciężarówek";
            }
            case NO_ENTER_FOR_MOTORCYCLES_OUTPUT_PICK_INDEX:{
                return "Zakaz wjazdu dla motocykli";
            }
            case NO_ENTER_OUTPUT_PICK_INDEX:{
                return "Zakaz wjazdu";
            }
            default:{
                return "Nie udało się określić";
            }
        }
    }

    private static double round(double number) {
        return Math.round(number * 1000d) / 1000d;
    }
}
