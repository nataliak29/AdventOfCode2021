import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day1Test extends TestCase {
    static String RESOURCE = "src/test/resources/day1_test.txt";

    public void testMeasurementIncrease(){
        Day1 day1 = new Day1();
        ArrayList<Integer> testArray =  new ArrayList<Integer>();
        testArray.add(10);
        testArray.add(12);
        int expectedResult = 1;
        int observedResult = day1.measurementIncrease(testArray);
        assertEquals(expectedResult,observedResult);
    }

    public void testMeasurementIncreaseEqual(){
        Day1 day1 = new Day1();
        ArrayList<Integer> testArray =  new ArrayList<Integer>();
        testArray.add(10);
        testArray.add(10);
        int expectedResult = 0;
        int observedResult = day1.measurementIncrease(testArray);
        assertEquals(expectedResult,observedResult);
    }

    public void testMeasurementIncreaseZero(){
        Day1 day1 = new Day1();
        ArrayList<Integer> testArray =  new ArrayList<Integer>();
        testArray.add(10);
        testArray.add(9);
        int expectedResult = 0;
        int observedResult = day1.measurementIncrease(testArray);
        assertEquals(expectedResult,observedResult);
    }

    public void testMeasurementIncreaseThree(){
        Day1 day1 = new Day1();
        ArrayList<Integer> testArray =  new ArrayList<Integer>();
        testArray.add(10);
        testArray.add(12);
        testArray.add(15);
        testArray.add(18);
        int expectedResult = 3;
        int observedResult = day1.measurementIncrease(testArray);
        assertEquals(expectedResult,observedResult);
    }

    public void testMeasurementIncreaseResource() throws FileNotFoundException {
        Day1 day1 = new Day1();
        String expectedResult = "7";
        String observedResult = day1.partOneAnswer(RESOURCE);
        assertEquals(expectedResult,observedResult);
    }

    public void testMeasurementIncreaseSlidingWindow(){
        Day1 day1 = new Day1();
        ArrayList<Integer> testArray =  new ArrayList<Integer>();
        testArray.add(10);
        testArray.add(12);
        testArray.add(15);
        testArray.add(18);
        testArray.add(18);
        testArray.add(18);
        int expectedResult = 3;
        int observedResult = day1.measurementIncreaseSlidingWindow(testArray);
        assertEquals(expectedResult,observedResult);
    }

    public void testMeasurementIncreaseSlidingResource() throws FileNotFoundException {
        Day1 day1 = new Day1();
        String expectedResult = "5";
        String observedResult = day1.partTwoAnswer(RESOURCE);
        assertEquals(expectedResult,observedResult);
    }
}