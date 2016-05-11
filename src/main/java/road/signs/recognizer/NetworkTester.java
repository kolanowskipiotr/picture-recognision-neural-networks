package road.signs.recognizer;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import road.signs.recognizer.config.ApplicationConfiguration;
import road.signs.recognizer.config.testing.TestingData;

import java.util.List;
import java.util.Optional;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkTester {

    public static void test(ApplicationConfiguration appConfig, BasicNetwork network) {
        System.out.println("Testing network");

        Optional<TestingData> testingDataOpt = Optional.ofNullable(appConfig.getTestingData());
        if(testingDataOpt.isPresent()) {

            TestingData testingData = testingDataOpt.get();
            MLDataSet testingSet = TrainingSetBuilder.buildTestingSet(testingData.getTestingSet());
            testingSet.forEach(testingPair -> askNetwork(network, testingPair, testingData.getOutputLabels()));
        } else {
            System.out.println("Network was not tested");
        }
    }

    private static void askNetwork(BasicNetwork network, MLDataPair queryData, List<String> outputLabels) {
        final MLData output = network.compute(queryData.getInput());
        System.out.println("Test: "
                + "\n    network response ["+ getLabel(output.getData(), outputLabels) +"] = "
                + round(output.getData(0)) + " "
                + round(output.getData(1)) + " "
                + round(output.getData(2)) + " "
                + round(output.getData(3))
                + "\n    should respond ["+ getLabel(queryData.getIdeal().getData(), outputLabels) +"] = "
                + queryData.getIdeal().getData(0) + " "
                + queryData.getIdeal().getData(1) + " "
                + queryData.getIdeal().getData(2) + " "
                + queryData.getIdeal().getData(3));
    }

    private static String getLabel(double[] data, List<String> outputLabels) {
        int indexOfPick = Integer.MIN_VALUE;
        double loclMax = Double.MIN_VALUE;
        for(int i = 0; i < data.length; i++){
            if(loclMax < data[i]){
                loclMax = data[i];
                indexOfPick = i;
            }
        }

        return outputLabels.get(indexOfPick);
    }

    private static double round(double number) {
        return Math.round(number * 1000d) / 1000d;
    }
}
