package backend.academy.domain;

public record Rect(double xMin, double yMin, double xMax, double yMax) {

    public boolean contains(Point point) {
        return (point.x() >= xMin && point.x() <= xMax) && (point.y() >= yMin && point.y() <= yMax);
    }

    public Pixel mapPointToPixel(FractalImage image, Point point) {
        int imageWidth = image.width();
        int imageHeight = image.height();
        int mappedX = imageWidth - (int) ((xMax - point.x()) / (xMax - xMin) * imageWidth);
        int mappedY = imageHeight - (int) ((yMax - point.y()) / (yMax - yMin) * imageHeight);
        return image.pixels()[mappedX - 1][mappedY - 1];
    }
}
