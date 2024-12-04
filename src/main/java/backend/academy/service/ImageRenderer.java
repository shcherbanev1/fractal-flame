package backend.academy.service;

import backend.academy.domain.FractalImage;
import backend.academy.domain.Pixel;
import backend.academy.type.ImageFormat;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageRenderer {

    private static final int RED_CODING_SHIFT = 16;
    private static final int GREEN_CODING_SHIFT = 8;

    public void renderPixels(FractalImage fractalImage, String outputPath, ImageFormat format) throws IOException {

        Pixel[][] pixels = fractalImage.pixels();
        int width = pixels.length;
        int height = pixels[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Pixel pixel = pixels[x][y];
                int r = pixel.r();
                int g = pixel.g();
                int b = pixel.b();
                int rgb = (r << RED_CODING_SHIFT) | (g << GREEN_CODING_SHIFT) | b;
                image.setRGB(x, y, rgb);
            }
        }

        File outputFile = new File(outputPath);
        ImageIO.write(image, format.name(), new File(outputFile + "." + format.format()));
    }

}
