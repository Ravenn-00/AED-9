package graph;

import list.LinkedLista;
import list.Node;

public class GraphListEdge<V, E> {
    private LinkedLista<VertexObj<V, E>> secVertex;
    private LinkedLista<EdgeObj<V, E>> secEdge;
    private int vertexCount;
    private int edgeCount;

    public GraphListEdge() {
        this.secVertex = new LinkedLista<>();
        this.secEdge = new LinkedLista<>();
        this.vertexCount = 0;
        this.edgeCount = 0;
    }

    public void insertVertex(V v) { // inserta un vertice si no existe
        if (!searchVertex(v)) {
            VertexObj<V, E> vertex = new VertexObj<>(v, vertexCount++);
            secVertex.insertLast(new Node<>(vertex));
        }
    }

    public void insertEdge(V v, V z) { // inserta una arista entre dos vertices si no existe
        VertexObj<V, E> vertexV = findVertex(v);
        VertexObj<V, E> vertexZ = findVertex(z);
        
        if (vertexV != null && vertexZ != null && !searchEdge(v, z)) {
            EdgeObj<V, E> edge = new EdgeObj<>(vertexV, vertexZ, null, edgeCount++);
            secEdge.insertLast(new Node<>(edge));
        }
    }

    public boolean searchVertex(V v) { // busca si un vertice existe
        return findVertex(v) != null;
    }

    public boolean searchEdge(V v, V z) { // busca si una arista existe entre dos vertices
        VertexObj<V, E> vertexV = findVertex(v);
        VertexObj<V, E> vertexZ = findVertex(z);
        
        if (vertexV == null || vertexZ == null) return false;
        
        Node<EdgeObj<V, E>> current = secEdge.head;
        while (current != null) {
            EdgeObj<V, E> edge = current.getData();
            if ((edge.getEndVertex1().equals(vertexV) && edge.getEndVertex2().equals(vertexZ)) ||
                (edge.getEndVertex1().equals(vertexZ) && edge.getEndVertex2().equals(vertexV))) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void bfs(V v) { // recorrido BFS desde un vertice
        VertexObj<V, E> start = findVertex(v);
        
        if (start == null) {
            System.out.println("vertice no existe ");
            return;
        }
        
        LinkedLista<VertexObj<V, E>> visited = new LinkedLista<>();
        LinkedLista<VertexObj<V, E>> queue = new LinkedLista<>();
        
        queue.insertLast(new Node<>(start));
        visited.insertLast(new Node<>(start));
        
        while (!queue.isEmpty()) {
            VertexObj<V, E> vertex = queue.head.getData();
            queue.remove(vertex);
            System.out.print(vertex.getInfo() + " ");
            
            Node<EdgeObj<V, E>> edgeNode = secEdge.head;
            while (edgeNode != null) {
                EdgeObj<V, E> edge = edgeNode.getData();
                VertexObj<V, E> neighbor = null;
                
                if (edge.getEndVertex1().equals(vertex)) {
                    neighbor = edge.getEndVertex2();
                } else if (edge.getEndVertex2().equals(vertex)) {
                    neighbor = edge.getEndVertex1();
                }
                
                if (neighbor != null && !visited.exist(neighbor)) {
                    queue.insertLast(new Node<>(neighbor));
                    visited.insertLast(new Node<>(neighbor));
                }
                edgeNode = edgeNode.getNext();
            }
        }
        System.out.println();
    }
    
    private VertexObj<V, E> findVertex(V v) { // busca un vertice por su dato
        Node<VertexObj<V, E>> current = secVertex.head;
        while (current != null) {
            if (current.getData().getInfo().equals(v)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("vertices:\n");
        Node<VertexObj<V, E>> vertexNode = secVertex.head;
        while (vertexNode != null) {
            sb.append(vertexNode.getData().getInfo()).append("\n");
            vertexNode = vertexNode.getNext();
        }
        
        sb.append("\nAristas:\n");
        Node<EdgeObj<V, E>> edgeNode = secEdge.head;
        while (edgeNode != null) {
            sb.append(edgeNode.getData().toString()).append("\n");
            edgeNode = edgeNode.getNext();
        }
        return sb.toString();
    }
}