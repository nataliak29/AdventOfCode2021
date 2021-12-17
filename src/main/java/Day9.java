import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Day9 extends Day {

    static String RESOURCE = "src/main/resources/day9_input.txt";

    public Day9() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day9().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day9().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> recordsArray = getResourceAsStringArray(resource);
        int[][] grid = getGrid(recordsArray);
        int numberOrRows = inputNumberOfRows(recordsArray);
        int numberOfColumns = inputNumberOfColumns(recordsArray);
        int totalRisk = 0;

        for (int i = 0; i < numberOrRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                LocationPoint lp = new LocationPoint(grid, j, i);
                totalRisk += lp.riskLevel();
            }
        }
        int answer = totalRisk;
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> recordsArray = getResourceAsStringArray(resource);
        int[][] grid = getGrid(recordsArray);
        int[][] gridWithEdge = addEdge(grid);
        ArrayList<Integer> basinSizes = new ArrayList<>();
        Boolean [][] vis = createGridWithBooleanValues(gridWithEdge);

        for (int i = 0; i < gridWithEdge.length; i++) {
            for (int j = 0; j < gridWithEdge[0].length; j++) {
                LocationPoint lp = new LocationPoint(gridWithEdge, j, i);
                if ( lp.isLowPoint()){
                     int basinSize = DFS(i, j, gridWithEdge, vis);
                     basinSizes.add(basinSize);
                 }
            }
        }

        int answer = topThreeBasinsProduct(basinSizes);
        return String.valueOf(answer);
    }

    public int topThreeBasinsProduct(ArrayList<Integer> basinSizes) {
        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        int answer = 1;
        for (int b = 0; b < 3; b++){
            answer = answer * basinSizes.get(b);
        }
        return answer;

    }

    public int inputNumberOfRows(ArrayList<String> recordsArray) {
        return recordsArray.size();
    }

    public int inputNumberOfColumns(ArrayList<String> recordsArray) {
        return recordsArray.get(0).length();
    }

    public int[][] getGrid(ArrayList<String> recordsArray) {
        int numberOrRows = recordsArray.size();
        int numberOfColumns = recordsArray.get(0).length();
        int[][] grid = new int[numberOrRows][numberOfColumns];
        for (int i = 0; i < numberOrRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                grid[i][j] = Character.getNumericValue(recordsArray.get(i).charAt(j));
            }
        }
        return grid;
    }

    public int[][] addEdge(int[][] grid) {
        int numberOrRows = grid.length;
        int numberOfColumns = grid[0].length;
        int[][] newGrid = new int[numberOrRows + 2][numberOfColumns + 2];
        for (int i = 0; i < numberOrRows + 2; i++) {
            for (int j = 0; j < numberOfColumns + 2; j++) {
                if (i == numberOrRows + 1 || j == numberOfColumns + 1 || i == 0 || j == 0) {
                    newGrid[i][j] = 9;
                } else {
                    newGrid[i][j] = grid[i - 1][j - 1];
                }
            }
        }
        return newGrid;
    }

    public Boolean [][] createGridWithBooleanValues(int[][] grid) {
        Boolean vis[][] = new Boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                vis[i][j] = false;
            }
        }
        return  vis;
    }

    /** Depth First Traversal on the matrix grid[]
     * https://www.geeksforgeeks.org/depth-first-traversal-dfs-on-a-2d-array/
     **/

    static int DFS(int row, int col, int grid[][], Boolean vis[][]) {
        int dRow[] = {0, 1, 0, -1};
        int dCol[] = {-1, 0, 1, 0};
        int basinSize = 0;

        Stack<LocationPoint> st = new Stack<LocationPoint>();
        LocationPoint lpStart = new LocationPoint(grid,  col,row);
        st.push(lpStart);

        while (!st.empty()) {
            LocationPoint curr = st.pop();
            row = curr.yCoordinate;
            col = curr.xCoordinate;

            // Mark the current cell as visited
            vis[row][col] = true;
            basinSize += 1;

            // Push all the adjacent cells
            for (int i = 0; i < 4; i++) {
                int adjy = row + dRow[i];
                int adjx = col + dCol[i];

                if (!isValid(vis, adjy, adjx ))
                    continue;
                LocationPoint nextLP = new LocationPoint(grid, adjx, adjy);
                if (nextLP.pointValue != 9 && vis[adjy][adjx] == false) {
                    st.push(new LocationPoint(grid, adjx, adjy));
                    vis[adjy][adjx] = true;
                }
            }
        }
        return basinSize;
    }

    static Boolean isValid(Boolean vis[][], int row,
                           int col) {
        int ROW = vis.length;
        int COL = vis[0].length;
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL) {
            return false;
        }

        if (vis[row][col])
            return false;

        return true;
    }


    static class LocationPoint {
        int[][] grid;
        int xCoordinate;
        int yCoordinate;
        int pointValue;

        public LocationPoint(int[][] grid, int xCoordinate, int yCoordinate) {
            this.grid = grid;
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
            this.pointValue = grid[yCoordinate][xCoordinate];
        }

        public boolean isLowerThanLeft() {
            int leftLocationPoint = 0;
            try {
                leftLocationPoint = grid[yCoordinate][xCoordinate - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }
            if (pointValue < leftLocationPoint) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isLowerThanBottom() {
            int bottomLocationPoint = 0;
            try {
                bottomLocationPoint = grid[yCoordinate - 1][xCoordinate];
            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }
            if (pointValue < bottomLocationPoint) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isLowerThanRight() {
            int rightLocationPoint = 0;
            try {
                rightLocationPoint = grid[yCoordinate][xCoordinate + 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }
            if (pointValue < rightLocationPoint) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isLowerThanTop() {
            int topLocationPoint = 0;
            try {
                topLocationPoint = grid[yCoordinate + 1][xCoordinate];
            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }
            if (pointValue < topLocationPoint) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isLowPoint() {
            if (isLowerThanBottom() == true &&
                    isLowerThanLeft() == true &&
                    isLowerThanRight() == true &&
                    isLowerThanTop() == true) {
                return true;
            } else {
                return false;
            }
        }

        public int riskLevel() {
            int riskLevel = 0;
            if (isLowPoint()) {
                riskLevel = 1 + pointValue;
            }
            return riskLevel;
        }

    }
}
