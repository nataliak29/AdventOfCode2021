public class Grid {
    int[][] grid;

    public Grid(int[][] grid) {
        this.grid = grid;
    }

    public Integer getTotalRows() {
        return grid.length;
    }

    public Integer getTotalColumn() {
        return grid[0].length;
    }

    public Integer getValue(int row, int col) {
        return grid[row][col];
    }

    public void updateGrid(int row, int col, int value) {
        for (int i = 0; i < getTotalRows(); i++) {
            for (int j = 0; j < getTotalColumn(); j++) {
                if (i == row & j == col) {
                    grid[i][j] = value;
                }
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < getTotalRows(); i++) {
            stringBuffer.append("{");
            for (int j = 0; j < getTotalColumn(); j++) {
                if (j == getTotalColumn() - 1) {
                    stringBuffer.append(String.valueOf(grid[i][j]));
                } else {
                    stringBuffer.append(String.valueOf(grid[i][j]) + ",");
                }
            }
            stringBuffer.append("}" + System.lineSeparator());
        }
        return stringBuffer.toString();
    }

    public Boolean isValidCell(int row, int col) {
        int ROW = getTotalRows();
        int COL = getTotalColumn();
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }


        final Grid other = (Grid) obj;

        if (getTotalRows() != other.getTotalRows()) {
            return false;
        }

        if (getTotalColumn() != other.getTotalColumn()){
            return false;
        }

        for (int i = 0; i < getTotalRows(); i++) {
            for (int j = 0; j < getTotalColumn(); j++) {
                if (grid[i][j] != other.getValue(i,j)){
                    return false;
                }

            }
        }
        return true;

    }


}