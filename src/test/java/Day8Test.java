import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        String expectedAnswer = "61229";
        assertEquals(expectedAnswer, actualAnswer);
    }


    public void testGetAllOutputs() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("be | fdgacbe", "fgdeca fcdbega | efabcd yyy"));
        ArrayList<String> expectedArray = new ArrayList<String>(
                Arrays.asList("abcdefg", "abcdef", "yyy"));
        ArrayList<String> actualArray = day8.getAllOutputs(inputArrayString);
        assertEquals(actualArray.get(0), expectedArray.get(0));
        assertEquals(actualArray.get(1), expectedArray.get(1));
        assertEquals(actualArray.get(2), expectedArray.get(2));
    }

    public void testGetAllInputs() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("be | fdgacbe", "fgdeca fcdbega | efabcd yyy"));
        ArrayList<String> expectedArray = new ArrayList<String>(
                Arrays.asList("be", "acdefg", "abcdefg"));
        ArrayList<String> actualArray = day8.getAllInputs(inputArrayString);
        assertEquals(actualArray.get(0), expectedArray.get(0));
        assertEquals(actualArray.get(1), expectedArray.get(1));
        assertEquals(actualArray.get(2), expectedArray.get(2));
    }

    public void testDecodeStringToDigitTwo() {
        String input = "ab";
        int expectedDigit = 1;
        int actualDigit = day8.decodeStringToDigitUniqueLength(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitThree() {
        String input = "abb";
        int expectedDigit = 7;
        int actualDigit = day8.decodeStringToDigitUniqueLength(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitFour() {
        String input = "abbc";
        int expectedDigit = 4;
        int actualDigit = day8.decodeStringToDigitUniqueLength(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitSeven() {
        String input = "abbcabc";
        int expectedDigit = 8;
        int actualDigit = day8.decodeStringToDigitUniqueLength(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testDecodeStringToDigitOther() {
        String input = "abbabcabcdb";
        int expectedDigit = -1;
        int actualDigit = day8.decodeStringToDigitUniqueLength(input);
        assertEquals(actualDigit, expectedDigit);
    }

    public void testCountDigitsWithUniqueNumberOfSegments() {
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("bw", "fgd", "fcdb", "efabc", "yyyyyy", "absabsa", "aaaabbbb"));
        int expectedCount = 4;
        int actualCount = day8.countDigitsWithUniqueNumberOfSegments(inputArrayString);
        assertEquals(actualCount, expectedCount);
    }

    public void testDecodeAllDigits(){
        ArrayList<String> inputArrayString = new ArrayList<String>(
                Arrays.asList("acedgfb", "cdfbe", "gcdfa", "fbcad", "dab", "cefabd", "cdfgeb", "eafb", "cagedb", "ab"));
        day8.decodeUniqueLengthDigits(inputArrayString);
    }

    public void testInArrayAButNotInArrayB(){
        ArrayList<String> inputArrayA = new ArrayList<String>(
                Arrays.asList("abcd", "efdsr", "wwey"));
        ArrayList<String> inputArrayB = new ArrayList<String>(
                Arrays.asList("abcd", "efdsr", "zzz"));
        String actualArray = day8.inArrayAButNotInArrayB(inputArrayA,inputArrayB);
        String expectedArray = "wy";
        assertEquals(expectedArray,actualArray);
    }

    public void testIntersection(){
        String[] a = {"a","b"};
        String[] b = {"b","c", "d"};
        String[] actualArray = day8.intersection(a,b);
        String[] expectedArray = {"b"};
        assertTrue(Arrays.deepEquals(expectedArray, actualArray));
    }

    public void testInAButNotInB(){
        String[] a = {"a","b"};
        String[] b = {"b","c", "d"};
        String actualArray = day8.inAButNotInB(a,b);
        String expectedArray = "a";
        assertEquals(expectedArray,actualArray);
    }

    public void testSingleChars() {
        String inputString = "abc";
        String[] actualArray = day8.singleChars(inputString);
        String[] expectedArray = {"a","b","c"};
        assertTrue(Arrays.deepEquals(expectedArray, actualArray));
    }

    public void testDecodeDigits(){
        ArrayList<String> inputsArray = new ArrayList<String>(
                Arrays.asList("acedgfb", "cdfbe", "gcdfa", "fbcad", "dab", "cefabd", "cdfgeb", "eafb", "cagedb", "ab"));
        HashMap<Integer, String> actualMap = day8.decodeDigits(inputsArray);
        HashMap<Integer, String> expectedMap = new HashMap<>();
        expectedMap.put(0,"cagedb");
        expectedMap.put(1,"ab");
        expectedMap.put(2,"acdfg");
        expectedMap.put(3,"fbcad");
        expectedMap.put(4,"eafb");
        expectedMap.put(5,"bcdef");
        expectedMap.put(6,"bcdefg");
        expectedMap.put(7,"dab");
        expectedMap.put(8,"acedgfb");
        expectedMap.put(9,"cdfgeb");
        assertEquals(expectedMap,actualMap);
    }

    public void testError(){
        String l = "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb";
        ArrayList<String> outputsArray = day8.getAllOutputs(l);
        ArrayList<String> inputsArray = day8.getAllInputs(l);
        System.out.println("INPUTS");
        for (String in : inputsArray){
            System.out.println(in);
        }
        System.out.println("INPUTS");
        HashMap<Integer, String> decodeDigitsMap = day8.decodeDigits(inputsArray);
        Integer decodeOutputDigits = day8.decodeOutputDigits(outputsArray, decodeDigitsMap);
        System.out.println("record " + l + " output " + decodeOutputDigits);

    }


}
