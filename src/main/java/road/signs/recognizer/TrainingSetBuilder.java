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


    private static final List<String> INPUT_DATA = ImmutableList.of(
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\1.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\2.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\3.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\4.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\S_00\\5.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\1.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\2.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\3.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\4.bmp",
            "C:\\Users\\Basia\\IdeaProjects\\road signs recognizer\\learning_set\\ZWC_00\\5.bmp"
    );
    private static final double[][] IDEAL = {
            {1.0, 0.0},
            {1.0, 0.0},
            {1.0, 0.0},
            {1.0, 0.0},
            {1.0, 0.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0}
    };

    public static BasicMLDataSet build(){
        List<double[]> readedPictures = INPUT_DATA.stream()
                .map(path -> new File(path))
                .map(file -> readImage(file))
                .map(image -> convertTo2DWithoutUsingGetRGB(image))
                .map(arrayOfInts -> Ints.concat(arrayOfInts))
                .map(flatArrayOfInts -> convertToDoublesAndShrinkValues(flatArrayOfInts))
                .collect(Collectors.toList());

        return new BasicMLDataSet(to2dArray(readedPictures), IDEAL);
    }

    private static double[][] to2dArray(List<double[]> list) {
        double[][] matrix=new double[list.size()][];
        matrix=list.toArray(matrix);
        return matrix;
    }

    private static double[] convertToDoublesAndShrinkValues(int[] flatArrayOfInts) {
        return Arrays.stream(flatArrayOfInts)
                .asDoubleStream()
                .map(pixel -> pixel/(256*256*256))
                .boxed()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    private static BufferedImage readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

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
}
