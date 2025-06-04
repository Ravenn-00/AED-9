package graph;

import list.LinkedList;
import list.Node;

public class GraphLink<T> {
    protected LinkedList<Vertex<T>> listVertex;
    public GraphLink() {
        this.listVertex = new LinkedList<Vertex<T>>();
    }
    public void insertVertex(T data) {
        Vertex<T> v = new Vertex<T>(data);
        if(!this.listVertex.exist(v)) {
            this.listVertex.insertLast(new Node<Vertex<T>>(v));
        }
    }
    public void insertEdge(T source, T destination, int weight) {
        
    }
    public void searchVertex() {

    }
    public void searchEdge() {

    }
    public void removeVertex() {

    }
    public void removeEdge() {

    }
    public void dfs() {

    }
    public void dfsPath() {

    }
    public void insertEdgeWeigh() {

    }
    public void shortPath() {

    }
    public void isConexo() {

    }
    public void Dijsktra() {

    }
    public String toString() {
        return this.listVertex.toString();
    }
    
}
