package picture.recognizer.config.dto.network.architecture;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Basia on 11.05.16.
 */
public class NetworkArchitecture {
    //domyślnie używałem wartości = new LayerConfiguration(40000, null) jeśli obrazek 200x200 to 40000 neuronów
    // wejściowe zawsze mają null w activationFunction
    private LayerConfiguration inputLayer;
    //domyślnie używałem wartości = new LayerConfiguration(4, ActivationFunctionType.ActivationSigmoid)
    private LayerConfiguration outputLayer;
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
