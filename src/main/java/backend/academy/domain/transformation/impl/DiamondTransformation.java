package backend.academy.domain.transformation.impl;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;

public class DiamondTransformation implements Transformation {

    private static final double TRANSFORMATION_CONSTANT = 1.5;

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double atan = Math.atan2(point.x(), point.y());
        double x = Math.sin(atan) * Math.cos(r);
        double y = Math.cos(atan) * Math.sin(r);
        return new Point(TRANSFORMATION_CONSTANT * x, TRANSFORMATION_CONSTANT * y);
    }
}
