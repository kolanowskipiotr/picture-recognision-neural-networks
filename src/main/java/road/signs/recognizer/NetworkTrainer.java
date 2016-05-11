package road.signs.recognizer;

import org.encog.ml.data.MLDataSet;
import org.encog.ml.ea.train.EvolutionaryAlgorithm;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkTrainer {

    public static double DEFAULT_ERROR_LEVEL = 0.01;

    public static void train(BasicNetwork network, MLDataSet trainingSet, double errorLevel){
        double[] results = {1.0, 1.0, 1.0};
        int epoch = 1;
        ResilientPropagation trainer = builtNetworkTrainer(network, trainingSet);
        do {
            trainer.iteration();
            displayTrainingErrorLevel(epoch, trainer.getError());
            epoch++;
            results[epoch%3] = trainer.getError();
        } while (results[0] > errorLevel ||
                results[1] > errorLevel ||
                results[2] > errorLevel);

        trainer.finishTraining();
    }


    private static ResilientPropagation builtNetworkTrainer(BasicNetwork network, MLDataSet trainingSet) {
        return new ResilientPropagation(network, trainingSet);
    }

    private static void displayTrainingErrorLevel(int epoch, double error) {
        System.out.println("Epoch #" + epoch + " Error: " + error);
    }
}
