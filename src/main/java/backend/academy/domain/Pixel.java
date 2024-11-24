package backend.academy.domain;

import lombok.Getter;

public class Pixel {
    @Getter private final int x;
    @Getter private final int y;
    private int r;
    private int g;
    private int b;
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
    public void addColor(int r, int g, int b) {
        this.r += r == 0 ? r : r / 2;
        this.g += g == 0 ? g : g / 2;
        this.b += b == 0 ? b : b / 2;
        hitCount++;
    }

    public int r() {
        return r / hitCount;
    }

    public int g() {
        return g / hitCount;
    }

    public int b() {
        return b / hitCount;
    }
}
