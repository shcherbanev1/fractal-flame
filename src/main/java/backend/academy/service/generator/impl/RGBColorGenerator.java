package backend.academy.service.generator.impl;

import backend.academy.domain.Color;
import backend.academy.service.generator.RandomGenerator;

public class RGBColorGenerator extends RandomGenerator<Color> {

    private static final int MAX_RGB_VALUE = 256;

    @Override
    public Color generate() {
        return new Color(random.nextInt(MAX_RGB_VALUE), random.nextInt(MAX_RGB_VALUE), random.nextInt(MAX_RGB_VALUE));
    }
}
