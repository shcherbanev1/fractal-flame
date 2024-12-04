package backend.academy.domain.transformations;

import backend.academy.domain.Point;
import backend.academy.domain.transformation.Transformation;
import backend.academy.domain.transformation.impl.AffineTransformation;
import backend.academy.domain.transformation.impl.DiamondTransformation;
import backend.academy.domain.transformation.impl.DiscTransformation;
import backend.academy.domain.transformation.impl.HeartTransformation;
import backend.academy.domain.transformation.impl.HyperbolicTransformation;
import backend.academy.domain.transformation.impl.SphereTransformation;
import backend.academy.domain.transformation.impl.SpiralTransformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformationTest {

    private Point point;

    @BeforeEach
    void setUp() {
        point = new Point(3, 4);
    }

    @Test
    void affineTransformation() {
        Transformation transformation = new AffineTransformation(1, 3, 2, 5, 5, 3);
        Point result = transformation.transform(point);
        assertTrue(Math.abs(17 - result.x()) <= 1e-5);
        assertTrue(Math.abs(38 - result.y()) <= 1e-5);
    }

    @Test
    void diamondTransformation() {
        Transformation transformation = new DiamondTransformation();
        Point result = transformation.transform(point);
        assertTrue(Math.abs(0.255295 - result.x()) <= 1e-5);
        assertTrue(Math.abs(-1.150709 - result.y()) <= 1e-5);
    }

    @Test
    void sphereTransformation() {
        Transformation transformation = new SphereTransformation();
        Point result = transformation.transform(point);
        assertTrue(Math.abs(0.12 - result.x()) <= 1e-5);
        assertTrue(Math.abs(0.16 - result.y()) <= 1e-5);
    }

    @Test
    void discTransformation() {
        Transformation transformation = new DiscTransformation();
        Point result = transformation.transform(point);
        assertTrue(Math.abs(0 - result.x()) <= 1e-5);
        assertTrue(Math.abs(-0.204832 - result.y()) <= 1e-5);
    }

    @Test
    void heartTransformation() {
        Transformation transformation = new HeartTransformation();
        Point result = transformation.transform(point);
        assertTrue(Math.abs(-0.379199 - result.x()) <= 1e-5);
        assertTrue(Math.abs(4.9856 - result.y()) <= 1e-5);
    }

    @Test
    void hyperbolicTransformation() {
        Transformation transformation = new HyperbolicTransformation();
        Point result = transformation.transform(point);
        assertTrue(Math.abs(0.12 - result.x()) <= 1e-5);
        assertTrue(Math.abs(4 - result.y()) <= 1e-5);
    }

    @Test
    void spiralTransformation() {
        Transformation transformation = new SpiralTransformation();
        Point result = transformation.transform(point);
        assertTrue(Math.abs(-0.031784 - result.x()) <= 1e-5);
        assertTrue(Math.abs(0.063267 - result.y()) <= 1e-5);
    }

}
