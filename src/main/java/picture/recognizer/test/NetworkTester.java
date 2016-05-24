package picture.recognizer.test;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import picture.recognizer.config.dto.ApplicationConfiguration;
import picture.recognizer.config.dto.testing.TestingData;
import picture.recognizer.train.TrainingSetBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkTester {

    public static void test(ApplicationConfiguration appConfig, BasicNetwork network) {
        System.out.println("Testing network");

        Optional<TestingData> testingDataOpt = Optional.ofNullable(appConfig.getTestingData());
        if(testingDataOpt.isPresent()) {

            TestingData testingData = testingDataOpt.get();
            MLDataSet testingSet = TrainingSetBuilder.buildTestingSet(testingData, appConfig.isAlphaChanelEnabled());
            testingSet.forEach(testingPair -> askNetwork(network, testingPair, testingData.getOutputLabels()));
        } else {
            System.out.println("Network was not tested");
        }
    }

    private static void askNetwork(BasicNetwork network, MLDataPair queryData, List<String> outputLabels) {
        final MLData output = network.compute(queryData.getInput());
        System.out.println("Test: "
                + "\n    network response ["+ getLabel(output.getData(), outputLabels) +"] = "
                + neuronsValueToString(output.getData())
                + "\n    should respond ["+ getLabel(queryData.getIdeal().getData(), outputLabels) +"] = "
                + neuronsValueToString(queryData.getIdeal().getData())
        );
    }

    private static String neuronsValueToString(double[] neuronsValues) {
        return Arrays.stream(neuronsValues).boxed()
                .map(NetworkTester::round)
                .map(d -> d.toString())
                .collect(Collectors.joining(" "));
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
        return Math.round(number * 10000000d) / 10000000d;
    }
}
