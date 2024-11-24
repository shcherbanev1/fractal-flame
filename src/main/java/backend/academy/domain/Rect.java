package backend.academy.domain;

public record Rect(double xMin, double yMin, double xMax, double yMax) {

    public boolean contains(Point point) {
        return (point.x() >= xMin && point.x() <= xMax) && (point.y() >= yMin && point.y() <= yMax);
    }

    public Pixel mapPointToPixel(Point point , int imageWidth, int imageHeight) {
        double mappedX = (xMax - point.x()) / (xMax - xMin) * imageWidth;
        double mappedY = (yMax - point.y()) / (yMax - yMin) * imageHeight;
        return new Pixel((int) mappedX, (int) mappedY);
    }
}
