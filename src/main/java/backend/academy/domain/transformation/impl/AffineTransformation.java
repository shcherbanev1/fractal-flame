package backend.academy.domain.transformation.impl;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AffineTransformation implements Transformation {

    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    @Override
    public Point transform(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;
        return new Point(x, y);
    }

}
