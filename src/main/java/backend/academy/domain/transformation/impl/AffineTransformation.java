package backend.academy.domain.transformation.impl;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AffineTransformation implements Transformation {

    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;
    @Getter private final int red;
    @Getter private final int green;
    @Getter private final int blue;

    @Override
    public Point transform(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;
        return new Point(x, y);
    }

}
