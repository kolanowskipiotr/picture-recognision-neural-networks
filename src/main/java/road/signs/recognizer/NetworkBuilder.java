package road.signs.recognizer;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkBuilder {

    public static NetworkBuilderLayerData INPUT_LAYER_FOR_200x200_PICTURE = new NetworkBuilderLayerData(40000);
    public static NetworkBuilderLayerData OUTPUT_LAYER_FOR_4_TYPES_OF_PICTURES = new NetworkBuilderLayerData(4);

    public static BasicNetwork build(NetworkBuilderData data) {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, data.getInputLayer().getNumberOfNeurons()));
        data.getHiddenLayers()
                .forEach(layer -> network
                        .addLayer(new BasicLayer(new ActivationSigmoid(), true, layer.getNumberOfNeurons())
                        ));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, data.getOutputLayer().getNumberOfNeurons()));
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }

    //boilerplate code
    public static class NetworkBuilderData{
        private NetworkBuilderLayerData inputLayer;
        private NetworkBuilderLayerData outputLayer;
        private List<NetworkBuilderLayerData> hiddenLayers = newArrayList();

        public NetworkBuilderData(NetworkBuilderLayerData inputLayer, NetworkBuilderLayerData outputLayer, List<NetworkBuilderLayerData> hiddenLayers) {
            this.inputLayer = inputLayer;
            this.outputLayer = outputLayer;
            this.hiddenLayers = hiddenLayers;
        }

        public NetworkBuilderData(NetworkBuilderLayerData inputLayer, NetworkBuilderLayerData outputLayer) {
            this.inputLayer = inputLayer;
            this.outputLayer = outputLayer;
        }

        public NetworkBuilderLayerData getInputLayer() {

            return inputLayer;
        }

        public void setInputLayer(NetworkBuilderLayerData inputLayer) {
            this.inputLayer = inputLayer;
        }

        public NetworkBuilderLayerData getOutputLayer() {
            return outputLayer;
        }

        public void setOutputLayer(NetworkBuilderLayerData outputLayer) {
            this.outputLayer = outputLayer;
        }

        public List<NetworkBuilderLayerData> getHiddenLayers() {
            return hiddenLayers;
        }

        public void setHiddenLayers(List<NetworkBuilderLayerData> hiddenLayers) {
            this.hiddenLayers = hiddenLayers;
        }
    }

    public static class NetworkBuilderLayerData {
        int numberOfNeurons;

        public NetworkBuilderLayerData(int numberOfNeurons) {
            this.numberOfNeurons = numberOfNeurons;
        }

        public int getNumberOfNeurons() {
            return numberOfNeurons;
        }

        public void setNumberOfNeurons(int numberOfNeurons) {
            this.numberOfNeurons = numberOfNeurons;
        }
    }

}
