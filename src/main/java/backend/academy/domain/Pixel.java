package backend.academy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Pixel {
    private final int x;
    private final int y;
    @Setter private int r;
    @Setter private int g;
    @Setter private int b;
    private int hitCount;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        r = 0;
        g = 0;
        b = 0;
        hitCount = 0;
    }

    /*
     * Accumulate color. When required divide by the number of hits
     * */
    public void addColor(Color color) {
        if (hitCount == 0) {
            r = color.red();
            g = color.green();
            b = color.blue();
        } else {
            r = (r + color.red()) / 2;
            g = (g + color.green()) / 2;
            b = (b + color.blue()) / 2;
        }
        hitCount++;
    }

}
