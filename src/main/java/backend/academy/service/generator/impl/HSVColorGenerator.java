package backend.academy.service.generator.impl;

import backend.academy.domain.Color;
import backend.academy.service.generator.RandomGenerator;

@SuppressWarnings("MagicNumber")
public class HSVColorGenerator extends RandomGenerator<Color> {

    @Override
    public Color generate() {
        int hue = random.nextInt(0, 360);
        double saturation = 1;
        double value = 1;
        return hsvToRgb(hue, saturation, value);
    }

    private Color hsvToRgb(int hue, double saturation, double value) {
        double c = saturation * value;
        double x = c * (1 - Math.abs((hue / 60.0) % 2 - 1));
        double m = value - c;

        double rPrime;
        double gPrime;
        double bPrime;

        if (hue < 60) {
            rPrime = c;
            gPrime = x;
            bPrime = 0;
        } else if (hue < 120) {
            rPrime = x;
            gPrime = c;
            bPrime = 0;
        } else if (hue < 180) {
            rPrime = 0;
            gPrime = c;
            bPrime = x;
        } else if (hue < 240) {
            rPrime = 0;
            gPrime = x;
            bPrime = c;
        } else if (hue < 300) {
            rPrime = x;
            gPrime = 0;
            bPrime = c;
        } else {
            rPrime = c;
            gPrime = 0;
            bPrime = x;
        }

        int r = (int) ((rPrime + m) * 255);
        int g = (int) ((gPrime + m) * 255);
        int b = (int) ((bPrime + m) * 255);

        return new Color(r, g, b);
    }

}
