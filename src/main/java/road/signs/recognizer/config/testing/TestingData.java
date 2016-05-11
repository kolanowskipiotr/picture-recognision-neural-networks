package road.signs.recognizer.config.testing;

import road.signs.recognizer.config.training.TrainingPair;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Basia on 11.05.16.
 */
public class TestingData {

    private List<TrainingPair> testingSet = newArrayList();

    //musi być tyle stringów ile jest neuronów wyjściowych
    private List<String> outputLabels = newArrayList();

    public List<TrainingPair> getTestingSet() {
        return testingSet;
    }

    public void setTestingSet(List<TrainingPair> testingSet) {
        this.testingSet = testingSet;
    }

    public List<String> getOutputLabels() {
        return outputLabels;
    }

    public void setOutputLabels(List<String> outputLabels) {
        this.outputLabels = outputLabels;
    }
}
