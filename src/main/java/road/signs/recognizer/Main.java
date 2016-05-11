package road.signs.recognizer;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.plugin.system.SystemActivationPlugin;

import java.util.Arrays;

/**
 * CreatedbyBasiaon10.05.16.
 */
public class Main {


    public static void main(String[] args) {

        BasicNetwork network = buildNetwork();
        MLDataSet trainingSet = TrainingSetBuilder.build();
        ResilientPropagation trainer = builtNetworkTrainer(network, trainingSet);

        trainNetwork(trainer);

        trainingSet.forEach(queryData -> askNetwork(network, queryData));

        Encog.getInstance().shutdown();
    }

    private static void askNetwork(BasicNetwork network, MLDataPair queryData) {
        final MLData output = network.compute(queryData.getInput());
        System.out.println("recognize this: "
                + "\n , network responded=" + output.getData(0) + " " + output.getData(1)
                + "\n , should respond=" + queryData.getIdeal().getData(0) + " " + queryData.getIdeal().getData(1));

    }

    private static void trainNetwork(ResilientPropagation trainer) {
        double[] results = {1.0, 1.0, 1.0};
        int epoch = 1;
        double errorLevel = 0.01;
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

    private static void displayTrainingErrorLevel(int epoch, double error) {
        System.out.println("Epoch #" + epoch + " Error: " + error);
    }

    private static ResilientPropagation builtNetworkTrainer(BasicNetwork network, MLDataSet trainingSet) {
        return new ResilientPropagation(network, trainingSet);
    }

    private static BasicNetwork buildNetwork() {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 40000));
        //network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 5));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 2));
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }
}
