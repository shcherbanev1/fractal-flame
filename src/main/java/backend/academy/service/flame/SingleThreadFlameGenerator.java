package backend.academy.service.flame;

import backend.academy.domain.Color;
import backend.academy.domain.FractalImage;
import backend.academy.domain.Pixel;
import backend.academy.domain.Rect;
import backend.academy.domain.transformation.Transformation;
import backend.academy.domain.transformation.impl.AffineTransformation;
import backend.academy.type.Config;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("CPD-START")
@Slf4j
public class SingleThreadFlameGenerator extends FlameGenerator {

    @Override
    public FractalImage generate(
        List<AffineTransformation> affine,
        List<Transformation> nonLinear,
        List<Color> colors,
        Config config
    ) {
        FractalImage image = new FractalImage(config.imageWidth(), config.imageHeight());
        Rect world = new Rect(-config.xConstraint(), -config.yConstraint(), config.xConstraint(), config.yConstraint());
        Random random = new SecureRandom();

        for (int i = 0; i < config.totalPoints(); i++) {
            generatePart(
                config.iterationsForPoint(),
                affine,
                nonLinear,
                colors,
                config,
                image,
                world,
                random);
            if (i % (config.totalPoints() / MILESTONE_FOR_LOG) == 0) {
                log.trace("Dot - {}", i);
            }
        }
        return image;
    }

    @Override
    void addPixelColor(Pixel pixel, Color color, Config config) {
        if (pixel.x() <= config.imageWidth() && pixel.y() <= config.imageHeight()) {
            pixel.addColor(color);
        }
    }

}
