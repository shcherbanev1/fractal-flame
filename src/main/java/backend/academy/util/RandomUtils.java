package backend.academy.util;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    public static <T> T getRandomElement(List<T> list, Random random) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public static <T> int getRandomIndex(List<T> list, Random random) {
        return random.nextInt(list.size());
    }

}
