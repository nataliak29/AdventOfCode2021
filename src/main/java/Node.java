import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private List<Node> lowestRiskPath = new LinkedList<>();
    private Integer risk = Integer.MAX_VALUE;
    private Integer row;
    private Integer col;
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addAdjacentNodes(Node destination, int risk) {
        adjacentNodes.put(destination, risk);
    }

    public Node(Integer row,Integer col ) {
        this.row = row;
        this.col = col;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public Integer getRisk() {
        return this.risk;
    }

    public Integer getRow() {
        return this.row;
    }

    public Integer getCol() {
        return this.col;
    }

    public List<Node> getLowestRiskPath() {
        return this.lowestRiskPath;
    }

    public void setLowestRiskPath(LinkedList<Node> lowestRiskPath) {
        this.lowestRiskPath = lowestRiskPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return this.adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public String toString() {
        String thisNode = "Value: " + this.risk + "; row: " + this.getRow() +
                    "; column: " + this.getCol();

        return thisNode;
    }

    public String lowestRiskPathToString() {

        StringBuffer stringBuffer = new StringBuffer();
        for (Node node : lowestRiskPath) {
            stringBuffer.append(node.toString() + System.lineSeparator());
        }
        return stringBuffer.toString();
    }

}