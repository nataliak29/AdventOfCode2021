import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day9Test extends TestCase {
    Day9 day9 = new Day9();
    private static final String RESOURCE = "src/test/resources/day9_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day9.partOneAnswer(RESOURCE);
        String expectedAnswer = "15";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day9.partTwoAnswer(RESOURCE);
        String expectedAnswer = "-1";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testIsLowPointTrue(){
        int[][] inputGrid = {{2,1,3},{0,6,4}};
        Day9.LocationPoint lp = new Day9.LocationPoint(inputGrid, 1, 0);
        assertEquals(true, lp.isLowPoint());
    }

    public void testIsLowPointFalse(){
        int[][] inputGrid = {{2,1,3},{0,6,4}};
        Day9.LocationPoint lp = new Day9.LocationPoint(inputGrid, 2, 0);
        assertEquals(false, lp.isLowPoint());
    }

    public void testGetGrid(){
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("213", "064"));
        int[][] expectedGrid = {{2,1,3},{0,6,4}};

        int[][] actualGrid = day9.getGrid(inputArrayString);
        for (int i = 0; i < 2; i ++) {
           for (int j = 0; j < 3; j++){
               assertEquals(expectedGrid[i][j], actualGrid[i][j]);
            }
        }
    }


    public void testIsLowPoint(){
        int[][] inputGrid = {{2,1,3},{0,6,4}};
        int expectedNumberOfLowPoints = 2;
        int actualNumberOfLowPoints = 0;
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                Day9.LocationPoint lp = new Day9.LocationPoint(inputGrid, x, y);
                if (lp.isLowPoint() ) {
                    actualNumberOfLowPoints += 1;
                }
            }
        }
        assertEquals(expectedNumberOfLowPoints, actualNumberOfLowPoints);
    }

}