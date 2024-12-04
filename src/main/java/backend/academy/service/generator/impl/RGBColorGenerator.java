package backend.academy.service.generator.impl;

import backend.academy.domain.Color;
import backend.academy.service.generator.RandomGenerator;

public class RGBColorGenerator extends RandomGenerator<Color> {

    @Override
    public Color generate() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
