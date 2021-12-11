
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6 extends Day{

    static String RESOURCE = "src/main/resources/day6_input.txt";

    public Day6() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part1: " + new Day6().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day6().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Long[] lanternfishArray = getLanternfishArray(inputArray);
        int answer =  runSimuation (lanternfishArray,80);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Long[] lanternfishArray = getLanternfishArray(inputArray);
        int answer =  runSimuation (lanternfishArray,256);
        return String.valueOf(answer);
    }

    public Long[] getLanternfishArray(ArrayList<String> inputArray){
        ArrayList<String> finalArray  = new ArrayList<>(Arrays.asList(inputArray.get(0).split(",")));
        Long[] lanternfishList = new Long[finalArray.size()];
        for (Integer i = 0; i < finalArray.size(); i ++) {
            lanternfishList[i] = Long.valueOf(finalArray.get(i));
        }
        return  lanternfishList;
    }

    public  Long[] processOneDay (Long[] lanternfishArray){
        int originalArrayLength = lanternfishArray.length;
        int numberOfNewFish = 0;
        for (Integer i = 0; i < lanternfishArray.length; i ++) {
            if (lanternfishArray[i] > 0 ){
                lanternfishArray[i] -= 1;
            }
            else {
            if (lanternfishArray[i] == 0){
                lanternfishArray[i] = Long.valueOf(6);
                numberOfNewFish += 1;
            }}
        }
        lanternfishArray  = Arrays.copyOf(lanternfishArray, originalArrayLength + numberOfNewFish);
        for (Integer j = originalArrayLength; j <lanternfishArray.length; j++) {
            lanternfishArray[j] = Long.valueOf(8);
        }
        return lanternfishArray;

    }

    public int runSimuation (Long[] lanternfishArray,int numberOfDays) {
        if (numberOfDays == 0 ){
            return lanternfishArray.length;
        }
        int dayCount = 0;
        while (dayCount < numberOfDays) {
            System.out.println("Day " + dayCount);
            lanternfishArray = processOneDay(lanternfishArray);
            dayCount += 1;
            //System.out.println("Length of array " + lanternfishArray.length);
        }
        //for (long f: lanternfishArray){
            //System.out.println(f);
        //}
        return lanternfishArray.length;
    }


}
