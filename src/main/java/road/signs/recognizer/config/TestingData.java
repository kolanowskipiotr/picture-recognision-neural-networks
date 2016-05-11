package road.signs.recognizer.config;

import com.google.common.collect.ImmutableList;
import road.signs.recognizer.config.training.TrainingPair;

import java.util.List;

/**
 * Created by Basia on 11.05.16.
 */
public class TestingData {

    private List<TrainingPair> testingSet = ImmutableList.of(
            //stop
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\4.bmp",
                    new double[]{1.0, 0.0, 0.0, 0.0}),
            //zakaz zawracania ciężarówek
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\4.bmp",
                    new double[]{0.0, 1.0, 0.0, 0.0}),
            // zakaz wjazdu
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZW_00\\4.bmp",
                    new double[]{0.0, 0.0, 1.0, 0.0}),
            // zakaz wjazdu motocykli
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWM_00\\4.bmp",
                    new double[]{0.0, 0.0, 0.0, 1.0})
    );

    private List<String> outputLabels = ImmutableList.of(
            "stop", "zakaz zawracania ciężarówek", "zakaz wjazdu", "zakaz wjazdu motocykli"
    );

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
