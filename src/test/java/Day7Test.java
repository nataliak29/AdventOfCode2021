import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day7Test extends TestCase {
    Day7 day7 = new Day7();
    private static final String RESOURCE = "src/test/resources/day7_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day7.partOneAnswer(RESOURCE);
        String expectedAnswer = "37";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day7.partTwoAnswer(RESOURCE);
        String expectedAnswer = "168";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testCalculateMedianOdd() {
        int[]  inputArray = {2,3,4,5,6};
        int actualMedian  = day7.calculateMedian(inputArray);
        int expectedMedian = 4;
        assertEquals(actualMedian, expectedMedian);
    }

    public void testCalculateMedianEven() {
        int[]  inputArray = {2,3,5,10,12,13};
        int actualMedian  = day7.calculateMedian(inputArray);
        int expectedMedian = 8;
        assertEquals(actualMedian, expectedMedian);
    }

    public void testGetFuelUsed() {
        int[]  inputArray = {2,3,5,10,12,13};
        int median = 8;
        int actualTotalFuel = day7.getFuelUsed(inputArray, median);
        int expectedTotalFuel = 25;
        assertEquals(actualTotalFuel, expectedTotalFuel);
    }

    public void testCalculateMean() {
        int[]  inputArray = {2,3,33,10,12,13};
        int actualMean  = day7.calculateMean(inputArray);
        int expectedMean = 12;
        assertEquals(actualMean, expectedMean);
    }

    public void testGetFuelUsedNonLinear() {
        ArrayList<String> inputArray = day7.getResourceAsStringArray(RESOURCE);
        int[] integerList = day7.getIntegersList(inputArray);
        int actualTotalFuel = day7.getFuelUsedNonLinear(integerList, 2);
        int expectedTotalFuel = 206;
        assertEquals(actualTotalFuel, expectedTotalFuel);
    }

}
