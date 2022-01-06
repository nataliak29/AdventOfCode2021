import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 extends Day  {
    static String RESOURCE = "src/main/resources/day14_input.txt";

    public Day14() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day14().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day14().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        HashMap<String,String> instructions = getInstructionsHashMap(inputArray);
        int numberOfSteps = 10;
        String polymer = inputArray.get(0);
        for (int i = 0; i < numberOfSteps; i++){
            polymer = processStep(polymer, instructions);
        }
        HashMap<Character,Long> frequencies = getFrequencyOfElements(polymer);
        long answer = differenceBetweenFrequencies(frequencies);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        HashMap<String,String> instructions = getInstructionsHashMap(inputArray);
        int numberOfSteps = 40;
        String polymer = inputArray.get(0);
        char lastLetterInPolymer = polymer.charAt(polymer.length()-1);
        HashMap<String,Long> polymerPairs = initialSetUp(polymer);
        for (int i = 0; i < numberOfSteps; i++){
            System.out.println("Step "+ i);
            polymerPairs = processStepEfficient( polymerPairs, instructions);
        }

        HashMap<Character,Long> frequencies = getFrequencyOfElementsEfficient(polymerPairs);
        frequencies.put(lastLetterInPolymer,frequencies.get(lastLetterInPolymer) + 1);
        long answer = differenceBetweenFrequencies(frequencies);
        return String.valueOf(answer);
    }

    public String processStep(String polymer, HashMap<String,String> instructions){
        StringBuffer stringBuffer  = new StringBuffer();
        for (int i = 0; i < polymer.length() - 1; i++){
            String firstLetter = polymer.substring(i, i+1);
            String pair = polymer.substring(i, i+2);
            String toInsert = instructions.get(pair);
            stringBuffer.append(firstLetter);
            stringBuffer.append(toInsert);
        }
        String lastLetter = polymer.substring(polymer.length() -1, polymer.length());
        stringBuffer.append(lastLetter);
        return stringBuffer.toString();
    }

    public HashMap<String,Long> initialSetUp(String polymer) {
        HashMap<String, Long> polymerPairs = new HashMap<>();
        for (int i = 0; i < polymer.length() - 1; i++) {
            String pair = polymer.substring(i, i + 2);
            polymerPairs = addToMap(pair, Long.valueOf(1), polymerPairs);
        }
        return polymerPairs;
    }

    public HashMap<String,Long> addToMap(String toBeAdded, Long value, HashMap<String,Long> map){
        if (map.containsKey(toBeAdded)){
            long newValue = map.get(toBeAdded) + value;
            map.put(toBeAdded, newValue);
        }
        else {
            map.put(toBeAdded, value);
        }
        return map;
    }

    public HashMap<String,Long> processStepEfficient(HashMap<String,Long> polymerPairs, HashMap<String,String> instructions){
        HashMap<String,Long> polymerPairsToAdd = new HashMap<>();
        for (String pair : polymerPairs.keySet()){
            StringBuffer firstPair = new StringBuffer();
            StringBuffer secondPair = new StringBuffer();
            String firstLetter = pair.substring(0,1);
            String secondLetter = pair.substring(1,2);
            String letterToInsert = instructions.get(pair);
            firstPair.append(firstLetter);
            firstPair.append(letterToInsert);
            secondPair.append(letterToInsert);
            secondPair.append(secondLetter);
            Long value = polymerPairs.get(pair);
            polymerPairsToAdd = addToMap(firstPair.toString(), value, polymerPairsToAdd);
            polymerPairsToAdd = addToMap(secondPair.toString(), value, polymerPairsToAdd);
        }

        return polymerPairsToAdd;
    }

    public long differenceBetweenFrequencies(HashMap<Character,Long> frequencies){
        long minFreq = 0;
        long maxFreq = 0;
        for (Character key : frequencies.keySet()) {
            long value = frequencies.get(key);
            if (minFreq == 0 && maxFreq == 0){
                minFreq = value;
                maxFreq = value;
            }
            if (value > maxFreq) {
                maxFreq = value;
            }
            if (value < minFreq){
                minFreq = value;
            }
        }
        return maxFreq - minFreq;
    }

    public HashMap<Character,Long> getFrequencyOfElements(String polymer) {
        HashMap<Character,Long> frequencies = new HashMap<Character, Long>();
        for (int i = 0; i < polymer.length(); i++) {
            char c = polymer.charAt(i);
            Long freq = frequencies.get(c);
            if (freq != null) {
                frequencies.put(c, new Long(freq + 1));
            }
            else {
                frequencies.put(c, Long.valueOf(1));
            }
        }
        return frequencies;
    }

    public HashMap<Character,Long> getFrequencyOfElementsEfficient(HashMap<String,Long> polymerPairs) {
        HashMap<Character,Long> frequencies = new HashMap<Character, Long>();
        for (String pair: polymerPairs.keySet()) {
            char c = pair.charAt(0);
            Long freq = frequencies.get(c);
            if (freq != null) {
                frequencies.put(c, new Long(freq + polymerPairs.get(pair)));
            }
            else {
                frequencies.put(c, Long.valueOf(polymerPairs.get(pair)));
            }
        }
        return frequencies;
    }

    public HashMap<String,String> getInstructionsHashMap(ArrayList<String> inputArray) {
        HashMap <String,String> instructionsHashMap = new HashMap<>();
        for (String line : inputArray){
            if (line.contains("->")) {
                String pair = line.substring(0, 2);
                String replacement = line.substring(6);
                instructionsHashMap.put(pair,replacement);
            }
        }
        return instructionsHashMap;
    }
}
