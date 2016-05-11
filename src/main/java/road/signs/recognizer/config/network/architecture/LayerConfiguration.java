package road.signs.recognizer.config.network.architecture;

/**
 * Created by Basia on 11.05.16.
 */
public class LayerConfiguration {
    private Integer numberOfNeurons;
    private ActivationFunctionType activationFunction;

    public LayerConfiguration() {
    }

    public LayerConfiguration(int numberOfNeurons, ActivationFunctionType activationFunction) {
        this.numberOfNeurons = numberOfNeurons;
        this.activationFunction = activationFunction;
    }

    public Integer getNumberOfNeurons() {
        return numberOfNeurons;
    }

    public void setNumberOfNeurons(Integer numberOfNeurons) {
        this.numberOfNeurons = numberOfNeurons;
    }

    public ActivationFunctionType getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunctionType activationFunction) {
        activationFunction = activationFunction;
    }

}
