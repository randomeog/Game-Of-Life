package sample.Model;

import java.util.Random;
import java.util.function.Function;

public class Grid {
    private final Function<Integer, Integer> REVERSE_VALUE = val -> (val+1) % 2;
    private int height;
    private int width;
    private int resolution;

    private int [][] newGrid;


    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getResolution() {
        return resolution;
    }
    public int getSquare(int i, int j){
        return newGrid[i][j];
    }
    public int[][] getGrid(){
        return newGrid;
    }
    public void setGrid(int [][] newGrid){
        this.newGrid = newGrid;
    }


    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.resolution = 20;
        this.newGrid = fillGrid(this.height, this.width);
    }

    public Grid(int width, int height, int resolution) {
        this.height = height/resolution;
        this.width = width/resolution;
        this.resolution = resolution;
        this.newGrid = fillGrid(this.height, this.width);
    }


    public void newCell(int i, int j) {
        int newValue = REVERSE_VALUE.apply(newGrid[i][j]);
        this.newGrid[i][j] = newValue;
    }
    public int [][]fillGrid(int height, int width){
        int [][] newGrid = new int [height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newGrid[i][j] = 0;
            }
        }
        return newGrid;
    }




}
