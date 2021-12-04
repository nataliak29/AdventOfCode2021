import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Day3Test extends TestCase {
    private static final String RESOURCE = "src/test/resources/day3_test.txt";
    private static final String RESOURCE_SMALL = "src/test/resources/day3_small_test.txt";
    Day3 day3 = new Day3();

    public void testGetMostFrequentBitZeros() {
        ArrayList<Integer> inputList = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        int actualResult = day3.getMostFrequentBitFromArray(inputList);
        int expectedResult = 0;
        assertEquals(actualResult,expectedResult);
    }

    public void testGetMostFrequentBitOnes() {
        ArrayList<Integer> inputList = new ArrayList<Integer>(Arrays.asList(1,1,1,1));
        int actualResult = day3.getMostFrequentBitFromArray(inputList);
        int expectedResult = 1;
        assertEquals(actualResult,expectedResult);
    }

    public void testGetMostFrequentBitMixZero() {
        ArrayList<Integer> inputList = new ArrayList<Integer>(Arrays.asList(1,0,1,0,0));
        int actualResult = day3.getMostFrequentBitFromArray(inputList);
        int expectedResult = 0;
        assertEquals(actualResult,expectedResult);
    }

    public void testGetMostFrequentBitMixOne() {
        ArrayList<Integer> inputList = new ArrayList<Integer>(Arrays.asList(1,1,1,0,0));
        int actualResult = day3.getMostFrequentBitFromArray(inputList);
        int expectedResult = 1;
        assertEquals(actualResult,expectedResult);
    }

    public void testGetMostFrequentBitEqual() {
        ArrayList<Integer> inputList = new ArrayList<Integer>(Arrays.asList(1,1,0,0));
        int actualResult = day3.getMostFrequentBitFromArray(inputList);
        int expectedResult = 1;
        assertEquals(actualResult,expectedResult);
    }



    public void testCreateMatrixFromResource() throws IOException {
        ArrayList<ArrayList<Integer>> actualArray =  day3.createMatrixFromResource(RESOURCE_SMALL);
        ArrayList<ArrayList<Integer>> expectedArray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> rowOne = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        ArrayList<Integer> rowTwo = new ArrayList<Integer>(Arrays.asList(1,1,1,0));
        ArrayList<Integer> rowThree = new ArrayList<Integer>(Arrays.asList(1,1,0,0));
        expectedArray.add(rowOne);
        expectedArray.add(rowTwo);
        expectedArray.add(rowThree);
        assertTrue(Arrays.deepEquals(new ArrayList[]{actualArray},
                new ArrayList[]{expectedArray}));
    }


    public void testGetEpsilonAndGammaArray() {
        ArrayList<ArrayList<Integer>> inputArray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> rowOne = new ArrayList<Integer>(Arrays.asList(0,1,1));
        ArrayList<Integer> rowTwo = new ArrayList<Integer>(Arrays.asList(0,1,1));
        ArrayList<Integer> rowThree = new ArrayList<Integer>(Arrays.asList(0,1,0));
        inputArray.add(rowOne);
        inputArray.add(rowTwo);
        inputArray.add(rowThree);
        String[] expectedList = {"011","100"};
        String[] actualList = day3.getEpsilonAndGammaArray(inputArray);
        assertTrue(Arrays.deepEquals(actualList, expectedList));
    }

    public void testPartOneAnswerArray() throws IOException {
        assertEquals(String.valueOf(198),day3.partOneAnswer(RESOURCE));
    }


    public void testGetColumnFromMatrix() {
        ArrayList<ArrayList<Integer>> inputArray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> rowOne = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        ArrayList<Integer> rowTwo = new ArrayList<Integer>(Arrays.asList(1,1,1,0));
        ArrayList<Integer> rowThree = new ArrayList<Integer>(Arrays.asList(1,1,0,0));
        inputArray.add(rowOne);
        inputArray.add(rowTwo);
        inputArray.add(rowThree);
        int index = 2;
        ArrayList<Integer> expectedArray = new ArrayList<Integer>(Arrays.asList(0,1,0));
        ArrayList<Integer> actualArray = day3.getColumnFromMatrix(inputArray,index);
        assertEquals(expectedArray,actualArray);
    }

    public void testPartTwoAnswer() throws IOException {
        assertEquals(String.valueOf(230),day3.partTwoAnswer(RESOURCE));
    }
}