package backend.academy;

import backend.academy.domain.transformation.impl.SphereTransformation;
import backend.academy.domain.transformation.impl.SpiralTransformation;
import backend.academy.service.flame.FlameGenerator;
import backend.academy.service.flame.MultiThreadFlameGenerator;
import backend.academy.type.Config;
import backend.academy.type.ImageFormat;
import java.util.List;
import lombok.experimental.UtilityClass;

@SuppressWarnings("MagicNumber")
@UtilityClass
public class Main {
    public static void main(String[] args) {
        FlameGenerator generator = new MultiThreadFlameGenerator();
        Config config = new Config(1000,
            1_000_000,
            3,
            7920,
            4320,
            3.3,
            1.86,
            ImageFormat.JPEG);
        App app = new App(config, 20, 4, List.of(new SpiralTransformation(), new SphereTransformation()), generator);
        app.run();
    }
}
