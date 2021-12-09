import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day5Test extends TestCase {
    Day5 day5 = new Day5();
    private static final String RESOURCE = "src/test/resources/day5_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day5.partOneAnswer(RESOURCE);
        String expectedAnswer = "5";
        assertEquals(expectedAnswer,actualAnswer);
    }

    public void testPartTwoAnswer() {
    }

    public void testGetCoordinatesChangeArray() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("0,9 -> 5,9", "819,371 -> 819,705"));
        ArrayList<Day5.CoordinatesChange> expectedList = new ArrayList<Day5.CoordinatesChange>();
        Day5.CoordinatesChange firstCoordinatesChange = new Day5.CoordinatesChange(9, 0, 9, 5);
        Day5.CoordinatesChange secondCoordinatesChange = new Day5.CoordinatesChange(371, 819, 705, 819);
        expectedList.add(firstCoordinatesChange);
        expectedList.add(secondCoordinatesChange);
        assertEquals(day5.getCoordinatesChangeArray(inputArrayString).get(0), expectedList.get(0));
        assertEquals(day5.getCoordinatesChangeArray(inputArrayString).get(1), expectedList.get(1));
    }

    public void testIsVertical() {
        Day5.CoordinatesChange firstCoordinatesChange = new Day5.CoordinatesChange(0, 9, 5, 9);
        assertEquals(firstCoordinatesChange.isHorizontalOrVertical(),true);
    }

    public void testIsHorizontalOrVerticalFalse() {
        Day5.CoordinatesChange firstCoordinatesChange = new Day5.CoordinatesChange(0, 2, 5, 9);
        assertEquals(firstCoordinatesChange.isHorizontalOrVertical(),false);
    }

    public void testIsHorizontal() {
        Day5.CoordinatesChange firstCoordinatesChange = new Day5.CoordinatesChange(5, 2, 5, 9);
        assertEquals(firstCoordinatesChange.isHorizontalOrVertical(),true);
    }

    public void testGridEquals() {
        Day5.Grid grid = new Day5.Grid(5, 2);
        Day5.Grid gridCopy = new Day5.Grid(5, 2);
        assertEquals(grid.equals(gridCopy),true);
    }

    public void testAddLineSegmentHorizontal() {
        Day5.Grid expectedGrid = new Day5.Grid(10, 10);
        expectedGrid.setElement(0, 9, 1);
        expectedGrid.setElement(1, 9, 1);
        expectedGrid.setElement(2, 9, 1);
        Day5.Grid actualGrid = new Day5.Grid(10, 10);
        Day5.CoordinatesChange coordinatesChange = new Day5.CoordinatesChange(0, 9, 2, 9);
        actualGrid.addLineSegment(coordinatesChange);
        assertEquals(actualGrid, expectedGrid);
    }

    public void testAddLineSegmentVertical() {
        Day5.Grid expectedGrid = new Day5.Grid(5, 7);
        expectedGrid.setElement(0, 2, 1);
        expectedGrid.setElement(1, 2, 1);
        expectedGrid.setElement(2, 2, 1);
        Day5.Grid actualGrid = new Day5.Grid(5, 7);
        Day5.CoordinatesChange coordinatesChange = new Day5.CoordinatesChange(0, 2, 2, 2);
        actualGrid.addLineSegment(coordinatesChange);
        assertEquals(actualGrid, expectedGrid);
    }


}