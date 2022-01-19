import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Packet {
    String binaryInput;
    Integer version;
    Integer typeID;
    Integer endOfPacket;
    String binaryString;
    Integer packetVersionSum;
    Integer packetOperationResult;
    Stack<Long> literalValues;
    Stack<Integer> operationTypeStack;

    public Packet(String binaryInput){
        this.binaryInput = binaryInput;
        version = getVersion();
        typeID = getTypeID();
        binaryString = binaryInput.substring(6,binaryInput.length());
    }

    public Integer getVersion(){
        String binaryString = binaryInput.substring(0,3);
        return Integer.parseInt(binaryString,2);
    }

    public Integer getTypeID(){
        String binaryString = binaryInput.substring(3,6);
        return Integer.parseInt(binaryString,2);
    }

    public Integer getLengthTypeID(){
        return Integer.parseInt(binaryString.substring(0,1));
    }


    public Integer getLengthOfSubPackets(){
        if (getLengthTypeID() == 0 & getTypeID() != 4){
            return Integer.parseInt(binaryString.substring(1,16),2);
        }
        else {
            return null;
        }
    }

    public Integer getNumberOfSubPackets (){
        if (getLengthTypeID() == 1 & getTypeID() != 4){
            return Integer.parseInt(binaryString.substring(1,12),2);
        }
        else {
            return null;
        }
    }

    public String getSubPacketsBinaryString() {
        if (getLengthOfSubPackets() != null) {
            return binaryString.substring(16, binaryString.length());
        }
        if (getNumberOfSubPackets() != null) {
            return binaryString.substring(12, binaryString.length());
        } else {
            for (int i = 0; i < binaryString.length(); i += 5) {
                ;
                if (binaryString.substring(i, i + 1).equals("0")) {
                    endOfPacket = i + 5;
                    break;
                }
            }
            String subPacket = binaryString.substring(endOfPacket, binaryString.length());
            if (!subPacket.contains("1")){
                return null;
            }
            else {
                return subPacket;
            }

        }
    }

    public Long getLiteralValueType4(){
        StringBuffer literalValueString = new StringBuffer();
        for (int i = 0; i < binaryString.length(); i+=5){
            //System.out.println(binaryString.substring(i+1,i+5));
            literalValueString.append(binaryString.substring(i+1,i+5));
            //System.out.println(binaryString.substring(i+1,i+5));
            if (binaryString.substring(i,i+1).equals("0")){
                endOfPacket = i+5;
                break;
            }
        }
        Long literalValue = Long.parseLong(literalValueString.toString(), 2);
        return  literalValue;
    }


    public Long performOperation(Integer typeID, List<Long> values){
        Long result = Long.valueOf(0);
        if (typeID == 0){
            result = Long.valueOf(0);
            for (long value:values){
                result = result + value;
            }
        }
        if (typeID == 1){
            result = Long.valueOf(1);
            for (long value:values){
                result = result * value;
            }
        }
        if (typeID == 2){
            result = Long.MAX_VALUE;
            for (long value:values){
                if (value < result){
                    result = value;
                }
            }
        }
        if (typeID == 3){
            result = Long.valueOf(0);
            for (long value:values){
                if (value > result){
                    result = value;
                }
            }
        }

        if (typeID == 5){
            result = Long.valueOf(0);
            System.out.println("typeID 5 values");
            for (Long l: values){
                System.out.println(l);
            }
            if (values.get(0) < values.get(1)){
                result = Long.valueOf(1);
            }
        }

        if (typeID == 6){
            result = Long.valueOf(0);
            if (values.get(0) > values.get(1)){
                result = Long.valueOf(1);
            }
        }

        if (typeID == 7){
            result = Long.valueOf(0);
            if (values.get(0).equals(values.get(1))){
                result = Long.valueOf(1);
            }
        }

        return result;

    }


    public Integer getVersionSumInPacket(String binaryString, int versionSum){
        if (binaryString == null){
            return versionSum;
        }
        Packet packet  = new Packet(binaryString);
        String subPacketsString = packet.getSubPacketsBinaryString();
        return getVersionSumInPacket(subPacketsString,  versionSum + packet.getVersion());
    }


    public Integer parseOperationNew(String binaryString,
                                  Stack<Integer> operationTypeStack,
                                  Stack<Long> literalValues){
        Packet packet  = new Packet(binaryString);
        typeID = packet.getTypeID();
        if (typeID == 4){
            //System.out.println(binaryString.length());
            Long value = packet.getLiteralValueType4();
            //System.out.println(value);
            literalValues.push(value);
            operationTypeStack.push(typeID);
        }
        else {
            operationTypeStack.push(typeID);
        }
        String subPacketsString = packet.getSubPacketsBinaryString();
        if (typeID ==5){
            System.out.println("start "+subPacketsString);
        }

        if (subPacketsString != null){
            parseOperation(subPacketsString,operationTypeStack,literalValues);
        }

        else {
            return 0;
        }
        this.literalValues = literalValues;
        this.operationTypeStack =operationTypeStack;
        return 1;

    }


    public Integer parseOperation(String binaryString,
                                  Stack<Integer> operationTypeStack,
                                  Stack<Long> literalValues){
        Packet packet  = new Packet(binaryString);
        typeID = packet.getTypeID();
        if (typeID == 4){
            //System.out.println(binaryString.length());
            Long value = packet.getLiteralValueType4();
            //System.out.println(value);
            literalValues.push(value);
            operationTypeStack.push(typeID);
        }
        else {
            operationTypeStack.push(typeID);
        }
        String subPacketsString = packet.getSubPacketsBinaryString();
        if (typeID ==5){
            System.out.println("start "+subPacketsString);
        }

        if (subPacketsString != null){
            parseOperation(subPacketsString,operationTypeStack,literalValues);
        }

        else {
            return 0;
        }
        this.literalValues = literalValues;
        this.operationTypeStack =operationTypeStack;
        return 1;

    }

    public Long runOperations(Stack<Integer> operationTypeStack,
                              Stack<Long>  literalValues){
        Integer numberOfLiteralValues = 0;
        Long result = Long.valueOf(0);
        while (!operationTypeStack.empty()) {

            Integer typeID = operationTypeStack.pop();
            System.out.println("typeID "+typeID + " size "+operationTypeStack.size());
            System.out.println("size of literavl value "+literalValues.size());
            if (typeID == 4) {
                numberOfLiteralValues += 1;
            } else {
                if (operationTypeStack.empty())
                {
                    numberOfLiteralValues = literalValues.size();
                }
                List<Long> subListOfValues = new ArrayList<Long>();
                for (int i = 0; i < numberOfLiteralValues; i++){
                    Long element = literalValues.pop();
                    subListOfValues.add(element);
                }
                /**
                for (Integer i : subListOfValues) {
                    System.out.println(i);
                }
                **/
                //System.out.println(subListOfValues.size());
                if (subListOfValues.size() == 1){
                    result = subListOfValues.get(0);
                }
                else {
                    result = performOperation(typeID, subListOfValues);
                }
                literalValues.add(0,result);
                numberOfLiteralValues = 0;
                //operationTypeStack.push(4);
                //System.out.println("result "+result);
            }
        }
    return result;
    }

    public Stack<Integer> getOperationTypeStack() {
        return operationTypeStack;
    }

    public Stack<Long> getLiteralValues() {
        return literalValues;
    }
}
