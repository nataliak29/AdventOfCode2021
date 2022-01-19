import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Day16 extends Day{
    static String RESOURCE = "src/main/resources/day16_input.txt";

    public Day16() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println("Part1: " + new Day16().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day16().partTwoAnswer(RESOURCE));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        String input = convertToBinary(inputArray.get(0));
        Packet packet = new Packet(input);
        int answer = packet.getVersionSumInPacket(input,0);
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        String input = convertToBinary(inputArray.get(0));
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        for (long l: literalValues){
          // if (l == 0) System.out.println(l);
        }

        for (int l: stackOfOperationTypeIDs){
            //System.out.println(l);
        }

        //Long answer = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        //return String.valueOf(answer);
        return "";
    }


    public String convertToBinary(String hexadecimalInput) {
        StringBuffer binaryInput = new StringBuffer();
        HashMap<Character, String> hashMap = getHexToBinaryLookup();
        for (int i = 0; i < hexadecimalInput.length(); i++) {
            char ch = hexadecimalInput.charAt(i);
            binaryInput.append(hashMap.get(ch));
        }
        return binaryInput.toString();
    }


    private HashMap<Character, String> getHexToBinaryLookup(){
        HashMap<Character, String> hashMap
                = new HashMap<Character, String>();
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");
        return hashMap;
    }
}
