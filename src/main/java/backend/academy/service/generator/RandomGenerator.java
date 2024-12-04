package backend.academy.service.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class RandomGenerator<T> {

    protected final Random random = ThreadLocalRandom.current();

    public List<T> generate(int number) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(generate());
        }
        return list;
    }

    protected abstract T generate();

}
