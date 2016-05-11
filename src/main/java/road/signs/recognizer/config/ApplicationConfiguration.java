package road.signs.recognizer.config;

import road.signs.recognizer.config.network.architecture.NetworkArchitecture;
import road.signs.recognizer.config.training.TrainingData;

/**
 * Created by Basia on 11.05.16.
 */
public class ApplicationConfiguration {

    private NetworkArchitecture networkArchitecture = new NetworkArchitecture();
    private String loadNetworkPath; //;= "encogexample.ser";
    private boolean saveNetwork;
    private TrainingData trainingData = new TrainingData();
    private TestingData testingData = new TestingData();

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
}
