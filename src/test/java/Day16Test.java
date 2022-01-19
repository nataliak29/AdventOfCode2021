import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Day16Test extends TestCase {
    Day16 day16 = new Day16();
    private static final String RESOURCE = "src/test/resources/day16_test.txt";

    public void testPartOneAnswer() throws FileNotFoundException {
        String actualAnswer = day16.partOneAnswer(RESOURCE);
        String expectedAnswer = "31";
        assertEquals(expectedAnswer, actualAnswer);
    }

    public void testGetBinary() {
        String input ="D2FE28";
        String actualString =day16.convertToBinary(input);
        String expectedString = "110100101111111000101000";
        assertEquals(expectedString,actualString);

    }

    public void testGetVersion() {
        String input = "110100101111111000101000";
        int actualVersion = new Packet(input).getVersion();
        int expectedVersion = 6;
        assertEquals(expectedVersion,actualVersion);
    }


    public void testGetTypeID() {
        String input = "110100101111111000101000";
        int actualTypeID = new Packet(input).getTypeID();
        int expectedTypeID = 4;
        assertEquals(expectedTypeID,actualTypeID);
    }

    public void testGetLiteralValue() {
        String input = "110100101111111000101000";
        Long actualTypeID = new Packet(input).getLiteralValueType4();
        Long expectedTypeID = Long.valueOf(2021);
        assertEquals(expectedTypeID,actualTypeID);
    }

    public void testGetLengthTypeID() {
        String input = "00111000000000000110111101000101001010010001001000000000";
        int actualLengthTypeID= new Packet(input).getLengthTypeID();
        int expectedLengthTypeID = 0;
        assertEquals(expectedLengthTypeID,actualLengthTypeID);
    }

    public void testGetLengthOfSubPackets() {
        String input = "00111000000000000110111101000101001010010001001000000000";
        int actualLengthOfSubPackets= new Packet(input).getLengthOfSubPackets();
        int expectedLengthOfSubPackets = 27;
        assertEquals(expectedLengthOfSubPackets,actualLengthOfSubPackets);
    }

    public void testGetNumberOfSubPackets() {
        String input = "11101110000000001101010000001100100000100011000001100000";
        int actualNumberOfSubPackets = new Packet(input).getNumberOfSubPackets();
        int expectedNumberOfSubPackets = 3;
        System.out.println(new Packet(input).endOfPacket);
        assertEquals(expectedNumberOfSubPackets ,actualNumberOfSubPackets );
    }

    public void testGetSubPacketsBinaryStringTypeZero() {
        String input = "00111000000000000110111101000101001010010001001000000000";
        Packet packet = new Packet(input);
        String expectedString = "1101000101001010010001001000000000";
        String actualString = packet.getSubPacketsBinaryString();
        assertEquals(expectedString,actualString );
    }

    public void testGetSubPacketsBinaryStringTypeOne() {
        String input = "11101110000000001101010000001100100000100011000001100000";
        Packet packet = new Packet(input);
        String expectedString = "01010000001100100000100011000001100000";
        String actualString = packet.getSubPacketsBinaryString();
        assertEquals(expectedString,actualString );
    }

    public void testGetSubPacketsBinaryStringLiteral() {
        String input = "110100101111111000101000";
        Packet packet = new Packet(input);
        String expectedString = null;
        String actualString = packet.getSubPacketsBinaryString();
        assertEquals(expectedString,actualString );
    }


    public void testGetVersionSum() {
        String inputHex ="620080001611562C8802118E34";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        int actualVersionSum = packet.getVersionSumInPacket(input,0);
        assertEquals(12,actualVersionSum);
    }

    public void testGetVersionSum2() {
        String inputHex ="8A004A801A8002F478";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        int actualVersionSum = packet.getVersionSumInPacket(input,0);
        assertEquals(16,actualVersionSum);
    }

    public void testGetVersionSum3() {
        String inputHex ="C0015000016115A2E0802F182340";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        int actualVersionSum = packet.getVersionSumInPacket(input,0);
        assertEquals(23,actualVersionSum);
    }

    public void testGetVersionSum4() {
        String inputHex ="A0016C880162017C3686B18A3D4780";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        int actualVersionSum = packet.getVersionSumInPacket(input,0);
        assertEquals(31,actualVersionSum);
    }

    public void testParseOperationType0() {
        String inputHex ="C200B40A82";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        Long actualResult = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        assertEquals(Long.valueOf(3), actualResult);
    }


    public void testParseOperationType1() {
        String inputHex ="04005AC33890";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        Long actualResult = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        assertEquals(Long.valueOf(54), actualResult);
    }

    public void testParseOperationType2() {
        String inputHex ="880086C3E88112";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        Long actualResult = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        assertEquals(Long.valueOf(7), actualResult);
    }

    public void testParseOperationType3() {
        String inputHex ="CE00C43D881120";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        Long actualResult = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        assertEquals(Long.valueOf(9), actualResult);
    }

    public void testParseOperationType5() {
        String inputHex ="D8005AC2A8F0";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        Long actualResult = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        assertEquals(Long.valueOf(1), actualResult);
    }

    public void testParseOperationType6() {
        String inputHex ="F600BC2D8F";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        Long actualResult = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        assertEquals(Long.valueOf(0), actualResult);
    }

    public void testParseOperationType7() {
        String inputHex ="9C005AC2F8F0";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Stack<Integer> stackOfOperationTypeIDs = new Stack<>();
        Stack<Long> literalValues = new Stack<>();
        packet.parseOperation(input, stackOfOperationTypeIDs, literalValues);
        stackOfOperationTypeIDs = packet.getOperationTypeStack();
        literalValues = packet.getLiteralValues();
        Long actualResult = packet.runOperations(stackOfOperationTypeIDs, literalValues);
        assertEquals(Long.valueOf(0), actualResult);
    }


    public void testParseOperationVersion2() {
        String input = "10011100000000010100000100001000000000100101000000110010000011110001100000000010000100000100101000001000";
        Packet packet = new Packet(input);
        Stack<Integer> stack = new Stack<>();
        Stack<Long> values = new Stack<>();
        packet.parseOperation(input, stack, values);
        stack = packet.getOperationTypeStack();
        values = packet.getLiteralValues();
        Stack<Integer> stackExpected = new Stack<>();
        stackExpected.push(7);
        stackExpected.push(0);
        stackExpected.push(4);
        stackExpected.push(4);
        stackExpected.push(1);
        stackExpected.push(4);
        stackExpected.push(4);
        Stack<Long> valuesExpected = new Stack<>();
        valuesExpected.push(Long.valueOf(1));
        valuesExpected.push(Long.valueOf(3));
        valuesExpected.push(Long.valueOf(2));
        valuesExpected.push(Long.valueOf(2));
        assertEquals(stackExpected, stack);
        assertEquals(valuesExpected, values);

    }


    public void testParseOperationV2Type0() {
        Stack<Integer> stackExpected = new Stack<>();
        stackExpected.push(0);
        stackExpected.push(4);
        stackExpected.push(4);
        Stack<Long> valuesExpected = new Stack<>();
        valuesExpected.push(Long.valueOf(1));
        valuesExpected.push(Long.valueOf(2));
        String inputHex ="C200B40A82";
        String input = day16.convertToBinary(inputHex);
        Packet packet = new Packet(input);
        Long actualResult = packet.runOperations(stackExpected,valuesExpected);
        assertEquals(Long.valueOf(3), actualResult);

    }
    public void testParseOperationType0Combo() {
        Stack<Integer> stackExpected = new Stack<>();
        stackExpected.push(7);
        stackExpected.push(0);
        stackExpected.push(4);
        stackExpected.push(4);
        stackExpected.push(1);
        stackExpected.push(4);
        stackExpected.push(4);
        Stack<Long> valuesExpected = new Stack<>();
        valuesExpected.push(Long.valueOf(1));
        valuesExpected.push(Long.valueOf(3));
        valuesExpected.push(Long.valueOf(2));
        valuesExpected.push(Long.valueOf(2));
        String input = "10011100000000010100000100001000000000100101000000110010000011110001100000000010000100000100101000001000";
        Packet packet = new Packet(input);
        Long actualResult = packet.runOperations(stackExpected,valuesExpected);
        assertEquals(Long.valueOf(1), actualResult);


        //Integer actualResult = packet.packetOperationResult;
       // System.out.println(actualResult);
        //assertEquals(java.util.Optional.of(1), java.util.Optional.of(answer));

    }


}
