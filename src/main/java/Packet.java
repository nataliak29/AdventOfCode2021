import java.util.HashMap;

public class Packet {
    String binaryInput;
    Integer version;
    Integer typeID;
    Integer endOfPacket;
    String binaryString;
    Integer packetVersionSum;

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

    public Integer getLiteralValue(){
        StringBuffer literalValueString = new StringBuffer();
        System.out.println(binaryString);
        for (int i = 0; i < binaryString.length(); i+=5){
            literalValueString.append(binaryString.substring(i+1,i+5));
            System.out.println(binaryString.substring(i+1,i+5));
            if (binaryString.substring(i,i+1).equals("0")){
                endOfPacket = i+5;
                break;
            }
        }
        Integer literalValue = Integer.parseInt(literalValueString.toString(),2);
        return  literalValue;
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
