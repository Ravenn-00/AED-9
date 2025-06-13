package graph;

import list.LinkedLista;

public interface TADGraph<T> {
    void insertVertex(T data);
    void insertEdge(T origin, T destination);
    void insertEdgeWeight(T origin, T destination, int weight);
    boolean searchVertex(T data);
    boolean searchEdge(T origin, T destination);
    void removeVertex(T data);
    void removeEdge(T origin, T destination);
    void dfs(T start);
    void bfs(T start);
    LinkedLista<T> bfsPath(T origin, T destination);
    LinkedLista<T> shortPath(T origin, T destination);
    boolean isConexo();
    int getNodeDegree(T data);
    String getGraphType();
}
