import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 extends Day {

    static String RESOURCE = "src/main/resources/day8_input.txt";

    public Day8() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day8().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day8().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> recordsArray = getResourceAsStringArray(resource);
        ArrayList<String> outputsArray = getAllOutputs(recordsArray);
        int totalNumberOfDigits = countDigitsWithUniqueNumberOfSegments(outputsArray);
        int answer = totalNumberOfDigits;
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        int totalSum = 0;
        ArrayList<String> recordsArray = getResourceAsStringArray(resource);
        for (String l: recordsArray){
            ArrayList<String> outputsArray = getAllOutputs(l);
            ArrayList<String> inputsArray = getAllInputs(l);
            HashMap<Integer, String> decodeDigitsMap = decodeDigits(inputsArray);
            Integer decodeOutputDigits = decodeOutputDigits(outputsArray, decodeDigitsMap);
            totalSum += decodeOutputDigits;
        }

        int answer = totalSum;
        return String.valueOf(answer);
    }

    private String[] getAllSegmentsFromLine(String inputArray) {
        return inputArray.split(" | ");
    }

    private String[] getSegmentsFromLine(String[] allSegmentsArray, String inputOrOutput) {
        int indexOfDivider = Arrays.asList(allSegmentsArray).indexOf("|");
        if (inputOrOutput.equals("input")) {
            String[] resultingArray = Arrays.copyOfRange(allSegmentsArray, 0, indexOfDivider);
            return resultingArray;
        } else {
            String[] resultingArray = Arrays.copyOfRange(allSegmentsArray, indexOfDivider + 1, allSegmentsArray.length);
            return resultingArray;
        }
    }

    public static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public ArrayList<String> getAllOutputs(ArrayList<String> rawInputArray) {
        ArrayList<String> outputs = new ArrayList<String>();
        for (String line : rawInputArray) {
            String[] allSegments = getAllSegmentsFromLine(line);
            String[] lineOutputs = getSegmentsFromLine(allSegments, "output");
            for (String output : lineOutputs) {
                outputs.add(sortString(output));
            }
        }
        return outputs;
    }
    public ArrayList<String> getAllOutputs(String rawInput) {
        ArrayList<String> outputs = new ArrayList<String>();
        String[] allSegments = getAllSegmentsFromLine(rawInput);
        String[] lineOutputs = getSegmentsFromLine(allSegments, "output");
        for (String output : lineOutputs) {
                outputs.add(sortString(output));
        }
        return outputs;
    }

    public ArrayList<String> getAllInputs(ArrayList<String> rawInputArray) {
        ArrayList<String> inputs = new ArrayList<String>();
        for (String line : rawInputArray) {
            String[] allSegments = getAllSegmentsFromLine(line);
            String[] lineInputs = getSegmentsFromLine(allSegments, "input");
            for (String input : lineInputs) {
                inputs.add(sortString(input));
            }
        }
        return inputs;
    }

    public ArrayList<String> getAllInputs(String rawInput) {
        ArrayList<String> inputs = new ArrayList<String>();
        String[] allSegments = getAllSegmentsFromLine(rawInput);
        String[] lineInputs = getSegmentsFromLine(allSegments, "input");
        for (String input : lineInputs) {
            inputs.add(sortString(input));
        }

        return inputs;
    }

    public int decodeStringToDigitUniqueLength(String observedSegments) {
        int digit = -1;
        if (observedSegments.length() == 2) {
            digit = 1;
        }
        if (observedSegments.length() == 3) {
            digit = 7;
        }
        if (observedSegments.length() == 4) {
            digit = 4;
        }
        if (observedSegments.length() == 7) {
            digit = 8;
        }
        return digit;
    }

    public int countDigitsWithUniqueNumberOfSegments(ArrayList<String> array) {
        int totalCount = 0;
        for (String record : array) {
            int digit = decodeStringToDigitUniqueLength(record);
            if (digit != -1) {
                totalCount += 1;
            }
        }
        return totalCount;

    }

    public HashMap<Integer, String> decodeUniqueLengthDigits(ArrayList<String> lineInputs) {
        HashMap<Integer, String> segmentsEncodedDecoded = new HashMap<Integer, String>();
        HashMap<String, String> encodingRules = new HashMap<String, String>();

        for (String code : lineInputs) {
            int decodedDigit = decodeStringToDigitUniqueLength(code);
            if (decodedDigit != -1) {
                segmentsEncodedDecoded.put(decodedDigit, code);
            }

        }
        return segmentsEncodedDecoded;
    }

    public static String[] singleChars(String s) {
        return s.split("(?!^)");
    }

    public  String[] intersection(String[] a, String[] b) {
        return Stream.of(a)
                .filter(Arrays.asList(b)::contains)
                .toArray(String[]::new);
    }

    public String inAButNotInB(String[] a, String[] b) {
        ArrayList<String> inAButNotInB = new ArrayList<>();
        for (String aElement: a){
            if (!Arrays.stream(b).anyMatch(aElement::equals) & !inAButNotInB.contains(aElement)){
                inAButNotInB.add(aElement);
            }
        }
        //return inAButNotInB.toArray(new String[0]);
        return String.join("",inAButNotInB);
    }

    public String segmentsNotInEveryDigitsInArray(ArrayList<String> array){
        String[] allSegments = "abcdefg".split("");
        int numberOfDigits = array.size();
        StringBuffer result = new StringBuffer();
        for (String segment: allSegments){
            int numberOfDigitsContainThisSegment = 0;
            for (String digit : array){
                if (digit.contains(segment)) {
                    numberOfDigitsContainThisSegment += 1;
                }
            }
            if( numberOfDigitsContainThisSegment != numberOfDigits){
                result.append(segment);
            }
        }
        return String.valueOf(result);
    }

    public String segmentsInEveryDigitsInArray(ArrayList<String> array){
        String[] allSegments = "abcdefg".split("");
        int numberOfDigits = array.size();
        StringBuffer result = new StringBuffer();
        for (String segment: allSegments){
            int numberOfDigitsContainThisSegment = 0;
            for (String digit : array){
                if (digit.contains(segment)) {
                    numberOfDigitsContainThisSegment += 1;
                }
            }
            if( numberOfDigitsContainThisSegment == numberOfDigits){
                result.append(segment);
            }
        }
        return String.valueOf(result);
    }

    public String inArrayAButNotInArrayB( ArrayList<String> arrayA,
                                              ArrayList<String> arrayB) {
        String digitsAString = String.join("", arrayA);;
        String[] aStringArray = digitsAString.split("");
        String digitBString = String.join("", arrayB);;
        String[] bStringArray = digitBString.split("");
        String inAButNotInB = inAButNotInB(aStringArray, bStringArray);
        return inAButNotInB;
    }

    public ArrayList<String> getAllEncodedDigitsOfLengthX(ArrayList<String> inputsArray, int X){
        ArrayList<String> encodedDigits = new ArrayList<String>();
        for (String input: inputsArray){
            if( input.length()== X){
                encodedDigits.add(input);
            }
        }
        return encodedDigits;
    }

    public String removeDuplicates(StringBuffer sb){
        return Arrays.asList(sb.toString().split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
    }

    public HashMap<Integer, String> decodeDigits(ArrayList<String> inputsArray){
        HashMap<Integer, String> decodedDigits = decodeUniqueLengthDigits(inputsArray);
        ArrayList<String> digitOne = new ArrayList<String>(Arrays.asList(decodedDigits.get(1)));
        ArrayList<String> digitFour = new ArrayList<String>(Arrays.asList(decodedDigits.get(4)));
        ArrayList<String> digitOneFourSeven = new ArrayList<String>(Arrays.asList(decodedDigits.get(1),
                        decodedDigits.get(4),
                        decodedDigits.get(7)));
        ArrayList<String> digitSeven = new ArrayList<String>(Arrays.asList(decodedDigits.get(7)));
        ArrayList<String> digitEight = new ArrayList<String>(Arrays.asList(decodedDigits.get(8)));
        ArrayList<String> digitsOfLength5 = getAllEncodedDigitsOfLengthX(inputsArray, 5);
        ArrayList<String> digitsOfLength6 = getAllEncodedDigitsOfLengthX(inputsArray, 6);

        HashMap<String, String> encodingRules = new HashMap<String, String>();

        /**Rule - In 1 but not in 7 **/
        encodingRules.put("a",inArrayAButNotInArrayB(digitSeven,digitOne));


        /**Rule - In all digits of length 6**/
        encodingRules.put("abfg",segmentsInEveryDigitsInArray(digitsOfLength6));


        /**Rule - Not in every digit of length 6**/
        encodingRules.put("cde",segmentsNotInEveryDigitsInArray(digitsOfLength6));

        /**Rule - In 8 but not in 1,4 or 7**/
        encodingRules.put("eg",inArrayAButNotInArrayB(digitEight,digitOneFourSeven));

        /**Rule - In all digits of length 5**/
        encodingRules.put("adg",segmentsInEveryDigitsInArray(digitsOfLength5));

        /**Rule - In 4 but not in 1**/
        encodingRules.put("bd",inArrayAButNotInArrayB(digitFour,digitOne));

        /**Digit 2**/
        StringBuffer decodedDigitTwo = new StringBuffer();
        decodedDigitTwo.append(encodingRules.get("a"));
        decodedDigitTwo.append(encodingRules.get("cde"));
        decodedDigitTwo.append(encodingRules.get("eg"));
        String decodedTwo = sortString(removeDuplicates(decodedDigitTwo));
        decodedDigits.put(2,decodedTwo);
        encodingRules.put("acdeg",decodedTwo);

        /**Digit 5**/
        StringBuffer decodedDigitFive = new StringBuffer();
        decodedDigitFive.append(encodingRules.get("abfg"));
        decodedDigitFive.append(encodingRules.get("bd"));
        String decodedFive = sortString(removeDuplicates(decodedDigitFive));
        decodedDigits.put(5,decodedFive);
        encodingRules.put("abdfg",decodedFive);

        /**Digit 3**/
        for (String key : digitsOfLength5) {
            if (!key.equals(decodedFive) && !key.equals(decodedTwo)
            )
            {   String decodedThree = key;
                decodedDigits.put(3,decodedThree);
                encodingRules.put("acdfg",decodedThree);
            }
        }

        /**Digit 6**/
        StringBuffer decodedDigitSix = new StringBuffer();
        decodedDigitSix.append(encodingRules.get("abdfg"));
        decodedDigitSix.append(encodingRules.get("eg"));
        String decodedSix = sortString(removeDuplicates(decodedDigitSix));
        decodedDigits.put(6,decodedSix);
        encodingRules.put("abdefg",decodedSix);

        /**Rule - In 6 but not in 5**/
        ArrayList<String> digitSix = new ArrayList<String>(Arrays.asList(decodedDigits.get(6)));
        ArrayList<String> digitFive = new ArrayList<String>(Arrays.asList(decodedDigits.get(5)));
        encodingRules.put("e",inArrayAButNotInArrayB(digitSix,digitFive));

        /**Digit 0**/
        String encodedLetterE = encodingRules.get("e");

        for (String key : digitsOfLength6) {
            if (key.contains(encodedLetterE) && !key.equals(decodedSix))
            {   String decodedZero = key;
                decodedDigits.put(0,decodedZero);
                encodingRules.put("abcefg",decodedZero);
            }
        }

        /**Digit 9**/
        for (String key : digitsOfLength6) {
            if (!key.equals(decodedDigits.get(0)) && !key.equals(decodedSix)
            )
            {   String decodedNine = key;
                decodedDigits.put(9,decodedNine);
                encodingRules.put("abcdfg",decodedNine);
            }
        }

        return  decodedDigits;
    }

    public Integer decodeOutputDigits(ArrayList<String> outputsArray, HashMap<Integer, String> decodedInputs) {
        StringBuffer outputString = new StringBuffer();
        for (String outputDigit : outputsArray){
            for (Integer digit : decodedInputs.keySet()) {
                if ( outputDigit.equals(decodedInputs.get(digit))) {
                    outputString.append(String.valueOf(digit));
                }
            }
        }
        return Integer.parseInt(outputString.toString());

    }

}
