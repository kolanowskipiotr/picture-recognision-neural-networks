package road.signs.recognizer;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import org.encog.ml.data.basic.BasicMLDataSet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Basia on 11.05.16.
 */
public class TrainingSetBuilder {


    private static final String PATH_TO_TRAINING_DATA_FOLDER = "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set";
    private static final List<String> INPUT_DATA_TRAINING = ImmutableList.of(
            //stop
            PATH_TO_TRAINING_DATA_FOLDER + "\\S_00\\1.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\S_00\\2.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\S_00\\3.bmp",
            //PATH_TO_TRAINING_DATA_FOLDER + "\\S_00\\4.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\S_00\\5.bmp",
            //zakaz zawracania ciężarówek
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWC_00\\1.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWC_00\\2.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWC_00\\3.bmp",
            //PATH_TO_TRAINING_DATA_FOLDER + "\\ZWC_00\\4.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWC_00\\5.bmp",
            // zakaz wjazdu
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZW_00\\1.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZW_00\\2.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZW_00\\3.bmp",
            //PATH_TO_TRAINING_DATA_FOLDER + "\\ZW_00\\4.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZW_00\\5.bmp",
            // zakaz wjazdu motocykli
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWM_00\\1.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWM_00\\2.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWM_00\\3.bmp",
            //PATH_TO_TRAINING_DATA_FOLDER + "\\ZWM_00\\4.bmp",
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWM_00\\5.bmp"
    );
    private static final List<String> INPUT_DATA_TESTING = ImmutableList.of(
            //stop
            PATH_TO_TRAINING_DATA_FOLDER + "\\S_00\\4.bmp",
            //zakaz zawracania ciężarówek
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWC_00\\4.bmp",
            // zakaz wjazdu
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZW_00\\4.bmp",
            // zakaz wjazdu motocykli
            PATH_TO_TRAINING_DATA_FOLDER + "\\ZWM_00\\4.bmp"
    );

    public static final int STOP_OUTPUT_PICK_INDEX = 0;
    public static final int NO_ENER_FOR_TRUCKS_OUTPUT_PICK_INDEX = 1;
    public static final int NO_ENTER_OUTPUT_PICK_INDEX = 2;
    public static final int NO_ENTER_FOR_MOTORCYCLES_OUTPUT_PICK_INDEX = 3;

    private static final double[][] IDEAL_TRAINING = {
            //stop
            {1.0, 0.0, 0.0, 0.0},
            {1.0, 0.0, 0.0, 0.0},
            {1.0, 0.0, 0.0, 0.0},
            {1.0, 0.0, 0.0, 0.0},
            //{1.0, 0.0, 0.0, 0.0},
            //zakaz wiazdu ciężarówek
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            //{0.0, 1.0, 0.0, 0.0},
            // zakaz wjazdu
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            //{0.0, 0.0, 1.0, 0.0},
            // zakaz wjazdu motocykli
            {0.0, 0.0, 0.0, 1.0},
            {0.0, 0.0, 0.0, 1.0},
            {0.0, 0.0, 0.0, 1.0},
            {0.0, 0.0, 0.0, 1.0}//,
            //{0.0, 0.0, 0.0, 1.0}
    };
    private static final double[][] IDEAL_TESTING = {
            //stop
            {1.0, 0.0, 0.0, 0.0},
            //zakaz wiazdu ciężarówek
            {0.0, 1.0, 0.0, 0.0},
            // zakaz wjazdu
            {0.0, 0.0, 1.0, 0.0},
            // zakaz wjazdu motocykli
            {0.0, 0.0, 0.0, 1.0}
    };

    public static BasicMLDataSet buildTestingSet(){
        return build(INPUT_DATA_TESTING, IDEAL_TESTING);
    }

    public static BasicMLDataSet buildTrainingSet(){
        return build(INPUT_DATA_TRAINING, IDEAL_TRAINING);
    }

    private static BasicMLDataSet build(List<String> inputData, double[][] ideal){
        List<double[]> readedPictures = inputData.stream()
                .map(File::new)
                .map(TrainingSetBuilder::readImage)
                .map(TrainingSetBuilder::convertTo2DWithoutUsingGetRGB)
                .map(Ints::concat)
                .map(TrainingSetBuilder::convertToDoublesAndShrinkValues)
                .collect(Collectors.toList());

        return new BasicMLDataSet(to2dArray(readedPictures), ideal);
    }

    private static BufferedImage readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * POżyczone z internetów nie wiem jak działa ale działa :P
     * W dodatku zapierdziela jak durne w porównaniu z pobieramiem każdego pixla osobno.
     * @param image
     * @return
     */
    private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                //argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

    private static double[] convertToDoublesAndShrinkValues(int[] flatArrayOfInts) {
        return Arrays.stream(flatArrayOfInts)
                .asDoubleStream()
                .map(TrainingSetBuilder::normalizePixelValue)
                .boxed()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    private static double normalizePixelValue(double pixel) {
        return pixel/(256*256*256);
    }

    private static double[][] to2dArray(List<double[]> list) {
        double[][] matrix=new double[list.size()][];
        matrix=list.toArray(matrix);
        return matrix;
    }
}
