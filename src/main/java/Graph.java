import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Node getNode(int row, int col){
        for (Node node : nodes) {
            int thisNodeRow = node.getRow();
            int thisNodeCol = node.getCol();
            if (thisNodeCol == col && thisNodeRow == row){
                return node;
            }
        }
        return null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Node node : nodes) {
            String thisNode = "Value: " + node.getRisk() + "; row: " + node.getRow() +
                    "; column: " + node.getCol() + node.getAdjacentNodes();
            stringBuffer.append(thisNode + System.lineSeparator());
        }
        return stringBuffer.toString();
    }

}