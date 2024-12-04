package backend.academy.domain.transformation.impl;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;

public class HeartTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double atan = Math.atan2(point.x(), point.y());
        double x = r * Math.sin(atan * r);
        double y = -r * Math.cos(atan * r);
        return new Point(x, y);
    }
}
