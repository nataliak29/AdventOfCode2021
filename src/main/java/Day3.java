import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 extends Day {
    static String RESOURCE = "src/main/resources/day3_input.txt";

    public Day3() {
        super();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Part1: " + new Day3().partOneAnswer(RESOURCE));
        System.out.println("Part2: "+ new Day3().partTwoAnswer(RESOURCE));
    }
    public ArrayList<ArrayList<Integer>> createMatrixFromResource(String resource) throws IOException {
        int fileLength = fileLength(resource);
        int lineLength = fileFirstLineLength(resource);
        ArrayList<ArrayList<Integer>> bitsMatrix = new ArrayList<ArrayList<Integer>>();
        Scanner inFile = new Scanner(new FileReader(resource));
        for (int row = 0; row < fileLength; row++) {
            String line = inFile.nextLine();
            String[] data = line.split("");
            ArrayList<Integer> thisRow = new ArrayList<Integer>();
            for (int column = 0; column < lineLength; column++) {
                thisRow.add(Integer.parseInt(data[column]));
            }
            bitsMatrix.add(thisRow);
        }
        inFile.close();
        return bitsMatrix;
    }




    public ArrayList<Integer> getColumnFromMatrix(ArrayList<ArrayList<Integer>> matrix, int index){
        int matrixSize = matrix.size();
         ArrayList<Integer> requestedColumn = new ArrayList<>();
        for (int i = 0; i < matrixSize; i++){
            requestedColumn.add(matrix.get(i).get(index));
        }
        return requestedColumn;

    }

    public int getMostFrequentBitFromArray(ArrayList<Integer> array){
        int arraySize = array.size();
        int totalSum = 0;
        int mostFrequentBit = 0;
        for (int i: array){
            totalSum += i;
        }
        if (totalSum >= (float) arraySize / 2 ){
            mostFrequentBit = 1;
        }
        else {
            mostFrequentBit = 0;
        }
        return mostFrequentBit;
    }

    public String[] getEpsilonAndGammaArray(ArrayList<ArrayList<Integer>> matrix) {
        StringBuffer epsilon  = new StringBuffer();
        StringBuffer gamma = new StringBuffer();

        for (int i =0; i<matrix.get(0).size(); i++){
            ArrayList<Integer> column = getColumnFromMatrix(matrix, i);
            int mostFrequentBit = getMostFrequentBitFromArray(column);
            int leastFrequentBit = 0;
            if (mostFrequentBit == 0) {
                leastFrequentBit = 1;
            }
            else {
                leastFrequentBit = 0;
            }
            epsilon.append(String.valueOf(mostFrequentBit));
            gamma.append(String.valueOf(leastFrequentBit));

        }
        String[] epsilonAndGammaList = new String[]{String.valueOf(epsilon),String.valueOf(gamma)};
        return epsilonAndGammaList;

    }


    public int getPowerConsumption(String[] epsilonAndGammaList) {
        int powerConsumption = Integer.parseInt(epsilonAndGammaList[0],2)
                * Integer.parseInt(epsilonAndGammaList[1],2);
        return powerConsumption;
    }

    public int getLifeSupportrating(String oxygenGeneratorRating, String CO2ScrubberRating) {
        int powerConsumption = Integer.parseInt(oxygenGeneratorRating,2)
                * Integer.parseInt(CO2ScrubberRating,2);
        return powerConsumption;
    }

    @Override
    public String partOneAnswer(String resource) throws IOException {
        ArrayList<ArrayList<Integer>> array = createMatrixFromResource(resource);
        String[] epsilonAndGamma = getEpsilonAndGammaArray(array);
        int answer = getPowerConsumption(epsilonAndGamma);
        return String.valueOf(answer);
    }

    public String getTargetRating(ArrayList<ArrayList<Integer>> matrix, String frequency){
        StringBuffer targetRating  = new StringBuffer();
        ArrayList<ArrayList<Integer>> matrixCopy = new ArrayList<ArrayList<Integer>>();
        matrixCopy.addAll(matrix);

        for (int i =0; i<matrixCopy.get(0).size(); i++){
            ArrayList<Integer> column = getColumnFromMatrix(matrixCopy, i);
            int mostFrequentBit = getMostFrequentBitFromArray(column);
            int leastFrequentBit = 0;
            if (mostFrequentBit == 0) {
                leastFrequentBit = 1;
            }
            else {
                leastFrequentBit = 0;
            }
            int targetBit = 0;
            if (frequency == "mostFrequent") {
                targetBit = mostFrequentBit;
            }
            else {
                targetBit = leastFrequentBit;
            }

            targetRating.append(String.valueOf(targetBit));
            if(matrixCopy.size()==1){
                StringBuffer lastElement = new StringBuffer();
                for (int k=0; k<matrixCopy.get(0).size(); k++){
                    lastElement.append(String.valueOf(matrixCopy.get(0).get(k)));

                }
                return String.valueOf(lastElement);
            }
            for (int j =matrixCopy.size()-1; j>=0; j--) {
                if( matrixCopy.get(j).get(i) != targetBit){
                    matrixCopy.remove(j);
                }

            }

        }
        return String.valueOf(targetRating);
    }


    @Override
    public String partTwoAnswer(String resource) throws IOException {
        ArrayList<ArrayList<Integer>> matrix = createMatrixFromResource(resource);
        String oxygenGeneratorRating = getTargetRating(matrix, "mostFrequent");
        String CO2ScrubberRating = getTargetRating(matrix, "leastFrequent");
        int answer = getLifeSupportrating(oxygenGeneratorRating, CO2ScrubberRating);
        return String.valueOf(answer);
    }


}
