package graph;

public class Edge {
    private Node nodeFrom;
    private Node nodeTo;
    private int weight;
    private int betweenness = 0;

    Edge(Node nodeFrom, Node nodeTo, int weight) {
        this.nodeTo = nodeTo;
        this.nodeFrom = nodeFrom;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "nodeFrom=" + nodeFrom +
                ", nodeTo=" + nodeTo +
                ", weight=" + weight +
//                ", id='" + id + '\'' +
                '}';
    }

    public Node getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(Node nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public Node getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(Node nodeTo) {
        this.nodeTo = nodeTo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBetweenness() {
        return betweenness;
    }

    public void setBetweenness(int betweenness) {
        this.betweenness = betweenness;
    }
}
