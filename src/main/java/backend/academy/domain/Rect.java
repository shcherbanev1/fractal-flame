package backend.academy.domain;

public record Rect(double xMin, double yMin, double xMax, double yMax) {

    public boolean contains(Point point) {
        return (point.x() >= xMin && point.x() <= xMax) && (point.y() >= yMin && point.y() <= yMax);
    }

    public Pixel mapPointToPixel(FractalImage image, Point point , int imageWidth, int imageHeight) {
        int mappedX = (int) ((xMax - point.x()) / (xMax - xMin) * imageWidth);
        int mappedY = (int) ((yMax - point.y()) / (yMax - yMin) * imageHeight);
        return image.pixels()[mappedX][mappedY];
    }
}
