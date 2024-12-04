package backend.academy.type;

public record Config(
    int totalPoints,
    int iterationsForPoint,
    int symmetricAmount,
    int imageWidth,
    int imageHeight,
    double xConstraint,
    double yConstraint,
    ImageFormat format) {
}
