package graph;
// import list.*;
public class Edge<T> {
    private Vertex<T> refDest;
    private int weight;

    public Edge(Vertex<T> destination, int weight) {
        this.refDest = destination;
        this.weight = weight;
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
    public boolean equals(Object o) {
        if(o instanceof Edge<?>) {
            Edge<T> e = (Edge<T>) o;
            return this.refDest.equals(e.getDestination()) && this.weight == e.getWeight();
        }
        return false;
    }
    public String toString() {
        if(this.weight > -1 ) return this.refDest.getData() + " (" + this.weight + ")";
        else return this.refDest.getData() + " ";
    }
    
}
