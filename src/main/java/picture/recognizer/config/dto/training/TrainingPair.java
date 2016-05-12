package picture.recognizer.config.dto.training;

/**
 * Created by Basia on 11.05.16.
 */
public class TrainingPair {

    private String imagePath;
    private double[] ideal;

    public TrainingPair(String imagePath, double[] ideal) {
        this.imagePath = imagePath;
        this.ideal = ideal;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double[] getIdeal() {
        return ideal;
    }

    public void setIdeal(double[] ideal) {
        this.ideal = ideal;
    }
}
