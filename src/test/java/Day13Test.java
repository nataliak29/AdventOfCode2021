import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day13Test extends TestCase {
    Day13 day13 = new Day13();
    private static final String RESOURCE = "src/test/resources/day13_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day13.partOneAnswer(RESOURCE);
        String expectedAnswer = "17";
        assertEquals(expectedAnswer, actualAnswer);
    }


    public void testGetAxisToFoldOnY() {
        String instruction = "fold along y=7";
        String actualResult = day13.getAxisToFoldOn(instruction);
        assertEquals("Y", actualResult);
    }

    public void testGetAxisToFoldOnX() {
        String instruction = "fold along x=5";
        String actualResult = day13.getAxisToFoldOn(instruction);
        assertEquals("X", actualResult);
    }

    public void testGetAxisValueToFoldOn() {
        String instruction = "fold along y=7";
        int actualResult = day13.getAxisValueToFoldOn(instruction);;
        assertEquals(7, actualResult);
    }



    public void testProcessOneSetOfCoordinates() {
        int[][] grid = {{0, 0, 0},
                {1, 1, 1},
        };
        Day13.Grid gr = new Day13.Grid(grid);
        String coordinates = "0,0";
        int[][] expectedGridValues = {
                {1,0,0},
                {1,1,1},
        };
        Day13.Grid expectedGrid = new Day13.Grid(expectedGridValues);
        assertEquals(expectedGrid,day13.processOneSetOfCoordinates(gr,coordinates));
    }

    public void testFoldOnYAxis() {
        int[][] grid = {{0,0,0},
                {1,1,1},
                {1,1,1},
                {1,1,1},
        };
        Day13.Grid gr = new Day13.Grid(grid);
        int[][] expectedGridValues = {
                {1,1,1},
                {1,1,1},
        };
        Day13.Grid expectedGrid = new Day13.Grid(expectedGridValues);
        assertEquals(expectedGrid,gr.foldOnYAxis(2));

    }


    public void testFoldOnXAxis() {
        int[][] grid = {
                {0,0,0,0},
                {1,1,1,0},
                {1,1,1,0},
                {1,1,1,0},
        };
        Day13.Grid gr = new Day13.Grid(grid);
        int[][] expectedGridValues = {
                {0,0},
                {1,1},
                {1,1},
                {1,1}
        };
        Day13.Grid expectedGrid = new Day13.Grid(expectedGridValues);
        assertEquals(expectedGrid,gr.foldOnXAxis(2));

    }


}
