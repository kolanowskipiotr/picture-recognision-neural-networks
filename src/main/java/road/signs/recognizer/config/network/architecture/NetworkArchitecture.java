package road.signs.recognizer.config.network.architecture;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkArchitecture {
    private LayerConfiguration inputLayer = new LayerConfiguration(40000, null);
    private LayerConfiguration outputLayer = new LayerConfiguration(4, ActivationFunctionType.ActivationSigmoid);
    private List<LayerConfiguration> hiddenLayers = newArrayList();

    public LayerConfiguration getInputLayer() {
        return inputLayer;
    }

    public void setInputLayer(LayerConfiguration inputLayer) {
        this.inputLayer = inputLayer;
    }

    public LayerConfiguration getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(LayerConfiguration outputLayer) {
        this.outputLayer = outputLayer;
    }

    public List<LayerConfiguration> getHiddenLayers() {
        return hiddenLayers;
    }

    public void setHiddenLayers(List<LayerConfiguration> hiddenLayers) {
        this.hiddenLayers = hiddenLayers;
    }
}
