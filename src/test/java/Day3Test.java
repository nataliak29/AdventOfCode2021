import junit.framework.TestCase;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3Test extends TestCase {
    private static final String RESOURCE = "src/test/resources/day3_test.txt";
    private static final String RESOURCE_SMALL = "src/test/resources/day3_small_test.txt";
    Day3 day3 = new Day3();

    public void testGetMostFrequentBitZeros() {
        int[] inputList  = {0,0,0,0};
        int actualResult = day3.getMostFrequentBit(inputList);
        int expectedResult = 0;
        assertEquals(actualResult,expectedResult);
    }

    public void testGetMostFrequentBitOnes() {
        int[] inputList  = {1,1,1,1};
        int actualResult = day3.getMostFrequentBit(inputList);
        int expectedResult = 1;
        assertEquals(actualResult,expectedResult);
    }

    public void testGetMostFrequentBitMixZero() {
        int[] inputList  = {1,0,1,0,0};
        int actualResult = day3.getMostFrequentBit(inputList);
        int expectedResult = 0;
        assertEquals(actualResult,expectedResult);
    }

    public void testGetMostFrequentBitMixOne() {
        int[] inputList  = {1,1,1,0,0};
        int actualResult = day3.getMostFrequentBit(inputList);
        int expectedResult = 1;
        assertEquals(actualResult,expectedResult);
    }

    public void testSplitColumnsIntoArrayFromResource() throws IOException {
        int [][] actualArray =  day3.splitColumnsIntoArrayFromResource(RESOURCE_SMALL);
        int [][] expectedArray = {{0,1,1}, {0,1,1}, {0,1,0}, {0,0,0}};
        assertTrue(Arrays.deepEquals(actualArray, expectedArray));
    }

    public void testCreateMatrixFromResource() throws IOException {
        int [][] actualArray =  day3.createMatrixFromResource(RESOURCE_SMALL);
        int [][] expectedArray = {{0,0,0,0}, {1,1,1,0},{1, 1,0, 0}};
        assertTrue(Arrays.deepEquals(actualArray, expectedArray));

    }

    public void testGetEpsilonAndGamma() {
        int [][] inputArray = {{0,1,1}, {0,1,1}, {0,1,0}};
        String[] expectedList = {"110","001"};
        String[] actualList = day3.getEpsilonAndGamma(inputArray);
        assertTrue(Arrays.deepEquals(actualList, expectedList));
    }

    public void testPartOneAnswer() throws IOException {
        assertEquals(String.valueOf(198),day3.partOneAnswer(RESOURCE));
    }

    public void testGetListFromMatrix() {
        int numberOfDifferentElements = 0;
        int [][] inputArray = {{0,1,1,2}, {0,1,1,1}, {0,1,0,3}};
        int index = 3;
        int [] expectedList = {2,1,3};

        int [] actualList = day3.getListFromMatrix(inputArray,index);
        for (int k=0; k<expectedList.length; k++) {
            if( actualList[k] != expectedList[k]){
                numberOfDifferentElements++;
            }
        }
        //assertEquals(expectedList,actualList);
        assertEquals(0,numberOfDifferentElements);
    }

    //public void testOxygenGeneratorRating() throws IOException {
        //int [][] actualList = day3.oxygenGeneratorRating(RESOURCE);
        //for (String s:actualList){
        //    System.out.println(s);
       // }
        //assertTrue(Arrays.deepEquals(actualList, expectedList));
    //}

    public void testPartTwoAnswer() {
    }
}