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
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class FlameGenerator {

    protected static final int SKIP_STEPS = -20;
    protected static final int MILESTONE_FOR_LOG = 10;

    public abstract FractalImage generate(
        List<AffineTransformation> affine,
        List<Transformation> nonLinear,
        List<Color> colors,
        Config config
    );

    @SuppressFBWarnings("NOS_NON_OWNED_SYNCHRONIZATION")
    @SuppressWarnings("ParameterNumber")
    protected void generatePart(
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
                    addPixelColor(pixel, color, config);
                }
            }
        }
    }

    protected Point transformPoint(
        Point point,
        List<AffineTransformation> affine,
        List<Transformation> nonLinear,
        Random random,
        int affineIndex
    ) {
        AffineTransformation affineTransformation = affine.get(affineIndex);
        Point newPoint = affineTransformation.transform(point);

        Transformation nonLinearTransformation = RandomUtils.getRandomElement(nonLinear, random);
        return nonLinearTransformation.transform(newPoint);
    }

    abstract void addPixelColor(Pixel pixel, Color color, Config config);

    protected Color getColorForAffineIndex(List<Color> colors, List<AffineTransformation> affine, int affineIndex) {
        int colorIndex = (int) ((double) colors.size() / affine.size() * affineIndex);
        return colors.get(colorIndex);
    }

    protected Point randomPoint(Rect world, Random random) {
        return new Point(random.nextDouble(world.xMin(), world.xMax()), random.nextDouble(world.yMin(), world.yMax()));
    }

    protected List<Point> rotate(int symmetricalAmount, Point point) {
        List<Point> rotatedPoints = new ArrayList<>(symmetricalAmount);
        double rotateAngle = Math.PI * 2 / symmetricalAmount;
        for (int i = 0; i < symmetricalAmount; i++) {
            double currentAngle = i * rotateAngle;
            var x = point.x();
            var y = point.y();
            double xRotated = x * Math.cos(currentAngle) - y * Math.sin(currentAngle);
            double yRotated = x * Math.sin(currentAngle) + y * Math.cos(currentAngle);
            rotatedPoints.add(new Point(xRotated, yRotated));
        }
        return rotatedPoints;
    }

}
