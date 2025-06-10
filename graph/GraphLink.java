package graph;

import list.LinkedLista;
import list.Node;

public class GraphLink<T> {
    protected LinkedLista<Vertex<T>> listVertex;
    protected LinkedLista<Edge<T>> listEdge;
    public GraphLink() {
        this.listVertex = new LinkedLista<>();
        this.listEdge = new LinkedLista<>();
    }
    public void insertVertex(T data) {
        this.listVertex.insertLast(new list.Node<>(new Vertex<T>(data)));   
    }
    public void insertEdge(T verOri, T verDes) {
        Vertex<T> vOri = null, vDes = null;
        list.Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(verOri)) {
                vOri = current.getData();
            }
            if (current.getData().getData().equals(verDes)) {
                vDes = current.getData();
            }
            current = current.getNext();
        }
        if (vOri != null && vDes != null) {
            Edge<T> edge = new Edge<>(vOri, vDes, -1);
            vOri.listAdj.insertLast(new list.Node<>(edge));
            this.listEdge.insertLast(new list.Node<>(edge));
        }
    }
    public String toString() {
        return this.listVertex.toString();
    }
    public void bfs(T v) {
        Vertex<T> start = null;
        Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) {
                start = current.getData();
                break;
            }
            current = current.getNext();
        }
        if (start == null) {
            System.out.println("Vertex no existe.  ");
            return;
        }
        LinkedLista<Vertex<T>> visited = new LinkedLista<>();
        LinkedLista<Vertex<T>> queue = new LinkedLista<>();
        queue.insertLast(new Node<>(start));
        visited.insertLast(new Node<>(start));
        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.head.getData();
            queue.remove(vertex);
            System.out.print(vertex.getData() + " ");
            Node<Edge<T>> adj = vertex.listAdj.head;
            while (adj != null) {
                Vertex<T> neighbor = adj.getData().getDestination();
                if (!visited.exist(neighbor)) {
                    queue.insertLast(new Node<>(neighbor));
                    visited.insertLast(new Node<>(neighbor));
                }
                adj = adj.getNext();
            }
        }
        System.out.println();
    }
    public LinkedLista<T> bfsPath(T v, T z) {
        Vertex<T> start = null, end = null;
        Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) {
                start = current.getData();
            }
            if (current.getData().getData().equals(z)) {
                end = current.getData();
            }
            current = current.getNext();
        }
        LinkedLista<T> path = new LinkedLista<>();
        if (start == null || end == null) return path;

        LinkedLista<Vertex<T>> visited = new LinkedLista<>();
        LinkedLista<Vertex<T>> queue = new LinkedLista<>();
        LinkedLista<NodeParent<T>> parents = new LinkedLista<>();

        queue.insertLast(new Node<>(start));
        visited.insertLast(new Node<>(start));
        parents.insertLast(new Node<>(new NodeParent<T>(start, null)));

        boolean found = false;
        while (!queue.isEmpty() && !found) {
            Vertex<T> vertex = queue.head.getData();
            queue.remove(vertex);
            Node<Edge<T>> adj = vertex.listAdj.head;
            while (adj != null) {
                Vertex<T> neighbor = adj.getData().getDestination();
                if (!visited.exist(neighbor)) {
                    queue.insertLast(new Node<>(neighbor));
                    visited.insertLast(new Node<>(neighbor));
                    parents.insertLast(new Node<>(new NodeParent<T>(neighbor, vertex)));
                    if (neighbor.equals(end)) {
                        found = true;
                        break;
                    }
                }
                adj = adj.getNext();
            }
        }
        if (!found) return path;
        Vertex<T> step = end;
        LinkedLista<T> reversePath = new LinkedLista<>();
        while (step != null) {
            reversePath.insertFirst(new Node<>(step.getData()));
            Node<NodeParent<T>> p = parents.head;
            Vertex<T> parent = null;
            while (p != null) {
                if (p.getData().vertex.equals(step)) {
                    parent = p.getData().parent;
                    break;
                }
                p = p.getNext();
            }
            step = parent;
        }
        Node<T> n = reversePath.head;
        while (n != null) {
            path.insertLast(new Node<>(n.getData()));
            n = n.getNext();
        }
        return path;
    }
    private static class NodeParent<T> {
        Vertex<T> vertex;
        Vertex<T> parent;
        NodeParent(Vertex<T> vertex, Vertex<T> parent) {
            this.vertex = vertex;
            this.parent = parent;
        }
    }
}