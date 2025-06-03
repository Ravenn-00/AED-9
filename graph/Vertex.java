package graph;
import list.*;

public class Vertex<T> {
    private T data;
    protected LinkedList<Edge> listAdj;

    public Vertex(T data) {
        this.data = data;
        this.listAdj = new LinkedList<Edge>();
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public boolean equals(Object o) {
        if(o instanceof Vertex<?>) {
            Vertex<T> v = (Vertex<T>) o;
            return this.data.equals(v.getData());
        }
        return false;
    }
    public String toString() {
        return this.data + " --> " + listAdj.toString();
    }    
}
