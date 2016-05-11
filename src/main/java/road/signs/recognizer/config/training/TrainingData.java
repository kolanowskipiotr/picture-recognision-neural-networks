package road.signs.recognizer.config.training;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Basia on 11.05.16.
 */
public class TrainingData {

    private double acceptedErrorLevel;


    /** taiej do tej pory używaliśy PropagationType.ResilientPropagation; ale dostępne wartości to:
     ManhattanPropagation
     ResilientPropagation
     Backpropagation
     ScaledConjugateGradient
     QuickPropagation
     */
    private PropagationType propagationType;

    // To pole jest wymagane jeśli wybierasz ManhattanPropagation
    private double theLearnRateForManhattanPropagation;
    private List<TrainingPair> trainingSet = newArrayList();

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
