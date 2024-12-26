package backend.academy.service;

import backend.academy.domain.FractalImage;
import backend.academy.domain.Pixel;

public class Corrector {

    private static final double GAMMA = 0.8;

    public void correct(FractalImage image) {
        Pixel[][] pixels = image.pixels();
        double max = 0.0;
        int width = pixels.length;
        int height = pixels[0].length;
        double[][] normal = new double[width][height];

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                if (pixels[row][col].hitCount() != 0) {
                    normal[row][col] = Math.log10(pixels[row][col].hitCount());
                    if (normal[row][col] > max) {
                        max = normal[row][col];
                    }
                }
            }
        }
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                normal[row][col] /= max;
                int r = (int) (pixels[row][col].color().red() * Math.pow(normal[row][col], (1.0 / GAMMA)));
                int g = (int) (pixels[row][col].color().green() * Math.pow(normal[row][col], (1.0 / GAMMA)));
                int b = (int) (pixels[row][col].color().blue() * Math.pow(normal[row][col], (1.0 / GAMMA)));
                pixels[row][col].color().red(r);
                pixels[row][col].color().green(g);
                pixels[row][col].color().blue(b);
            }
        }
    }

}
