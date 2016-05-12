package picture.recognizer.train;

import com.google.common.primitives.Ints;
import org.encog.ml.data.basic.BasicMLDataSet;
import picture.recognizer.config.dto.training.TrainingPair;

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

    public static BasicMLDataSet buildTestingSet(List<TrainingPair> testingData){
        return build(getInputData(testingData), getIdealData(testingData));
    }

    public static BasicMLDataSet buildTrainingSet(List<TrainingPair> trainingData){
        return build(getInputData(trainingData), getIdealData(trainingData));
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

    private static double[][] getIdealData(List<TrainingPair> trainingData) {
        return to2dArray(trainingData.stream()
                .map(TrainingPair::getIdeal)
                .collect(Collectors.toList()));
    }

    private static List<String> getInputData(List<TrainingPair> trainingData) {
        return trainingData.stream()
                .map(TrainingPair::getImagePath)
                .collect(Collectors.toList());
    }
}
