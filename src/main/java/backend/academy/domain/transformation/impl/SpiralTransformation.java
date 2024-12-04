package backend.academy.domain.transformation.impl;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;

public class SpiralTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double atan = Math.atan2(point.x(), point.y());
        double x = 1 / r * (Math.cos(atan) + Math.sin(r));
        double y = 1 / r * (Math.sin(atan) - Math.cos(r));
        return new Point(x, y);
    }
}
