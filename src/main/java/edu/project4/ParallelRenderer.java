package edu.project4;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

public class ParallelRenderer implements Renderer {

    private static final int CORES = Runtime.getRuntime().availableProcessors();

    @Override
    public FractalImage render(
        @NotNull FractalImage canvas, @NotNull Rectangle world,
        @NotNull List<Transformation> variations, @NotNull List<AffinCoeff> coeffs) {

        var executors = Executors.newFixedThreadPool(CORES);

        for (var thread = 0; thread < CORES; thread++) {
            executors.execute(() -> createSample(canvas, world, variations, coeffs));
        }


        executors.close();
        return canvas;
    }

    private void createSample(FractalImage canvas, Rectangle world,
        List<Transformation> variations, List<AffinCoeff> coeffs) {
        for (var sample = 0; sample < SAMPLES / CORES; sample++) {
            Point point = new Point(
                ThreadLocalRandom.current().nextDouble(world.x0(), world.x()),
                ThreadLocalRandom.current().nextDouble(world.y0(), world.y())
            );

            for (var step = -SKIP_ITERATIONS; step < ITERATIONS; step++) {
                int i = ThreadLocalRandom.current().nextInt(coeffs.size());
                AffinCoeff affine = coeffs.get(i);

                int j = ThreadLocalRandom.current().nextInt(variations.size());
                Transformation transformation = variations.get(j);

                point = affine.transform(point);
                point = transformation.apply(point);

                if (step >= 0 && world.contains(point)) {

                    int x1 = (int) ((point.x() - world.x0()) / (world.x() - world.x0()) * canvas.width());
                    int y1 = (int) ((point.y() - world.y0()) / (world.y() - world.y0()) * canvas.height());

                    if (x1 < canvas.width() && y1 < canvas.height()) {

                        Pixel pixel = canvas.getPixel(x1, y1);
                        if (pixel.hitCount() == 0) {
                            canvas.setPixel(new Pixel(
                                x1, y1,
                                affine.red(), affine.green(), affine.blue(),
                                1
                            ));
                        } else {
                            canvas.setPixel(new Pixel(
                                x1, y1,
                                (pixel.red() + affine.red()) / 2,
                                (pixel.green() + affine.green()) / 2,
                                (pixel.blue() + affine.blue()) / 2,
                                pixel.hitCount() + 1
                            ));
                        }
                    }
                }
            }
        }
    }
}
