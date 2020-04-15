package sample.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StartPatterns {

    public static Grid getGridForTemplate(String val, int width, int height) throws Exception {
        switch (val) {
            case "Own":
                return new Grid(width, height);
            case "Constant":
                return constantPattern(width, height);
            case "Glider":
                return gliderPattern(width, height);
            case "Oscilator":
                return oscilatorPattern(width, height);
            case "Random":
                return randomPattern(width, height);
            default:
                throw new Exception("Unknown pattern");
        }
    }

    public static Grid constantPattern(int width, int height) {

        Grid grid = new Grid(width, height);
        int w = width / 2;
        int h = height / 2;
        grid.newCell(h, w);
        grid.newCell(h, w + 1);
        grid.newCell(h + 1, w - 1);
        grid.newCell(h + 1, w + 2);
        grid.newCell(h + 2, w);
        grid.newCell(h + 2, w + 1);
        return grid;
    }

    public static Grid gliderPattern(int width, int height) {
        Grid grid = new Grid(width, height);
        int w = width / 2;
        int h = height / 2;
        grid.newCell(h, w + 1);
        grid.newCell(h, w + 2);
        grid.newCell(h + 1, w);
        grid.newCell(h + 1, w + 1);
        grid.newCell(h + 2, w + 2);
        return grid;
    }

    private static Grid oscilatorPattern(int width, int height) {
        Grid grid = new Grid(width, height);
        int w = width / 2;
        int h = height / 2;
        grid.newCell(h, w + 1);
        grid.newCell(h + 1, w + 1);
        grid.newCell(h + 2, w + 1);
        return grid;
    }

    private static Grid randomPattern(int width, int height) {
        Grid grid = new Grid(width, height);
        Random random = new Random();
        int numOfRandoms = random.nextInt(1000);
        double[] randomDoubles = random.doubles(numOfRandoms).toArray();
        for (int i = 0; i < randomDoubles.length - 1; i += 2) {
            int x = (int) (randomDoubles[i] * height);
            int y = (int) (randomDoubles[i + 1] * width);
            grid.newCell(x, y);
        }
        return grid;
    }
}
