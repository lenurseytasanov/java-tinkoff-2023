package edu.project4;

import java.util.List;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Renderer {
    int SAMPLES = 20000;

    int ITERATIONS = 1000;

    int SKIP_ITERATIONS = 20;

    FractalImage render(@NotNull FractalImage canvas, @NotNull Rectangle world,
        @NotNull List<Transformation> variations, @NotNull List<AffinCoeff> coeffs);
}
