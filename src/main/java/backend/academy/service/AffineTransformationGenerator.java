package backend.academy.service;

import backend.academy.domain.transformation.impl.AffineTransformation;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AffineTransformationGenerator {

    private static final double LOWER_CONSTRAINT_FOR_COEFFICIENT = -1.0;
    private static final double UPPER_CONSTRAINT_FOR_COEFFICIENT = 1.0;
    private static final int RGB_MAX_VALUE = 255;

    public List<AffineTransformation> generateAffineTransformation(int n) {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            affineTransformations.add(generateAffineTransformation());
        }
        return affineTransformations;
    }

    public AffineTransformation generateAffineTransformation() {
        Random random = new SecureRandom();
        double a, b, c, d, e, f;
        do {
            a = random.nextDouble(LOWER_CONSTRAINT_FOR_COEFFICIENT, UPPER_CONSTRAINT_FOR_COEFFICIENT);
            b = random.nextDouble(LOWER_CONSTRAINT_FOR_COEFFICIENT, UPPER_CONSTRAINT_FOR_COEFFICIENT);
            c = random.nextDouble(LOWER_CONSTRAINT_FOR_COEFFICIENT, UPPER_CONSTRAINT_FOR_COEFFICIENT);
            d = random.nextDouble(LOWER_CONSTRAINT_FOR_COEFFICIENT, UPPER_CONSTRAINT_FOR_COEFFICIENT);
            e = random.nextDouble(LOWER_CONSTRAINT_FOR_COEFFICIENT, UPPER_CONSTRAINT_FOR_COEFFICIENT);
            f = random.nextDouble(LOWER_CONSTRAINT_FOR_COEFFICIENT, UPPER_CONSTRAINT_FOR_COEFFICIENT);
        } while (!isValidTransformation(a, b, c, d, e, f));

        int red = random.nextInt(RGB_MAX_VALUE);
        int green = random.nextInt(RGB_MAX_VALUE);
        int blue = random.nextInt(RGB_MAX_VALUE);

        return new AffineTransformation(a, b, c, d, e, f, red, green, blue);
    }

    private boolean isValidTransformation(double a, double b, double c, double d, double e, double f) {
        if (!(a * a + d * d < 1)) {
            return false;
        }
        if (!(b * b + e * e < 1)) {
            return false;
        }
        return a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d);
    }

}
