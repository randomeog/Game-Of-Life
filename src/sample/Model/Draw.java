package sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw {
    private GraphicsContext gc;
    private int resolution;

    public Draw(GraphicsContext gc, int resolution) {
        this.gc = gc;
        this.resolution = resolution;
    }

    public void drawCells(Grid grid){
        for(int i=0;i<grid.getHeight(); i++){
            for(int j=0;j<grid.getWidth();j++){
                int count = grid.getSquare(i,j);
                int x = i*grid.getResolution();
                int y = j*grid.getResolution();
                drawCell(y,x,count);

                }
            }
        }
    public void drawFromClick(Grid grid, int i, int j) {
        int iPix = i - (i % resolution);
        int jPix = j - (j % resolution);
        int y = iPix / grid.getResolution();
        int x = jPix / grid.getResolution();
        grid.newCell(x, y);
        int value = grid.getSquare(x, y);
        drawCell(iPix, jPix, value);
    }

    private void drawCell(int i, int j, int value) {
        Color color = Color.WHITE;
        if(value == 1)
            color = Color.BLACK;
        gc.setFill(color);
        gc.fillRect(i, j,
                19, 19);
    }
}

