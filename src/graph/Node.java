package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String label;
    private List<Edge> edges = new ArrayList<>();
    private List<String>ids = new ArrayList<>();

    private Node predecessor;
    private int distanceFromSource = Integer.MAX_VALUE;
    private int eccentricity = -1;
    private boolean marked = false;

    public Node(String label) {
        this.label = label;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getId() {
        return ids;
    }

    public void setId(List<String>ids) {
        this.ids =ids;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getWeightForNeigbhor(Node nodeTo) {
        int weight = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getNodeTo().equals(nodeTo)) {
                weight = edges.get(i).getWeight();
            }
        }
        return (weight);
    }

    public Edge getEdgeToNeighbor(Node neighbor) {
        for (Edge edge: edges) {
            if (edge.getNodeTo().equals(neighbor)) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "graph.Node{" +
                "label='" + label + '\'' +
                ",ids=" +ids +
                ", marked=" + marked +
                '}';
    }

    public int getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(int eccentricity) {
        this.eccentricity = eccentricity;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }
}
