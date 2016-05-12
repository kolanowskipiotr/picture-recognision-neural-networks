package picture.recognizer.train;

import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.Propagation;
import picture.recognizer.config.dto.ApplicationConfiguration;
import picture.recognizer.config.dto.training.TrainingData;

import java.util.Optional;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkTrainer {

    public static void train(ApplicationConfiguration appConfig, BasicNetwork network) {
        System.out.println("Training network");

        Optional<TrainingData> trainingDataOpt = Optional.ofNullable(appConfig.getTrainingData());
        if(trainingDataOpt.isPresent()) {

            TrainingData trainingData = trainingDataOpt.get();
            NetworkTrainer.doTraing(network, trainingData);
        } else {

            System.out.println("No training for network");
        }
    }

    private static void doTraing(BasicNetwork network, TrainingData trainingData){
        if(isDataValid(trainingData)){

            MLDataSet trainingSet = TrainingSetBuilder.buildTrainingSet(trainingData.getTrainingSet());
            trainNetwork(network, trainingData, trainingSet);
        }else{

            displayDataValidationError();
        }
    }

    private static void trainNetwork(BasicNetwork network, TrainingData trainingData, MLDataSet trainingSet) {
        double[] results = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        int epoch = 1;
        Propagation trainer = builtNetworkTrainer(network, trainingSet, trainingData);
        do {
            trainer.iteration();
            displayTrainingErrorLevel(epoch, trainer.getError());
            epoch++;
            results[epoch%3] = trainer.getError();
        } while (results[0] > trainingData.getAcceptedErrorLevel() ||
                results[1] > trainingData.getAcceptedErrorLevel() ||
                results[2] > trainingData.getAcceptedErrorLevel());

        trainer.finishTraining();
    }

    private static boolean isDataValid(TrainingData trainingData) {
        return trainingData.getAcceptedErrorLevel() > 0.0;
    }

    private static Propagation builtNetworkTrainer(BasicNetwork network, MLDataSet trainingSet, TrainingData trainingData) {
        return trainingData.getPropagationType().getInstance(
                network,
                trainingSet,
                trainingData.getTheLearnRateForManhattanPropagation());
    }

    private static void displayTrainingErrorLevel(int epoch, double error) {
        System.out.println("Epoch #" + epoch + " Error level: " + error);
    }

    private static void displayDataValidationError() {
        System.out.println("\n[ERROR] TrainingData not valid!");
    }
}
