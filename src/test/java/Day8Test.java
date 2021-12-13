import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day8Test extends TestCase {
    Day8 day8 = new Day8();
    private static final String RESOURCE = "src/test/resources/day8_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day8.partOneAnswer(RESOURCE);
        String expectedAnswer = "26";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testPartTwoAnswer() throws FileNotFoundException {
        String actualAnswer = day8.partTwoAnswer(RESOURCE);
        String expectedAnswer = "-1";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testGetAllSegments() {
        String input = "be | fdgacbe";
        String[]  expectedArray = {"be","|","fdgacbe"};
        assertTrue(Arrays.deepEquals(expectedArray, day8.getAllSegmentsFromLine(input)));
    }

    public void testGetSegmentsInput() {
        String[]  inputArray = {"be","cbdgef","|","fdgacbe"};
        String[]  expectedArray = {"be","cbdgef"};
        String[]  actualArray = day8.getSegmentsFromLine(inputArray, "input");
        assertTrue(Arrays.deepEquals(expectedArray, actualArray));
    }

    public void testGetSegmentsOutput() {
        String[]  inputArray = {"be","cbdgef","|","fdgacbe"};
        String[]  expectedArray = {"fdgacbe"};
        String[]  actualArray = day8.getSegmentsFromLine(inputArray, "output");
        assertTrue(Arrays.deepEquals(expectedArray, actualArray));
    }

    public void testGetAllOutputs() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("be | fdgacbe", "fgdeca fcdbega | efabcd yyy"));
        ArrayList<String> expectedArray = new ArrayList<String>(
                Arrays.asList("fdgacbe", "efabcd", "yyy"));
        ArrayList<String> actualArray = day8.getAllOutputs(inputArrayString);
        assertEquals(actualArray.get(0), expectedArray.get(0));
        assertEquals(actualArray.get(1), expectedArray.get(1));
        assertEquals(actualArray.get(2), expectedArray.get(2));
    }

    public void testGetAllInputs() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("be | fdgacbe", "fgdeca fcdbega | efabcd yyy"));
        ArrayList<String> expectedArray = new ArrayList<String>(
                Arrays.asList("be", "fgdeca", "fcdbega"));
        ArrayList<String> actualArray = day8.getAllInputs(inputArrayString);
        assertEquals(actualArray.get(0), expectedArray.get(0));
        assertEquals(actualArray.get(1), expectedArray.get(1));
        assertEquals(actualArray.get(2), expectedArray.get(2));
    }

    public void testDecodeStringToDigitTwo() {
        String input = "ab";
        int expectedDigit = 1;
        int actualDigit = day8.decodeStringToDigit(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitThree() {
        String input = "abb";
        int expectedDigit = 7;
        int actualDigit = day8.decodeStringToDigit(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitFour() {
        String input = "abbc";
        int expectedDigit = 4;
        int actualDigit = day8.decodeStringToDigit(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitSeven() {
        String input = "abbcabc";
        int expectedDigit = 8;
        int actualDigit = day8.decodeStringToDigit(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitOther() {
        String input = "abbabcabcdb";
        int expectedDigit = -1;
        int actualDigit = day8.decodeStringToDigit(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testCountDigitsWithUniqueNumberOfSegments() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("bw", "fgd", "fcdb", "efabc", "yyyyyy", "absabsa", "aaaabbbb"));
        int expectedCount = 4;
        int actualCount = day8.countDigitsWithUniqueNumberOfSegments(inputArrayString);
        assertEquals(actualCount, expectedCount);
    }



}
