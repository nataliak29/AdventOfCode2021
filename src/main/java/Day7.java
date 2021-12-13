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
        int answer =  -1;
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

    public int getFuelUsed(int[] integerList,int median) {
        int totalFuel = 0;
        for (int element : integerList){
            totalFuel += Math.abs(element - median);
        }
        return totalFuel;
    }



}
