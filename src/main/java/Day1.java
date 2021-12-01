import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day1 extends Day {
    static String RESOURCE = "src/main/resources/day1_input.txt";

    public Day1() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day1().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day1().partTwoAnswer(RESOURCE));
    }

    public int measurementIncrease(ArrayList<Integer> myArray) {
        int elementsWithMeasurementIncrease = 0;
        for (int i = 1; i < myArray.size(); i++) {
            int thisElement = myArray.get(i);
            int previousElement = myArray.get(i-1);
            if (thisElement > previousElement) {
                elementsWithMeasurementIncrease += 1;
            }
        }
        return elementsWithMeasurementIncrease;
    }


    public int measurementIncreaseSlidingWindow(ArrayList<Integer> myArray) {
        ArrayList<Integer> slidingWindowArray = new ArrayList<Integer>();
        for (int i = 0; i < myArray.size()-2; i+=1) {
            int slidingWindowSum = myArray.get(i) + myArray.get(i+1) + myArray.get(i+2);
            slidingWindowArray.add(slidingWindowSum);
            }
        return measurementIncrease(slidingWindowArray);
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<Integer> array = convertStringArrayToIntegerArray(getResourceAsStringArray(resource));
        int answer = measurementIncrease(array);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<Integer> array = convertStringArrayToIntegerArray(getResourceAsStringArray(resource));
        int answer = measurementIncreaseSlidingWindow(array);
        return String.valueOf(answer);
    }


}
