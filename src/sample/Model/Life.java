package sample.Model;

public class Life {
    public void nextGen(Grid grid) {
        int[][] oldGrid = grid.getGrid();
        int[][] tempGrid = new int[grid.getHeight()][grid.getWidth()];

        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {

                int lNeighbours = countNeighbors(i, j, oldGrid);
                if (grid.getSquare(i, j) == 0 && lNeighbours == 3) {
                        tempGrid[i][j] = 1;
                } else if (grid.getSquare(i,j) == 1 && lNeighbours < 2 || lNeighbours >3) {
                    tempGrid[i][j] = 0;
                }
                else{
                        tempGrid[i][j] = grid.getSquare(i,j);
                }
            }
        }
        grid.setGrid(tempGrid);
    }

    private int countNeighbors(int i, int j, int[][] temp1Grid) {
        int sum = 0;
        for (int k = i-1; k <= i+1; k++) {
            for (int l = j-1; l <= j+1; l++) {
                int height=k;
                if(height<0)
                    height=temp1Grid.length-1;
                if(height > temp1Grid.length-1)
                    height = 0;
                int width = l;
                if(width<0)
                    width=temp1Grid[0].length-1;
                if(width > temp1Grid[0].length-1)
                    width = 0;
                if (temp1Grid[height][width] == 1 && !(height == i && l == j))
                    sum++;
            }
        }
        return sum;
    }


}

