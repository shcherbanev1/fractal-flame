package backend.academy.service.flame;

import backend.academy.domain.Color;
import backend.academy.domain.FractalImage;
import backend.academy.domain.Pixel;
import backend.academy.domain.Point;
import backend.academy.domain.Rect;
import backend.academy.domain.transformation.Transformation;
import backend.academy.domain.transformation.impl.AffineTransformation;
import backend.academy.type.Config;
import backend.academy.util.RandomUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiThreadFlameGenerator extends FlameGenerator {

    @Override
    public FractalImage generate(
        List<AffineTransformation> affine,
        List<Transformation> nonLinear,
        List<Color> colors,
        Config config
    ) {
        FractalImage image = new FractalImage(config.imageWidth(), config.imageHeight());
        Rect world = new Rect(-config.xConstraint(), -config.yConstraint(), config.xConstraint(), config.yConstraint());
        Random random = ThreadLocalRandom.current();

        AtomicInteger processedPoints = new AtomicInteger(0);
        try (ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            for (int i = 0; i < config.totalPoints(); i++) {
                service.execute(() -> {
                    generatePart(
                        config.iterationsForPoint(),
                        affine,
                        nonLinear,
                        colors,
                        config,
                        image,
                        world,
                        random);
                    int currentCount = processedPoints.incrementAndGet();
                    if (currentCount % (config.totalPoints() / 10) == 0) {
                        log.trace("Dot - {}", currentCount);
                    }
                });
            }
        }

        return image;
    }

    private void generatePart(
        int iterationsForPoint,
        List<AffineTransformation> affine,
        List<Transformation> nonLinear,
        List<Color> colors,
        Config config,
        FractalImage image,
        Rect world,
        Random random
    ) {
        Point point = randomPoint(world, random);

        for (int i = SKIP_STEPS; i < iterationsForPoint; i++) {
            int affineIndex = RandomUtils.getRandomIndex(affine, random);
            point = transformPoint(point, affine, nonLinear, random, affineIndex);
            Color color = getColorForAffineIndex(colors, affine, affineIndex);

            List<Point> rotatedPoints = rotate(config.symmetricAmount(), point);
            for (Point rotatedPoint : rotatedPoints) {
                if (i > 0 && world.contains(rotatedPoint)) {
                    Pixel pixel = world.mapPointToPixel(image, rotatedPoint);
                    synchronized (pixel) {
                        if (pixel.x() <= config.imageWidth() && pixel.y() <= config.imageHeight()) {
                            pixel.addColor(color);
                        }
                    }
                }
            }
        }
    }

}

