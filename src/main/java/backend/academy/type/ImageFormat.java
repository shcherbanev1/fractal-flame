package backend.academy.type;

import lombok.Getter;

@Getter
public enum ImageFormat {

    JPEG("jpeg"),
    PNG("png"),
    BMP("bmp");

    private final String format;

    ImageFormat(String format) {
        this.format = format;
    }
}
