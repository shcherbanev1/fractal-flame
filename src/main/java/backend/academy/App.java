package backend.academy;

import backend.academy.domain.Color;
import backend.academy.domain.FractalImage;
import backend.academy.domain.transformation.Transformation;
import backend.academy.domain.transformation.impl.AffineTransformation;
import backend.academy.service.Corrector;
import backend.academy.service.ImageRenderer;
import backend.academy.service.flame.FlameGenerator;
import backend.academy.service.generator.RandomGenerator;
import backend.academy.service.generator.impl.AffineTransformationGenerator;
import backend.academy.service.generator.impl.HSVColorGenerator;
import backend.academy.type.Config;
import java.io.IOException;
import java.util.List;

public class App {

    private static final double LOWER_COEF = -1.0;
    private static final double UPPER_COEF = 1.0;

    private final Config config;
    private final int affineNumber;
    private final int colorNumber;
    private final List<Transformation> nonLinear;
    private final FlameGenerator flameGenerator;

    public App(
        Config config,
        int affineNumber,
        int colorNumber,
        List<Transformation> nonLinear,
        FlameGenerator flameGenerator
    ) {
        this.config = config;
        this.affineNumber = affineNumber;
        this.colorNumber = colorNumber;
        this.nonLinear = nonLinear;
        this.flameGenerator = flameGenerator;
    }

    public void run() {
        FractalImage fractalImage = prepareImageGeneration();

        ImageRenderer imageRenderer = new ImageRenderer();
        try {
            imageRenderer.renderPixels(fractalImage, "src/main/resources/image", config.format());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Corrector corrector = new Corrector();
        corrector.correct(fractalImage);
        try {
            imageRenderer.renderPixels(fractalImage, "src/main/resources/image-corrected", config.format());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FractalImage prepareImageGeneration() {
        AffineTransformationGenerator affineTransformationGenerator =
            new AffineTransformationGenerator(LOWER_COEF, UPPER_COEF);
        List<AffineTransformation> affine = affineTransformationGenerator.generate(affineNumber);

        RandomGenerator<Color> colorGenerator = new HSVColorGenerator();
        List<Color> colors = colorGenerator.generate(colorNumber);

        return flameGenerator.generate(affine, nonLinear, colors, config);
    }

}
