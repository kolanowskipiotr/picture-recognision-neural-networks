package road.signs.recognizer;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import road.signs.recognizer.config.network.architecture.LayerConfiguration;
import road.signs.recognizer.config.network.architecture.NetworkArchitecture;

import java.util.List;
import java.util.Optional;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkBuilder {

    public static Optional<BasicNetwork> build(NetworkArchitecture data) {
        System.out.println("Building network");

        Optional<BasicNetwork> networkOpt = Optional.empty();
        if(isNetworkArchitectureValid(data)) {
            networkOpt = Optional.of(createNetwork(data));
        }else{
            dislayValidationError();
        }
        return networkOpt;
    }

    private static boolean isNetworkArchitectureValid(NetworkArchitecture data) {
        return data != null && data.getInputLayer() != null && data.getOutputLayer() != null;
    }

    private static BasicNetwork createNetwork(NetworkArchitecture data) {
        BasicNetwork network = new BasicNetwork();

        network.addLayer(createInputLayer(data.getInputLayer()));
        createHiddenLayers(data.getHiddenLayers(), network);
        network.addLayer(createOutputLayer(data.getOutputLayer()));

        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }

    private static BasicLayer createInputLayer(LayerConfiguration inputLayer) {
        return new BasicLayer(null, true, inputLayer.getNumberOfNeurons());
    }

    private static void createHiddenLayers(List<LayerConfiguration> hiddenLayers, BasicNetwork network) {
        hiddenLayers.forEach(
                layer -> network.addLayer(
                        new BasicLayer(layer.getActivationFunction().getInstance(), true, layer.getNumberOfNeurons())
                ));
    }

    private static BasicLayer createOutputLayer(LayerConfiguration outputLayerData) {
        return new BasicLayer(outputLayerData.getActivationFunction().getInstance(), true, outputLayerData.getNumberOfNeurons());
    }

    private static void dislayValidationError() {
        System.out.println("[Error] NetworkArchitecture not valid!");
    }

}
