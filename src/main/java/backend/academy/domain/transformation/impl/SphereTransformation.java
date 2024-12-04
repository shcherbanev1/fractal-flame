package backend.academy.domain.transformation.impl;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;

public class SphereTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double newX = point.x() / (r * r);
        double newY = point.y() / (r * r);
        return new Point(newX, newY);
    }
}

