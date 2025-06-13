package graph;

import list.LinkedLista;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphTest {
    public static void main(String[] args) {

        SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        System.out.println(graph);
    }
}