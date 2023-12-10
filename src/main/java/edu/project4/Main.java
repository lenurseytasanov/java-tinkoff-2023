package edu.project4;

import java.nio.file.Paths;
import java.util.List;

public final class Main {

    private Main() { }

    private static final int WIDTH = 1920;

    private static final int HEIGHT = 1080;

    private static final double WIDTH_SCALING = 1.777;

    private static final double HEIGHT_SCALING = 1;

    private static final int AFFINE_COEFFS_COUNT = 100;

    public static void main(String[] args) {
        Renderer renderer = new ParallelRenderer();
        GammaCorrector corrector = new GammaCorrector();

        FractalImage canvas = FractalImage.create(WIDTH, HEIGHT);
        Rectangle world = new Rectangle(-WIDTH_SCALING, -HEIGHT_SCALING, WIDTH_SCALING, HEIGHT_SCALING);

        List<AffinCoeff> coeffs = AffinCoeff.generate(AFFINE_COEFFS_COUNT);

        List<Transformation> variations = List.of(
            new Transformation.Spherical()
        );

        canvas = renderer.render(canvas, world, variations, coeffs);
        canvas = corrector.correct(canvas);

        ImageUtils.save(canvas, Paths.get("src/test/java/edu/project4/test.png"));
    }
}
