import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Packet {
    String binaryInput;
    Integer version;
    Integer typeID;
    Integer endOfPacket;
    String binaryString;
    Integer packetVersionSum;
    Integer packetOperationResult;

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

    public Integer getLiteralValueType4(){
        StringBuffer literalValueString = new StringBuffer();
        System.out.println(binaryString);
        for (int i = 0; i < binaryString.length(); i+=5){
            System.out.println(binaryString.substring(i+1,i+5));
            literalValueString.append(binaryString.substring(i+1,i+5));
            //System.out.println(binaryString.substring(i+1,i+5));
            if (binaryString.substring(i,i+1).equals("0")){
                endOfPacket = i+5;
                break;
            }
        }
        Integer literalValue = Integer.parseInt(literalValueString.toString(),2);
        return  literalValue;
    }

    /**
    public Integer performOperations(HashMap<Integer, ArrayList<Integer>> values){
        Integer
        for (Integer key: values.keySet()){

        }
    }
**/
    public Integer performOperation(Integer typeID, List<Integer> values){
        Integer result = 0;
        if (typeID == 0){
            result = 0;
            for (int value:values){
                result = result + value;
            }
        }
        if (typeID == 1){
            result = 1;
            for (int value:values){
                result = result * value;
            }
        }
        if (typeID == 2){
            result = Integer.MAX_VALUE;
            for (int value:values){
                if (value < result){
                    result = value;
                }
            }
        }
        if (typeID == 3){
            result = 0;
            for (int value:values){
                if (value > result){
                    result = value;
                }
            }
        }

        if (typeID == 5){
            result = 0;
            if (values.get(0) > values.get(1)){
                result = 1;
            }
        }

        if (typeID == 6){
            result = 0;
            if (values.get(0) < values.get(1)){
                result = 1;
            }
        }

        if (typeID == 7){
            result = 0;
            if (values.get(0) == values.get(1)){
                result = 1;
            }
        }

        return result;

    }

    public Integer parseOperation(String binaryString, Integer previousTypeID,
                                  List<Integer> values, Integer finalResult){
        Packet packet  = new Packet(binaryString);
        typeID = packet.getTypeID();
        if (typeID == 4){
            Integer value = packet.getLiteralValueType4();
            values.add(value);
        }
        else {
            previousTypeID = typeID;
        }

        String subPacketsString = packet.getSubPacketsBinaryString();
        if (subPacketsString == null){
            finalResult += performOperation(previousTypeID,values);
            packetOperationResult = finalResult;
            return finalResult;
        }

        else {
            parseOperation(subPacketsString, previousTypeID,values,finalResult);
        }
        return finalResult;

    }


    public Integer getVersionSumInPacket(String binaryString, int versionSum){
        Packet packet  = new Packet(binaryString);
        versionSum = versionSum + packet.getVersion();
        String subPacketsString = packet.getSubPacketsBinaryString();
        if (subPacketsString == null){
            packetVersionSum = versionSum;
            return versionSum;
        }
        else {
            getVersionSumInPacket(subPacketsString,  versionSum);
        }
        return versionSum;
    }




}
