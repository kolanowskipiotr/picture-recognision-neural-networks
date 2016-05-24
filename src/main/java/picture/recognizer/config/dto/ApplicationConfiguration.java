package picture.recognizer.config.dto;

import picture.recognizer.config.dto.network.architecture.NetworkArchitecture;
import picture.recognizer.config.dto.training.TrainingData;
import picture.recognizer.config.dto.testing.TestingData;

/**
 * Created by Basia on 11.05.16.
 */
public class ApplicationConfiguration {

    //jeśli to pole jest puste to sieć będzie zbudowana na podstawie podanej architektury umieszczonej w networkArchitecture;
    private String loadNetworkPath;
    private NetworkArchitecture networkArchitecture = new NetworkArchitecture();
    private boolean saveNetwork;

    //Jeśli nei będzie tych danych to aplikacja nie będzie trenować sieci. Pozwala to na pominięcie tego kroku
    private TrainingData trainingData = new TrainingData();

    //Jeśli nei będzie tych danych to aplikacja nie będzie testować sieci. Pozwala to na pominięcie tego kroku
    private TestingData testingData = new TestingData();

    //jeśłi włączone od wagi piksela będzie odjęte -16777216
    private boolean alphaChanelEnabled;

    public boolean isSaveNetwork() {
        return saveNetwork;
    }

    public void setSaveNetwork(boolean saveNetwork) {
        this.saveNetwork = saveNetwork;
    }

    public TestingData getTestingData() {
        return testingData;
    }

    public void setTestingData(TestingData testingData) {
        this.testingData = testingData;
    }

    public void setTrainingData(TrainingData trainingData) {
        this.trainingData = trainingData;
    }

    public NetworkArchitecture getNetworkArchitecture() {
        return networkArchitecture;
    }

    public void setNetworkArchitecture(NetworkArchitecture networkArchitecture) {
        this.networkArchitecture = networkArchitecture;
    }

    public String getLoadNetworkPath() {
        return loadNetworkPath;
    }

    public void setLoadNetworkPath(String loadNetworkPath) {
        this.loadNetworkPath = loadNetworkPath;
    }

    public TrainingData getTrainingData() {
        return trainingData;
    }

    public boolean isAlphaChanelEnabled() {
        return alphaChanelEnabled;
    }

    public void setAlphaChanelEnabled(boolean alphaChanelEnabled) {
        this.alphaChanelEnabled = alphaChanelEnabled;
    }
}
