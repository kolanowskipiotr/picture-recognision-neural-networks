package road.signs.recognizer.config.training;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by Basia on 11.05.16.
 */
public class TrainingData {

    private double acceptedErrorLevel = 0.01;
    private PropagationType propagationType = PropagationType.ResilientPropagation;
    private double theLearnRateForManhattanPropagation;
    private List<TrainingPair> trainingSet = ImmutableList.of(
            //stop
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\1.bmp",
                    new double[]{1.0, 0.0, 0.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\2.bmp",
                    new double[]{1.0, 0.0, 0.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\3.bmp",
                    new double[]{1.0, 0.0, 0.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\5.bmp",
                    new double[]{1.0, 0.0, 0.0, 0.0}),
            //zakaz zawracania ciężarówek
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\1.bmp",
                    new double[]{0.0, 1.0, 0.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\2.bmp",
                    new double[]{0.0, 1.0, 0.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\3.bmp",
                    new double[]{0.0, 1.0, 0.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\5.bmp",
                    new double[]{0.0, 1.0, 0.0, 0.0}),
            // zakaz wjazdu
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZW_00\\1.bmp",
                    new double[]{0.0, 0.0, 1.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZW_00\\2.bmp",
                    new double[]{0.0, 0.0, 1.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZW_00\\3.bmp",
                    new double[]{0.0, 0.0, 1.0, 0.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZW_00\\5.bmp",
                    new double[]{0.0, 0.0, 1.0, 0.0}),
            // zakaz wjazdu motocykli
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWM_00\\1.bmp",
                    new double[]{0.0, 0.0, 0.0, 1.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWM_00\\2.bmp",
                    new double[]{0.0, 0.0, 0.0, 1.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWM_00\\3.bmp",
                    new double[]{0.0, 0.0, 0.0, 1.0}),
            new TrainingPair("C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWM_00\\5.bmp",
                    new double[]{0.0, 0.0, 0.0, 1.0})
    );

    public List<TrainingPair> getTrainingSet() {
        return trainingSet;
    }

    public void setTrainingSet(List<TrainingPair> trainingSet) {
        this.trainingSet = trainingSet;
    }

    public PropagationType getPropagationType() {
        return propagationType;
    }

    public void setPropagationType(PropagationType propagationType) {
        this.propagationType = propagationType;
    }

    public double getTheLearnRateForManhattanPropagation() {
        return theLearnRateForManhattanPropagation;
    }

    public void setTheLearnRateForManhattanPropagation(double theLearnRateForManhattanPropagation) {
        this.theLearnRateForManhattanPropagation = theLearnRateForManhattanPropagation;
    }

    public double getAcceptedErrorLevel() {
        return acceptedErrorLevel;
    }

    public void setAcceptedErrorLevel(double acceptedErrorLevel) {
        this.acceptedErrorLevel = acceptedErrorLevel;
    }
}
