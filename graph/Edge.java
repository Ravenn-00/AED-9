package graph;

public class Edge<T> {
    private Vertex<T> refDest;
    private Vertex<T> refSource;
    private int weight;
    public Edge(Vertex<T> destination, int weight) { //constructor
        this.refDest = destination;
        this.weight = weight;
    }
    public Edge(Vertex<T> refSource, Vertex<T> destination) { //constructor
        this.refSource = refSource;
        this.refDest = destination;
    }
    public Edge(Vertex<T> refSource, Vertex<T> destination, int weight) { //constructor
        this.refSource = refSource;
        this.refDest = destination;
        this.weight = weight;
    }
    public Vertex<T> getSource() {
        return this.refSource;
    }
    public void setSource(Vertex<T> refSource) {
        this.refSource = refSource;
    }
    public Vertex<T> getDestination() {
        return this.refDest;
    }
    public void setDestination(Vertex<T> destination) {
        this.refDest = destination;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public boolean isEqual(Edge<T> other) { // metodo para comparar si 2 aristas son iguales (mismo destino y peso)
        return this.refDest.isEqual(other.getDestination()) && this.weight == other.getWeight();
    }
    public String toString() {
        if(this.weight > -1 ) return this.refDest.getData() + " (" + this.weight + ")";
        else return this.refDest.getData() + " ";
    }
    
}
