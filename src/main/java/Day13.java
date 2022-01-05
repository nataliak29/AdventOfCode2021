import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day13 extends Day {

    static String RESOURCE = "src/main/resources/day13_input.txt";

    public Day13() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day13().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day13().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        ArrayList<String> foldInstructions = getFoldInstructions(inputArray);

        Grid grid = setUpBeforeFolds(foldInstructions, inputArray);
        String firstFold = foldInstructions.get(0);

        grid = processOneFold(firstFold, grid);
        int answer = grid.countVisibleDots();
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        ArrayList<String> foldInstructions = getFoldInstructions(inputArray);
        Grid grid = setUpBeforeFolds(foldInstructions, inputArray);
        for (String instruction : foldInstructions){
            grid = processOneFold(instruction, grid);
        }
        System.out.println(grid);

        int answer = -1;
        return String.valueOf(answer);
    }

    public Grid setUpBeforeFolds(ArrayList<String> foldInstructions, ArrayList<String> inputArray ){
        int gridSizeX = 0;
        int gridSizeY = 0;

        String firstFold = foldInstructions.get(0);
        String foldOn = getAxisToFoldOn(firstFold);
        int foldOnValue = getAxisValueToFoldOn(firstFold);
        if (foldOn.equals("Y")){
            gridSizeY = foldOnValue * 2;
        }
        else {
            gridSizeX = foldOnValue * 2;
        }

        String secondFold = foldInstructions.get(1);
        String secondFoldOn = getAxisToFoldOn(secondFold);
        int secondFoldOnValue = getAxisValueToFoldOn(secondFold);

        if (secondFoldOn.equals("Y")){
            gridSizeY = secondFoldOnValue * 2;
        }
        else {
            gridSizeX = secondFoldOnValue * 2;
        }

        Grid grid = new Grid(new int [gridSizeY+1][gridSizeX+1]);
        for (String line: inputArray){
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                grid = processOneSetOfCoordinates(grid,line);
            }
        }
        return grid;

    }

    public Grid processOneFold(String foldInstruction, Grid grid){

        String foldOn = getAxisToFoldOn(foldInstruction);
        int foldOnValue = getAxisValueToFoldOn(foldInstruction);


        if (foldOn.equals("Y")){
            grid = grid.foldOnYAxis(foldOnValue);
        }
        else {
            grid = grid.foldOnXAxis(foldOnValue);
        }
        return grid;
    }

    public String getAxisToFoldOn(String instruction) {
        if (instruction.contains("y")) {
            return "Y";
        }
        else {
            return "X";
        }

    }

    public int getAxisValueToFoldOn(String instruction) {
        String value = instruction.replaceAll("[^0-9]+", "");
        return Integer.parseInt(value);
    }

    public ArrayList<String> getFoldInstructions(ArrayList<String> inputArray) {
        ArrayList<String> foldInstructions = new ArrayList<String>();
        for (String line : inputArray) {
            if (line.startsWith("fold")) {
                foldInstructions.add(line);
            }
        }
        return foldInstructions;
    }


    public Grid processOneSetOfCoordinates(Grid grid, String coordinatesString){
        String[] xAndYAxis = coordinatesString.split(",");
        int x = Integer.parseInt(xAndYAxis[0]);
        int y = Integer.parseInt(xAndYAxis[1]);
        grid.updateGrid(y, x, 1);
        return grid;
    }

    static class Grid {
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


        public Grid foldOnYAxis(int yAxis){
            int[][] newGridValues = new int[yAxis][getTotalColumn()];
            for (int i = 0; i < yAxis; i++) {
                for (int j = 0; j < getTotalColumn(); j++) {

                    int newValue = grid[i][j] + grid[getTotalRows() -1 - i][j];
                    if (newValue < 1) {
                        newGridValues[i][j] = 0;
                    }
                    else{
                        newGridValues[i][j] = 1;
                    }
                }
            }
            return new Grid(newGridValues);
        }

        public Grid foldOnXAxis(int xAxis){
            int[][] newGridValues = new int[getTotalRows()][xAxis];
            for (int j = 0; j < xAxis; j++) {
                for (int i = 0; i < getTotalRows(); i++) {

                    int newValue = grid[i][j] + grid[i][getTotalColumn()- 1 - j];
                    if (newValue < 1) {
                        newGridValues[i][j] = 0;
                    }
                    else {
                        newGridValues[i][j] = 1;
                    }
                }
            }
            return new Grid(newGridValues);
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

        public int countVisibleDots() {
            int visibleDotsCount = 0;
            for (int i = 0; i < getTotalRows(); i++) {
                for (int j = 0; j < getTotalColumn(); j++) {
                    if (grid[i][j] == 1) {
                        visibleDotsCount += 1;
                    }
                }
            }
            return visibleDotsCount;
        }


        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < getTotalRows(); i++) {
                stringBuffer.append("{");
                for (int j = 0; j < getTotalColumn(); j++) {
                    String output = ".";
                    if (grid[i][j] == 1){
                        output = "#";
                    }
                    stringBuffer.append(output);

                }
                stringBuffer.append("}" + System.lineSeparator());
            }
            return stringBuffer.toString();
        }


        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj.getClass() != this.getClass()) {
                return false;
            }

            final Day13.Grid other = (Day13.Grid) obj;

            for (int i = 0; i < getTotalRows(); i++) {
                for (int j = 0; j < getTotalColumn(); j++) {
                    if (grid[i][j] != other.getValue(i,j)) {
                        return false;
                    }
                }
            }

            return true;
        }


    }
}

