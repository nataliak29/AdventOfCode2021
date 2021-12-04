import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public int [][] createMatrixFromResource(String resource) throws IOException {
        int fileLength = fileLength(resource);
        int lineLength = fileFirstLineLength(resource);
        int[][] bitsArray = new int[fileLength][lineLength];
        //ArrayList<ArrayList<Integer>> bitsArray2 = new ArrayList<ArrayList<Integer>>();
        Scanner inFile = new Scanner(new FileReader(resource));
        for (int j = 0; j < fileLength; j++) {
            String line = inFile.nextLine();
            String[] data = line.split("");
            for (int i = 0; i < lineLength; i++) {
                bitsArray[j][i] = Integer.parseInt(data[i]);
                //ArrayList<Integer> ls= new ArrayList<Integer>(Arrays.asList(2, 0));
                //bitsArray2.set() = Integer.parseInt(data[i]);
                //System.out.println(" i "+i+ " j "+ j + "bitsArray[i][j]"+ bitsArray[i][j]);
            }
        }
        inFile.close();
        return bitsArray;
    }

    public int [][] splitColumnsIntoArrayFromResource(String resource) throws IOException {
        int fileLength = fileLength(resource);
        int lineLength = fileFirstLineLength(resource);
        int[][] bitsArray = new int[lineLength][fileLength];
        //ArrayList<ArrayList<Integer>> bitsArray2 = new ArrayList<ArrayList<Integer>>();
        Scanner inFile = new Scanner(new FileReader(resource));
        for ( int j =0 ; j<fileLength;j++) {
            String line = inFile.nextLine();
            String[] data = line.split("");
            for (int i = 0; i < lineLength; i++) {
                bitsArray[i][j] = Integer.parseInt(data[i]);
                //ArrayList<Integer> ls= new ArrayList<Integer>(Arrays.asList(2, 0));
                //bitsArray2.set() = Integer.parseInt(data[i]);
                //System.out.println(" i "+i+ " j "+ j + "bitsArray[i][j]"+ bitsArray[i][j]);
            }
        }
            inFile.close();
        return bitsArray;
    }

    public int[] getListFromMatrix(int [][] matrix, int index){
        int matrixSize = matrix.length;
        int [] myList = new int[matrixSize];
        for (int i = 0; i < matrix.length; i++){
                myList[i] = matrix[i][index];
        }
        return myList;

    }

    public int getMostFrequentBit(int[] list){
        int listLength = list.length;
        int totalSum = 0;
        int mostFrequentBit = 0;
        for (int i: list){
            totalSum += i;
        }
        if (totalSum > listLength / 2){
            mostFrequentBit = 1;
        }
        else {
            mostFrequentBit = 0;
        }
        return mostFrequentBit;
    }

    public String[] getEpsilonAndGamma (int [][] bitsArray) {
        StringBuffer epsilon  = new StringBuffer();
        StringBuffer gamma = new StringBuffer();

        for (int i =0; i<bitsArray.length; i++){
            int mostFrequentBit = getMostFrequentBit(bitsArray[i]);
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

    @Override
    public String partOneAnswer(String resource) throws IOException {
        int[][] array = splitColumnsIntoArrayFromResource(resource);
        String[] epsilonAndGamma = getEpsilonAndGamma(array);
        int answer = getPowerConsumption(epsilonAndGamma);
        return String.valueOf(answer);
    }



    public int [][] oxygenGeneratorRating (String resource) throws IOException {
        //ArrayList<String> stringArray = getResourceAsStringArray(resource);
        int [][] bitsArray = createMatrixFromResource(resource);
        //String mostFrequentBits = getEpsilonAndGamma(bitsArray)[0];
        //System.out.println("mostFrequentBits "+mostFrequentBits);
        for (int i =0;i < 20; i ++){
            //int [] myArray = bitsArray[][i];
            int [] columnBeingProcessed = getListFromMatrix(bitsArray,i);
            int mostFrequentBit = getMostFrequentBit(columnBeingProcessed);
            int arrayLength = bitsArray.length;
            //char bit = mostFrequentBits.charAt(i);
            System.out.println("arrayLength "+arrayLength);
            System.out.println("mostFrequentBit "+mostFrequentBit);
            //int i = list.size() - 1; i >= 0 ; i--
            for ( int j =arrayLength-1; j >=0; j--){
                System.out.println("columnBeingProcessed[j]  "+columnBeingProcessed[j]);
                if (columnBeingProcessed[j] != mostFrequentBit && arrayLength!=1 ){
                    System.out.println("bitsArray[i]  "+bitsArray[i]);
                    //bitsArray[i] = null;
                    //stringArray.remove(j);
                }

            }
        }
        return  bitsArray;

    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> array = getResourceAsStringArray(resource);
        int answer = 0;
        return String.valueOf(answer);
    }


}
