import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day11 extends Day{
    static String RESOURCE = "src/main/resources/day11_input.txt";

    public Day11() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day11().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day11().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Grid grid = getGrid(inputArray);
        int numberOfSteps = 100;
        int numberOfFlashes = 0;
        for (int i = 1; i <= numberOfSteps; i++) {
            grid = processOneStep(grid);
            numberOfFlashes += grid.numberOfOctopusOfValue(0);
        }

        int answer = numberOfFlashes;
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Grid grid = getGrid(inputArray);
        int numberOfFlashes = 0;
        int stepNumber = 0;
        int numberOfOctopus = grid.getTotalRows() * grid.getTotalColumn();
        while (numberOfOctopus != numberOfFlashes) {
            stepNumber += 1;
            grid = processOneStep(grid);
            numberOfFlashes = grid.numberOfOctopusOfValue(0);
        }

        int answer = stepNumber;
        return String.valueOf(answer);
    }

    public Grid flashingEffect(Grid inputGrid,Boolean[][] flashingOctopusGrid, int row, int col){
        int dRow[] = {0, 1, 0, -1, 1, -1, 1, -1};
        int dCol[] = {-1, 0, 1, 0, 1, -1, -1, 1};

        for (int i = 0; i < 8; i++) {
            int adjRow = row + dRow[i];
            int adjCol = col + dCol[i];

            if (inputGrid.isValidCell(adjRow,adjCol) && flashingOctopusGrid[adjRow][adjCol]!= true ) {
                int currentValue = inputGrid.getValue(adjRow, adjCol);
                inputGrid.updateGrid(adjRow, adjCol, currentValue + 1);
            }
        }
        return inputGrid;

    }

    public Grid processOneStep(Grid grid) {
        Boolean[][] flashingOctopusGrid = emptyFlashingOctopusGrid(grid);
        grid.addOneToAllElements();

        while (grid.hasOctopusThatWillFlash()) {
            for (int i = 0; i < grid.getTotalRows(); i++) {
                for (int j = 0; j < grid.getTotalColumn(); j++) {
                    if (grid.getValue(i, j) > 9) {
                        flashingOctopusGrid[i][j] = true;
                        grid = flashingEffect(grid, flashingOctopusGrid, i, j);
                        grid.updateGrid(i, j, 0);
                    }
                }
            }
        }
        return grid;
    }

    public Grid getGrid(ArrayList<String> recordsArray) {
        int numberOrRows = recordsArray.size();
        int numberOfColumns = recordsArray.get(0).length();
        int[][] grid = new int[numberOrRows][numberOfColumns];
        for (int i = 0; i < numberOrRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                grid[i][j] = Character.getNumericValue(recordsArray.get(i).charAt(j));
            }
        }
        Grid myGrid = new Grid(grid);
        return myGrid;
    }


    public Boolean[][] emptyFlashingOctopusGrid(Grid grid) {
        Boolean[][] gridFlashingOctopus = new Boolean[grid.getTotalRows()][grid.getTotalColumn()];
        for (int i = 0; i < grid.getTotalRows(); i++) {
            for (int j = 0; j < grid.getTotalColumn(); j++) {
                gridFlashingOctopus[i][j] = false;
            }

        }
        return gridFlashingOctopus;
    }


    static class Grid{
        int[][] grid;

        public Grid(int[][] grid) {
            this.grid = grid;
        }

        public Integer getTotalRows(){
            return grid.length;
        }

        public Integer getTotalColumn(){
            return grid[0].length;
        }

        public Integer getValue(int row, int col){
            return grid[row][col];
        }

        public void updateGrid(int row, int col, int value) {
            for (int i = 0; i < getTotalRows(); i++) {
                for (int j = 0; j < getTotalColumn(); j++) {
                    if ( i == row & j == col){
                        grid[i][j] = value;
                    }
                }
            }
        }

        public Boolean hasOctopusThatWillFlash(){
            for (int i = 0; i < getTotalRows(); i++) {
                for (int j = 0; j < getTotalColumn(); j++) {
                    if (grid[i][j] > 9) {
                        return true;
                    }
                }
            }
            return false;

        }

        public void addOneToAllElements() {
            for (int i = 0; i < getTotalRows(); i++) {
                for (int j = 0; j < getTotalColumn(); j++) {

                        grid[i][j] = grid[i][j] + 1;

                }
            }
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < getTotalRows(); i++) {
                stringBuffer.append("{");
                for (int j = 0; j < getTotalColumn(); j++) {
                    if (j == getTotalColumn() - 1) {
                        stringBuffer.append(String.valueOf(grid[i][j]) );
                    }
                    else {
                        stringBuffer.append(String.valueOf(grid[i][j]) + ",");
                    }
                }
                stringBuffer.append("}" + System.lineSeparator());
            }
            return stringBuffer.toString();
        }

        public int numberOfOctopusOfValue(int value) {
            int count = 0;
            for (int i = 0; i < getTotalRows(); i++) {
                for (int j = 0; j < getTotalColumn(); j++) {
                    if (grid[i][j] == value) {
                        count += 1;
                    }


                }
            }
            return count;
        }

        public Boolean isValidCell(int row, int col) {
            int ROW = getTotalRows();
            int COL = getTotalColumn();
            if (row < 0 || col < 0 ||
                    row >= ROW || col >= COL) {
                return false;
            }
            else {
                return true;
            }
        }



    }
}
