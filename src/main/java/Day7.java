import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day7 extends Day{

    static String RESOURCE = "src/main/resources/day7_input.txt";

    public Day7() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day7().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day7().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        int[] integerList = getIntegersList(inputArray);
        int median =  calculateMedian(integerList);
        int answer = getFuelUsed(integerList,median);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        int[] integerList = getIntegersList(inputArray);
        int mean =  calculateMean(integerList);
        int answer = findMinimalFuelSpend(integerList, mean);
        return String.valueOf(answer);
    }

    public int calculateMedian(int[] integerList) {
        Arrays.sort(integerList);
        int listLength = integerList.length;
        int median = 0;
        if ( listLength % 2 == 0){
            median = Math.round((float) ( integerList[listLength/2] + integerList[listLength/2 - 1] ) / 2);
        }
        else {
            median = integerList [Math.round(listLength/2)];
        }
        return median;
    }

    public int getFuelUsed(int[] integerList,int threshold) {
        int totalFuel = 0;
        for (int element : integerList){
            totalFuel += Math.abs(element - threshold);
        }
        return totalFuel;
    }

    public int calculateMean(int[] integerList) {
        int listLength = integerList.length;
        int totalSum = 0;
        for (int element : integerList){
            totalSum += element;
        }
        return Math.round( (float) totalSum/listLength);
    }

    /** https://en.wikipedia.org/wiki/Triangular_number **/
    public int triangleNumber(int n){
        return n * (n + 1) / 2;
    }

    public int getFuelUsedNonLinear(int[] integerList,int threshold) {
        int totalFuel = 0;
        for (int element : integerList){
            int numberOfSteps = Math.abs(element - threshold);
            totalFuel += triangleNumber(numberOfSteps);
        }
        return totalFuel;
    }

    public int findMinimalFuelSpend(int[] integerList,int mean) {
        int fuelSpendMean = getFuelUsedNonLinear(integerList, mean);
        int minimalFuelSpend = fuelSpendMean;
        for (int i = mean - 3; i < mean + 3; i ++){
            int fuelSpend = getFuelUsedNonLinear(integerList, i);
            if ( fuelSpend < minimalFuelSpend) {
                minimalFuelSpend = fuelSpend;
            }
        }
        return minimalFuelSpend;
    }

}
