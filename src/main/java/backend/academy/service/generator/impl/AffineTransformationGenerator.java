package backend.academy.service.generator.impl;

import backend.academy.domain.transformation.impl.AffineTransformation;
import backend.academy.service.generator.RandomGenerator;
import java.security.SecureRandom;
import java.util.Random;

public class AffineTransformationGenerator extends RandomGenerator<AffineTransformation> {

    private final double minCoefficient;
    private final double maxCoefficient;
    private final Random random;

    public AffineTransformationGenerator(double minCoefficient, double maxCoefficient) {
        this.minCoefficient = minCoefficient;
        this.maxCoefficient = maxCoefficient;
        random = new SecureRandom();
    }

    @Override
    protected AffineTransformation generate() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;
        do {
            a = random.nextDouble(minCoefficient, maxCoefficient);
            b = random.nextDouble(minCoefficient, maxCoefficient);
            c = random.nextDouble(minCoefficient, maxCoefficient);
            d = random.nextDouble(minCoefficient, maxCoefficient);
            e = random.nextDouble(minCoefficient, maxCoefficient);
            f = random.nextDouble(minCoefficient, maxCoefficient);
        } while (!isValidTransformation(a, b, d, e));
        return new AffineTransformation(a, b, c, d, e, f);
    }

    private boolean isValidTransformation(double a, double b, double d, double e) {
        if (!(a * a + d * d < 1)) {
            return false;
        }
        if (!(b * b + e * e < 1)) {
            return false;
        }
        return a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
    }
}
