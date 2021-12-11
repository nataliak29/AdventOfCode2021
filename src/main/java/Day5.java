import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day5 extends Day {
    static String RESOURCE = "src/main/resources/day5_input.txt";

    public Day5() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day5().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day5().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        ArrayList<CoordinatesChange> coordinatesChangeArray = getCoordinatesChangeArray (inputArray);
        Pair<Integer,Integer> gridSize = getRequiredGridSize(coordinatesChangeArray);
        Grid grid = new Grid(gridSize.getKey(), gridSize.getValue());
        for (CoordinatesChange cc : coordinatesChangeArray){
            if (cc.isHorizontalOrVertical()) {
                grid.addLineSegment(cc);
            }
        }
        int answer =  grid.countElementsWithMultipleIntersections(2);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        ArrayList<CoordinatesChange> coordinatesChangeArray = getCoordinatesChangeArray (inputArray);
        Pair<Integer,Integer> gridSize = getRequiredGridSize(coordinatesChangeArray);
        Grid grid = new Grid(gridSize.getKey(), gridSize.getValue());
        for (CoordinatesChange cc : coordinatesChangeArray){
            if (cc.isHorizontalOrVertical() || cc.isDiagonal() ) {
                grid.addLineSegment(cc);
            }
        }
        int answer =  grid.countElementsWithMultipleIntersections(2);
        return String.valueOf(answer);
    }

    public ArrayList<CoordinatesChange> getCoordinatesChangeArray (ArrayList<String> inputArray) {
        ArrayList<CoordinatesChange> coordinatesChanges = new ArrayList<CoordinatesChange>();
        for (String line : inputArray){
            String start = line.split(" -> ")[0];
            int XStart = Integer.parseInt(start.split(",")[1]);
            int YStart = Integer.parseInt(start.split(",")[0]);
            String end = line.split(" -> ")[1];
            int XEnd = Integer.parseInt(end.split(",")[1]);
            int YEnd = Integer.parseInt(end.split(",")[0]);
            CoordinatesChange thisCoordinatesChange = new CoordinatesChange(XStart,YStart,XEnd,YEnd);
            coordinatesChanges.add(thisCoordinatesChange);
        }
        return coordinatesChanges;
    }

    public Pair<Integer,Integer> getRequiredGridSize(ArrayList<CoordinatesChange> coordinatesArray) {
        int maxY = 0;
        int maxX = 0;
        for (CoordinatesChange cc: coordinatesArray){

            int xStart = cc.getXStart();
            int xEnd = cc.getXEnd();
            int yStart = cc.getYStart();
            int yEnd = cc.getYEnd();
            if (xStart >= xEnd && xStart > maxX){
                maxX = xStart;
            }
            if (xEnd >= xStart && xEnd > maxX){
                maxX = xEnd;
            }
            if (yStart >= yEnd && yStart > maxY){
                maxY = yStart;
            }
            if (yEnd >= yStart && yEnd > maxY){
                maxY = yEnd;
            }
        }
        Pair<Integer,Integer> gridSize = new Pair<>(maxX+1, maxY+1);
        return gridSize;
    }

    static class Grid {
        int[][] grid;
        int sizeOfX;
        int sizeOfY;

        public Grid(int sizeOfX, int sizeOfY) {
            this.sizeOfX = sizeOfX;
            this.sizeOfY = sizeOfY;
            grid = new int[sizeOfX][sizeOfY];
        }

        public int getElement(int row, int column) {
            return grid[row][column];
        }

        public void setElement(int row, int column, int newValue) {
            grid[row][column] = newValue;
        }

        public int countElementsWithMultipleIntersections(int numberOfIntersections) {
            int elementsCount = 0;
            for (int row = 0; row < sizeOfX; row++) {
                for (int column = 0; column < sizeOfY; column++) {
                    if (grid[row][column] >= numberOfIntersections) {
                        elementsCount += 1;
                    }
                }
            }
            return elementsCount;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int row = 0; row < sizeOfX; row++) {
                for (int column = 0; column < sizeOfY; column++) {
                    stringBuffer.append(String.valueOf(grid[row][column]));
                }
                stringBuffer.append("\n");
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

            final Grid other = (Grid) obj;
            for (int row = 0; row < sizeOfX; row++) {
                for (int column = 0; column < sizeOfY; column++) {
                    if (grid[row][column] != other.getElement(row, column)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void addLineSegment(CoordinatesChange coordinatesChange) {
            int XStart = coordinatesChange.getXStart();
            int YStart = coordinatesChange.getYStart();
            int XEnd = coordinatesChange.getXEnd();
            int YEnd = coordinatesChange.getYEnd();

            //vertical line - same X, different Y
            if (XStart == XEnd & YStart != YEnd) {
                if (YStart > YEnd) {
                    for (int column = YEnd; column <= YStart; column++) {
                        grid[XStart][column] += 1;
                    }
                }
                if (YStart < YEnd) {
                    for (int column = YStart; column <= YEnd; column++) {
                        grid[XStart][column] += 1;
                    }
                }
            }

            //horizontal line - same Y, different X
            if (XStart != XEnd & YStart == YEnd) {
                if (XStart > XEnd) {
                    for (int row = XEnd; row <= XStart; row++) {
                        grid[row][YStart] += 1;
                    }
                }
                if (XStart < XEnd) {
                    for (int row = XStart; row <= XEnd; row++) {
                        grid[row][YStart] += 1;
                    }
                }
            }

            //diagonal line

            if (Math.abs(XStart - XEnd) == Math.abs(YStart - YEnd)) {
                int slope = (YEnd - YStart) / (XEnd - XStart);
                int coefficient = - slope * YStart + XStart ;
                int maxColumn = coordinatesChange.getMaxColumn();
                int minColumn = coordinatesChange.getMinColumn();
                int maxRow = coordinatesChange.getMaxRow();
                int minRow = coordinatesChange.getMinRow();
                for (int row = minRow; row <= maxRow; row++) {
                    for (int column = minColumn; column <= maxColumn; column++) {
                        if (row == coefficient + slope * column) {
                            grid[row][column] += 1;
                        }
                    }
                }
            }

        }
    }



     static class CoordinatesChange {
        int XStart;
        int XEnd;
        int YStart;
        int YEnd;

        public int getXStart() {
            return XStart;
        }

        public int getXEnd() {
            return XEnd;
        }

        public int getYStart() {
            return YStart;
        }

        public int getYEnd() {
            return YEnd;
        }

        public CoordinatesChange(int XStart, int YStart, int XEnd, int YEnd) {
            this.XStart = XStart;
            this.XEnd = XEnd;
            this.YStart = YStart;
            this.YEnd = YEnd;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj.getClass() != this.getClass()) {
                return false;
            }

            final CoordinatesChange other = (CoordinatesChange) obj;
            if (this.XStart == other.getXStart() &
                    this.YStart == other.getYStart() &
                    this.XEnd == other.getXEnd() &
                    this.YEnd == other.getYEnd()) {
                return true;
            }
            else {
                return false;
            }
        }

        public boolean isHorizontalOrVertical() {
            if (this.XStart == this.XEnd ||
                    this.YStart == this.YEnd ) {
                return true;
            }
            else {
                return false;
            }
        }

         public boolean isDiagonal() {
             if (Math.abs(this.XStart - this.XEnd) ==
                     Math.abs(this.YStart - this.YEnd) ) {
                 return true;
             }
             else {
                 return false;
             }
         }

        public String toString() {
            return "X Start: " + this.XStart + ", Y Start " + this.YStart + "; X End: " + this.XEnd + ", Y End " + this.YEnd;
        }

        public int getMaxColumn() {
            int maxY = 0;
            if ( YStart >= YEnd) {
                maxY = YStart;
            }
            else {
                maxY = YEnd;
            }
            return maxY;
        }

         public int getMinColumn() {
             int minY = 0;
             if ( YStart >= YEnd) {
                 minY = YEnd;
             }
             else {
                 minY = YStart;
             }
             return minY;
         }

         public int getMaxRow() {
             int maxX = 0;
             if ( XStart >= XEnd) {
                 maxX = XStart;
             }
             else {
                 maxX = XEnd;
             }
             return maxX;
         }

         public int getMinRow() {
             int minX = 0;
             if ( XStart >= XEnd) {
                 minX = XEnd;
             }
             else {
                 minX = XStart;
             }
             return minX;
         }
    }
}
