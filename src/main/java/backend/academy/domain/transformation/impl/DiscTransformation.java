package backend.academy.domain.transformation.impl;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;

public class DiscTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double atan = Math.atan2(point.x(), point.y());
        double x = atan / Math.PI * Math.sin(Math.PI * r);
        double y = atan / Math.PI * Math.cos(Math.PI * r);

        return new Point(x, y);
    }
}
