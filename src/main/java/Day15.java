import java.io.FileNotFoundException;
import java.util.*;

public class Day15 extends Day {
    static String RESOURCE = "src/main/resources/day15_input.txt";

    public Day15() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException {
        long start2 = System.currentTimeMillis();
        //System.out.println("Part1: " + new Day15().partOneAnswer(RESOURCE));
        System.out.println("Part2: " + new Day15().partTwoAnswer(RESOURCE));
        long end2 = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end2-start2));
    }

    @Override
    public String partOneAnswer(String resource) throws FileNotFoundException {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Grid grid = createGrid(inputArray);
        Node startNode = new Node(0, 0);
        Node endNode = new Node(grid.getTotalRows() - 1, grid.getTotalColumn() - 1);
        Map<Node, Integer> adjacentNodeToStart = getAdjacentNode(startNode, grid);
        startNode.setRisk(0);
        startNode.setAdjacentNodes(adjacentNodeToStart);
        Graph risksGraph = createRisksGraph(grid);
        int minRisk = calculateShortestPathFromStartToEnd(risksGraph, startNode, endNode);
        int answer = minRisk;
        return String.valueOf(answer);
    }

    @Override
    public String partTwoAnswer(String resource) {
        ArrayList<String> inputArray = getResourceAsStringArray(resource);
        Grid grid = createGridLarge(inputArray);

        Node startNode = new Node(0, 0);
        Node endNode = new Node(grid.getTotalRows() - 1, grid.getTotalColumn() - 1);
        Map<Node, Integer> adjacentNodeToStart = getAdjacentNode(startNode, grid);
        startNode.setRisk(0);
        startNode.setAdjacentNodes(adjacentNodeToStart);
        Graph risksGraph = createRisksGraph(grid);
        int minRisk = calculateShortestPathFromStartToEnd(risksGraph, startNode, endNode);
        int answer = minRisk;
        return String.valueOf(answer);
    }

    public Grid createGrid(ArrayList<String> inputArray) {
        int numberOrRows = inputArray.size();
        int numberOfColumns = inputArray.get(0).length();
        int[][] inputs = new int[numberOrRows][numberOfColumns];
        for (int i = 0; i < numberOrRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                inputs[i][j] = Integer.parseInt(inputArray.get(i).substring(j, j + 1));
            }
        }
        Grid grid = new Grid(inputs);
        return grid;
    }

    public Grid createGridLarge(ArrayList<String> inputArray) {
        int numberOrRows = inputArray.size();
        int numberOfColumns = inputArray.get(0).length();
        int[][] inputs = new int[numberOrRows * 5][numberOfColumns * 5];
        for (int i = 0; i < numberOrRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                inputs[i][j] = Integer.parseInt(inputArray.get(i).substring(j, j + 1));
            }
        }

        for (int i = numberOrRows; i < numberOrRows * 5; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                int newValue = inputs[i - numberOrRows][j] + 1;
                if (newValue >= 10) {
                    newValue = 1;
                }
                inputs[i][j] = newValue;
            }
        }

        for (int j = numberOfColumns; j < numberOfColumns * 5; j++) {
            for (int i = 0; i < numberOrRows; i++) {
                int newValue = inputs[i][j - numberOfColumns] + 1;
                if (newValue >= 10) {
                    newValue = 1;
                }
                inputs[i][j] = newValue;
            }
        }

        for (int i = numberOrRows; i < numberOrRows * 5; i++) {
            for (int j = numberOrRows; j < numberOfColumns * 5; j++) {
                int newValue = inputs[i - numberOrRows][j] + 1;
                if (newValue >= 10) {
                    newValue = 1;
                }
                inputs[i][j] = newValue;
            }
        }

        Grid grid = new Grid(inputs);
        return grid;
    }


    public Graph createRisksGraph(Grid grid) {
        Graph risksGraph = new Graph();
        int gridNumberOrRows = grid.getTotalRows();
        int gridNumberOfColumns = grid.getTotalColumn();
        for (int i = 0; i < gridNumberOrRows; i++) {
            for (int j = 0; j < gridNumberOfColumns; j++) {
                int riskValue = grid.getValue(i, j);
                Node node = new Node(i, j);
                node.setRisk(riskValue);
                Map<Node, Integer> adjacentNodes = getAdjacentNode(node, grid);
                node.setAdjacentNodes(adjacentNodes);
                risksGraph.addNode(node);
            }
        }
        return risksGraph;
    }

    public Map<Node, Integer> getAdjacentNode(Node node, Grid grid) {
        int dRow[] = {0, 1, 0, -1};
        int dCol[] = {-1, 0, 1, 0};
        int nodeRow = node.getRow();
        int nodeCol = node.getCol();
        Map<Node, Integer> adjacentNodesMap = new HashMap<Node, Integer>();

        for (int i = 0; i < 4; i++) {
            int adjRow = nodeRow + dRow[i];
            int adjCol = nodeCol + dCol[i];

            if (adjRow < grid.getTotalRows() && adjCol < grid.getTotalColumn()
                    && adjCol >= 0 && adjRow >= 0) {
                Node adjacentNode = new Node(adjRow, adjCol);
                int adjacentNodeRisk = grid.getValue(adjRow, adjCol);
                adjacentNodesMap.put(adjacentNode, adjacentNodeRisk);
            }

        }
        return adjacentNodesMap;
    }

    public int calculateShortestPathFromStartToEnd(Graph graph, Node start, Node end) {
        int endRow = end.getRow();
        int endCol = end.getCol();
        int minRisk = Integer.MAX_VALUE;
        start.setRisk(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        //Queue<Node> unsettledNodes = new PriorityQueue<>();
        unsettledNodes.add(start);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestRiskNode(unsettledNodes);
            //Node currentNode = unsettledNodes.poll();
            //System.out.println(Arrays.toString(unsettledNodes.toArray()));
            //System.out.println("currentNode  " + currentNode);
            //System.out.println("currentNodeOriginal " + currentNodeOriginal);
            unsettledNodes.remove(currentNode);
            Map<Node, Integer> adjacentNodes = graph.getNode(currentNode.getRow(), currentNode.getCol())
                    .getAdjacentNodes();
            for (Node adjacentNode : adjacentNodes.keySet()) {
                Integer adjacentNodeRisk = adjacentNodes.get(adjacentNode);
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, adjacentNodeRisk, currentNode);

                if (currentNode.getRow() == endRow & currentNode.getCol() == endCol) {
                        for (Node n : adjacentNode.getLowestRiskPath()) {
                            if (n.getRow() == endRow & n.getCol() == endCol
                                    & n.getRisk() < minRisk) {
                                minRisk = n.getRisk();
                            }
                        }
                    }


                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return minRisk;
    }


    private Node getLowestRiskNode(Set<Node> unsettledNodes) {
        Node lowestRiskNode = null;
        int lowestRisk = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeRisk = node.getRisk();
            if (nodeRisk < lowestRisk) {
                lowestRisk = nodeRisk;
                lowestRiskNode = node;
            }
        }
        return lowestRiskNode;
    }


    private void calculateMinimumDistance(Node adjacentNode,
                                          Integer adjacentNodeRisk, Node currentNode) {
        Integer startNodeRisk = currentNode.getRisk();
        if (startNodeRisk + adjacentNodeRisk < adjacentNode.getRisk()) {
            adjacentNode.setRisk(startNodeRisk + adjacentNodeRisk);
            LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getLowestRiskPath());
            shortestPath.add(currentNode);
            adjacentNode.setLowestRiskPath(shortestPath);
        }
    }
}