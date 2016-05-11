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

    /**
     * TheinputnecessaryforXOR.
     */
    public static double XORINPUT[][] = {
            {0.0, 0.0},
            {1.0, 0.0},
            {0.0, 1.0},
            {1.0, 1.0}};
    /**
     * TheidealdatanecessaryforXOR.
     */
    public static double XORIDEAL[][] = {
            {0.0},
            {1.0},
            {1.0},
            {0.0}};


    public static void main(String[] args) {

        BasicNetwork network = buildNetwork();
        MLDataSet trainingSet = buildTraingSet();
        ResilientPropagation trainer = builtNetworkTrainer(network, trainingSet);

        trainNetwork(trainer);

        trainingSet.forEach(queryData -> askNetwork(network, queryData));

        Encog.getInstance().shutdown();
    }

    private static void askNetwork(BasicNetwork network, MLDataPair queryData) {
        final MLData output = network.compute(queryData.getInput());
        System.out.println("XOR this: " + queryData.getInput().getData(0) + " , " + queryData.getInput().getData(1)
                + "\n , network responded=" + output.getData(0)
                + "\n , should respond=" + queryData.getIdeal().getData(0));

    }

    private static void trainNetwork(ResilientPropagation trainer) {
        double[] results = {1.0, 1.0, 1.0};
        int epoch = 1;
        do {
            trainer.iteration();
            displayTrainingErrorLevel(epoch, trainer.getError());
            epoch++;
            results[epoch%3] = trainer.getError();
        } while (results[0] > 0.01 || results[1] > 0.01 || results[2] > 0.01);
        trainer.finishTraining();
    }

    private static void displayTrainingErrorLevel(int epoch, double error) {
        System.out.println("Epoch #" + epoch + " Error: " + error);
    }

    private static ResilientPropagation builtNetworkTrainer(BasicNetwork network, MLDataSet trainingSet) {
        return new ResilientPropagation(network, trainingSet);
    }

    private static BasicMLDataSet buildTraingSet() {
        return new BasicMLDataSet(XORINPUT, XORIDEAL);
    }

    private static BasicNetwork buildNetwork() {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 2));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 3));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 1));
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }
}
