package graph;

import java.util.*;

public class Graph {
    private Map<Integer, Node> nodes = new HashMap<>();

    private List<Edge> edges = new ArrayList<>();

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public void printGraph() {
        for (Map.Entry<Integer, Node> mapentry : nodes.entrySet()) {
            System.out.println("*****************************");
            System.out.println("Nom: " + mapentry.getValue().getLabel());
            System.out.println("ID: " + mapentry.getKey());
            System.out.println("Voisins: ");
            for (int i = 0; i < mapentry.getValue().getEdges().size(); i++) {
                System.out.println("    " + mapentry.getValue().getEdges().get(i).getNodeTo().getLabel() + ", distance: " + mapentry.getValue().getEdges().get(i).getWeight());
            }
        }
    }

    public Node findNodeByName(String name) {
        for (Map.Entry<Integer, Node> mapentry : nodes.entrySet()) {
            if (mapentry.getValue().getLabel().equals(name)) {
                return mapentry.getValue();
            }
        }

        throw new RuntimeException("Node not found");
//        return null;
    }

    public void addUndirectedEdgeBetweenNodes(int node1Index, int node2Index, int weight) {
        addEdgeBetweenNodes(node1Index, node2Index, weight);
        addEdgeBetweenNodes(node2Index, node1Index, weight);
    }

    public void addEdgeBetweenNodes(int node1Index, int node2Index, int weight) {
        Node node1 = nodes.get(node1Index);
        Node node2 = nodes.get(node2Index);

        Edge edge = new Edge(node1, node2, weight);

        edges.add(edge);
        node1.addEdge(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
        List<Edge> nodeFromEdges = edge.getNodeFrom().getEdges();

        nodeFromEdges.remove(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}