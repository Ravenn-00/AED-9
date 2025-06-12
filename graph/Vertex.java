package graph;
import list.LinkedLista;

public class Vertex<T> {
    private T data;
    protected LinkedLista<Edge<T>> listAdj;

    public Vertex(T data) {
        this.data = data;
        this.listAdj = new LinkedLista<Edge<T>>();
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public boolean isEqual(Vertex<T> other) { // compara si dos vertices son iguales
        return this.data.equals(other.getData());
    }
    public String toString() {
        return this.data + " --> " + listAdj.toString();
    }    
}