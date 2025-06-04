package graph;

import list.LinkedList;

public class GraphListEdge<V,E> {
    LinkedList<VertexObj<V,E>> secVertex;
    LinkedList<EdgeObj<V,E>> secEdge;
    public GraphListEdge(){
        this.secVertex = new LinkedList<VertexObj<V,E>>();
        this.secEdge = new LinkedList<EdgeObj<V,E>>();
    }
}
