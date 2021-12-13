import junit.framework.TestCase;

import java.io.FileNotFoundException;

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

    public void testCalculateMeanDataFromTestsFile() {
        int[]  inputArray = {16,1,2,0,4,2,7,1,2,14};
        int actualMean  = day7.calculateMean(inputArray);
        int expectedMean = 5;
        assertEquals(actualMean, expectedMean);
    }

    public void testCalculateMean() {
        int[]  inputArray = {2,3,33,10,12,13};
        int actualMean  = day7.calculateMean(inputArray);
        int expectedMean = 12;
        assertEquals(actualMean, expectedMean);
    }

    public void testGetFuelUsedNonLinearFromTestFile() {
        int[] inputArray = {16,1,2,0,4,2,7,1,2,14};
        int actualTotalFuel = day7.findMinimalFuelSpend(inputArray, day7.calculateMean(inputArray)) ;
        int expectedTotalFuel = 168;
        assertEquals(actualTotalFuel, expectedTotalFuel);
    }

}
