import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day8 extends Day {

    static String RESOURCE = "src/main/resources/day8_input.txt";

    public Day8() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day8().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day8().partTwoAnswer(RESOURCE));
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
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        //int[] integerList = getIntegersList(inputArray);
        int answer = -1;
        return String.valueOf(answer);
    }

    public String[] getAllSegmentsFromLine(String inputArray){
        return  inputArray.split(" | ");
    }

    public String[] getSegmentsFromLine(String[] allSegmentsArray, String inputOrOutput){
        int indexOfDivider = Arrays.asList(allSegmentsArray).indexOf("|");
        if ( inputOrOutput.equals("input")){
            String[] resultingArray = Arrays.copyOfRange(allSegmentsArray, 0, indexOfDivider);
            return  resultingArray;
        }
        else {
            String[] resultingArray = Arrays.copyOfRange(allSegmentsArray, indexOfDivider + 1, allSegmentsArray.length);
            return  resultingArray;
        }
    }

    public ArrayList<String> getAllOutputs(ArrayList<String> rawInputArray){
        ArrayList<String> outputs = new ArrayList<String>();
        for (String line : rawInputArray){
            String[] allSegments = getAllSegmentsFromLine(line);
            String[] lineOutputs = getSegmentsFromLine(allSegments,"output");
            for ( String output : lineOutputs) {
                outputs.add(output);
            }
        }
        return  outputs;
    }

    public ArrayList<String> getAllInputs(ArrayList<String> rawInputArray){
        ArrayList<String> inputs= new ArrayList<String>();
        for (String line : rawInputArray){
            String[] allSegments = getAllSegmentsFromLine(line);
            String[] lineInputs = getSegmentsFromLine(allSegments,"input");
            for ( String input : lineInputs) {
                inputs.add(input);
            }
        }
        return  inputs;
    }

    public int decodeStringToDigit(String observedSegments) {
        int digit = -1;
        if (observedSegments.length() == 2){
            digit = 1;
        }
        if (observedSegments.length() == 3){
            digit = 7;
        }
        if (observedSegments.length() == 4){
            digit = 4;
        }
        if (observedSegments.length() == 7){
            digit = 8;
        }
        return digit;
    }

    public int countDigitsWithUniqueNumberOfSegments(ArrayList<String> array) {
        int totalCount = 0;
        for (String record: array){
            int digit =  decodeStringToDigit(record);
            if (digit != -1){
                totalCount += 1;
            }
        }
        return totalCount;

    }
}
