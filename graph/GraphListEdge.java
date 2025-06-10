package graph;

import list.LinkedLista;

public class GraphListEdge<V,E> {
    LinkedLista<VertexObj<V,E>> secVertex;
    LinkedLista<EdgeObj<V,E>> secEdge;
    public GraphListEdge(){
        this.secVertex = new LinkedLista<VertexObj<V,E>>();
        this.secEdge = new LinkedLista<EdgeObj<V,E>>();
    }
}
