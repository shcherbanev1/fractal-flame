package backend.academy.domain;

import lombok.Getter;

@Getter
public class FractalImage {

    private final Pixel[][] pixels;
    private final int width;
    private final int height;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Pixel pixel = new Pixel(i, j);
                pixels[i][j] = pixel;
            }
        }
    }

}
