package graph;

import list.LinkedLista;
import list.Node;
import list.StackLink;
import list.QueueLink;

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
    public void insertEdgeWeight(T v, T z, int w) {
        Vertex<T> vOri = null, vDes = null;
        Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) vOri = current.getData();
            if (current.getData().getData().equals(z)) vDes = current.getData();
            current = current.getNext();
        }
        if (vOri != null && vDes != null) {
            Edge<T> edge1 = new Edge<>(vOri, vDes, w);
            Edge<T> edge2 = new Edge<>(vDes, vOri, w);
            vOri.listAdj.insertLast(new Node<>(edge1));
            vDes.listAdj.insertLast(new Node<>(edge2));
            this.listEdge.insertLast(new Node<>(edge1));
            this.listEdge.insertLast(new Node<>(edge2));
        }
    }
    public boolean isConexo() {
        if (this.listVertex.isEmpty()) return true;
        LinkedLista<Vertex<T>> visited = new LinkedLista<>();
        QueueLink<Vertex<T>> queue = new QueueLink<>();
        Vertex<T> start = this.listVertex.head.getData();
        queue.enqueue(start);
        visited.insertLast(new Node<>(start));
        while (!queue.isEmpty()) {
            Vertex<T> vertex = null;
            try {
                vertex = queue.dequeue();
            } catch (list.ExceptionIsEmpty e) {
                break;
            }
            Node<Edge<T>> adj = vertex.listAdj.head;
            while (adj != null) {
                Vertex<T> neighbor = adj.getData().getDestination();
                if (!visited.exist(neighbor)) {
                    queue.enqueue(neighbor);
                    visited.insertLast(new Node<>(neighbor));
                }
                adj = adj.getNext();
            }
        }
        return visited.lenght() == this.listVertex.lenght();
    }
    public LinkedLista<T> shortPath(T v, T z) {
        return dijkstraPath(v, z);
    }
    public StackLink<T> Dijkstra(T v, T w) {
        LinkedLista<T> path = dijkstraPath(v, w);
        StackLink<T> stack = new StackLink<>();
        Node<T> node = path.head;
        StackLink<T> temp = new StackLink<>();
        while (node != null) {
            temp.push(node.getData());
            node = node.getNext();
        }
        while (!temp.isEmpty()) {
            try {
                stack.push(temp.pop());
            } catch (list.ExceptionIsEmpty e) {
                // No debería ocurrir
            }
        }
        return stack;
    }
    private LinkedLista<T> dijkstraPath(T v, T z) {
        Vertex<T> start = null, end = null;
        Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) start = current.getData();
            if (current.getData().getData().equals(z)) end = current.getData();
            current = current.getNext();
        }
        LinkedLista<T> path = new LinkedLista<>();
        if (start == null || end == null) return path;
        LinkedLista<Vertex<T>> vertices = this.listVertex;
        LinkedLista<Vertex<T>> visited = new LinkedLista<>();
        java.util.HashMap<Vertex<T>, Integer> dist = new java.util.HashMap<>();
        java.util.HashMap<Vertex<T>, Vertex<T>> prev = new java.util.HashMap<>();

        current = vertices.head;
        while (current != null) {
            dist.put(current.getData(), Integer.MAX_VALUE);
            prev.put(current.getData(), null);
            current = current.getNext();
        }
        dist.put(start, 0);

        while (visited.lenght() < this.listVertex.lenght()) {
            Vertex<T> u = null;
            int minDist = Integer.MAX_VALUE;
            Node<Vertex<T>> node = vertices.head;
            while (node != null) {
                Vertex<T> vtx = node.getData();
                if (!visited.exist(vtx) && dist.get(vtx) < minDist) {
                    minDist = dist.get(vtx);
                    u = vtx;
                }
                node = node.getNext();
            }
            if (u == null) break;
            visited.insertLast(new Node<>(u));
            Node<Edge<T>> adj = u.listAdj.head;
            while (adj != null) {
                Vertex<T> neighbor = adj.getData().getDestination();
                int weight = adj.getData().getWeight();
                if (!visited.exist(neighbor)) {
                    int alt = dist.get(u) + weight;
                    if (alt < dist.get(neighbor)) {
                        dist.put(neighbor, alt);
                        prev.put(neighbor, u);
                    }
                }
                adj = adj.getNext();
            }
        }
        Vertex<T> step = end;
        StackLink<T> tempStack = new StackLink<>();
        while (step != null) {
            tempStack.push(step.getData());
            step = prev.get(step);
        }
        try {
            if (tempStack.isEmpty() || !tempStack.top().equals(v)) return new LinkedLista<T>();
        } catch (list.ExceptionIsEmpty e) {
            return new LinkedLista<T>();
        }
        while (!tempStack.isEmpty()) {
            try {
                path.insertLast(new Node<>(tempStack.pop()));
            } catch (list.ExceptionIsEmpty e) {
                // No debería ocurrir
            }
        }
        return path;
    }
}