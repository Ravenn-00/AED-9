package graph;

import list.LinkedLista;

public class GraphTest {
    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>();
        
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        
        graph.insertEdge("A", "B");
        graph.insertEdge("B", "C");
        graph.insertEdge("C", "D");
        graph.insertEdge("D", "A");
        
        System.out.println("Grafo:");
        System.out.println(graph);
        
        System.out.println("\nBFS desde A:");
        graph.bfs("A");
        
        System.out.println("\nDFS desde A:");
        graph.dfs("A");
        
        System.out.println("\nCamino mas corto de A a C:");
        LinkedLista<String> path = graph.bfsPath("A", "C");
        System.out.println(path);
        
        System.out.println("\n¿Es conexo? " + graph.isConexo());
        System.out.println("Tipo de grafo: " + graph.getGraphType());
        
        // Prueba GraphListEdge
        GraphListEdge<String, String> graphEdge = new GraphListEdge<>();
        graphEdge.insertVertex("A");
        graphEdge.insertVertex("B");
        graphEdge.insertVertex("C");
        
        graphEdge.insertEdge("A", "B");
        graphEdge.insertEdge("B", "C");
        
        System.out.println("\nGrafo con lista de aristas:");
        System.out.println(graphEdge);
        
        System.out.println("\nBFS desde A:");
        graphEdge.bfs("A");
    }
}