import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Grid {
    int[][] grid;
    HashMap<Pair<Integer, Integer>, Integer> distanceMap = new HashMap<>();


    public Grid(int[][] grid) {
        this.grid = grid;
    }

    public Integer getTotalRows() {
        return grid.length;
    }

    public Integer getTotalColumn() {
        return grid[0].length;
    }

    public Integer getValue(int row, int col) {
        return grid[row][col];
    }

    public void updateGrid(int row, int col, int value) {
        for (int i = 0; i < getTotalRows(); i++) {
            for (int j = 0; j < getTotalColumn(); j++) {
                if (i == row & j == col) {
                    grid[i][j] = value;
                }
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < getTotalRows(); i++) {
            stringBuffer.append("{");
            for (int j = 0; j < getTotalColumn(); j++) {
                if (j == getTotalColumn() - 1) {
                    stringBuffer.append(String.valueOf(grid[i][j]));
                } else {
                    stringBuffer.append(String.valueOf(grid[i][j]) + ",");
                }
            }
            stringBuffer.append("}" + System.lineSeparator());
        }
        return stringBuffer.toString();
    }

    public Boolean isValidCell(int row, int col) {
        int ROW = getTotalRows();
        int COL = getTotalColumn();
        if (row < 0 || col < 0 ||
                row >= ROW || col >= COL) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }


        final Grid other = (Grid) obj;

        if (getTotalRows() != other.getTotalRows()) {
            return false;
        }

        if (getTotalColumn() != other.getTotalColumn()) {
            return false;
        }

        for (int i = 0; i < getTotalRows(); i++) {
            for (int j = 0; j < getTotalColumn(); j++) {
                if (grid[i][j] != other.getValue(i, j)) {
                    return false;
                }

            }
        }
        return true;

    }

    public void setUpDistanceMap() {
        for (int i = 0; i < getTotalRows(); i++) {
            for (int j = 0; j < getTotalColumn(); j++) {
                Pair coordinates = new Pair<>(i, j);
                if (i == 0 && j == 0) {
                    distanceMap.put(coordinates, 0);
                } else {
                    distanceMap.put(coordinates, Integer.MAX_VALUE);
                }
            }
        }
    }

    public Map<Pair<Integer, Integer>, Integer> getAdjacentNodes(Pair<Integer, Integer> start) {
        int dRow[] = {0, 1, 0, -1};
        int dCol[] = {-1, 0, 1, 0};
        int nodeRow = start.getKey();
        int nodeCol = start.getValue();
        Map<Pair<Integer, Integer>, Integer> adjacentNodesMap = new HashMap<Pair<Integer, Integer>, Integer>();

        for (int i = 0; i < 4; i++) {
            int adjRow = nodeRow + dRow[i];
            int adjCol = nodeCol + dCol[i];

            if (adjRow < getTotalRows() && adjCol < getTotalColumn()
                    && adjCol >= 0 && adjRow >= 0) {
                Pair adjacentNode = new Pair(adjRow, adjCol);
                int adjacentNodeRisk = getValue(adjRow, adjCol);
                adjacentNodesMap.put(adjacentNode, adjacentNodeRisk);
            }

        }
        return adjacentNodesMap;

    }

    public Pair<Integer, Integer> findMinimumDistance(Map<Pair<Integer, Integer>, Integer> distances) {
        int minDistance = Integer.MAX_VALUE;
        Pair minPair = new Pair(0, 0);
        for (Pair pair : distances.keySet()) {
            if (distances.get(pair) < minDistance) {
                minDistance = distances.get(pair);
                minPair = pair;
            }
        }
        return minPair;
    }


    public void dijkstra(Pair<Integer, Integer> start) {
        int numberOfItems = getTotalColumn() * getTotalRows();
        Map<Pair<Integer, Integer>, Integer> settledNodes = new HashMap<>();
        Map<Pair<Integer, Integer>, Integer> unsettledNodes = new HashMap<>();
        setUpDistanceMap();
        unsettledNodes.put(start, 0);


        while (settledNodes.size() != numberOfItems) {
            Pair<Integer, Integer> minPair = findMinimumDistance(unsettledNodes);
            int value = unsettledNodes.get(minPair);
            unsettledNodes.remove(minPair);
            settledNodes.put(minPair,value);
            Map<Pair<Integer, Integer>, Integer> adjacentNodesMap = getAdjacentNodes(minPair);
            for (Map.Entry<Pair<Integer, Integer>, Integer> entry : adjacentNodesMap.entrySet()) {
                Pair<Integer, Integer> entryKey = entry.getKey();

                if (!settledNodes.containsKey(entry.getKey())) {
                    int edgeDistanceFromGrid = getValue(entryKey.getKey(), entryKey.getValue());
                    int newDistance = distanceMap.get(minPair) + edgeDistanceFromGrid;
                    if (newDistance < distanceMap.get(entryKey) )
                        distanceMap.put(entryKey, newDistance);
                    unsettledNodes.put(entryKey, distanceMap.get(entryKey));
                }
            }

        }
    }

    public HashMap<Pair<Integer, Integer>, Integer> getDistanceMap() {
        return distanceMap;
    }
}

