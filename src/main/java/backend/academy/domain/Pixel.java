package backend.academy.domain;

import lombok.Getter;

@Getter
public class Pixel {
    private final int x;
    private final int y;
    private final Color color;
    private int hitCount;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = new Color(0, 0, 0);
        hitCount = 0;
    }

    /*
     * Accumulate color. When required divide by the number of hits
     * */
    public void addColor(Color addingColor) {
        if (hitCount == 0) {
            color.red(addingColor.red());
            color.green(addingColor.green());
            color.blue(addingColor.blue());
        } else {
            color.red((color.red() + addingColor.red()) / 2);
            color.green((color.green() + addingColor.green()) / 2);
            color.blue((color.blue() + addingColor.blue()) / 2);
        }
        hitCount++;
    }

}
