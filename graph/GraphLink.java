package graph;

import list.ExceptionIsEmpty;
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
    public void insertVertex(T data) { // inserta un nuevo vertice
        this.listVertex.insertLast(new list.Node<>(new Vertex<T>(data)));   
    }
    public void insertEdge(T verOri, T verDes) {  // inserta una arista entre dos vertices
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
    public boolean searchVertex(T v) {  // busca si un vertice existe en el grafo
        Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public boolean searchEdge(T v, T z) {   // busca si existe una arista entre dos vertices
        Vertex<T> vertexV = null, vertexZ = null;
        Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) {
                vertexV = current.getData();
            }
            if (current.getData().getData().equals(z)) {
                vertexZ = current.getData();
            }
            current = current.getNext();
        }        
        if (vertexV == null || vertexZ == null) return false;
        Node<Edge<T>> edgeNode = vertexV.listAdj.head;
        while (edgeNode != null) {
            if (edgeNode.getData().getDestination().equals(vertexZ)) {
                return true;
            }
            edgeNode = edgeNode.getNext();
        }
        
        return false;
    }
    public void removeVertex(T v) { 
        Vertex<T> vertexToRemove = null;
        Node<Vertex<T>> current = this.listVertex.head;
        Node<Vertex<T>> prev = null;
        while (current != null) {
            if (current.getData().getData().equals(v)) {
                vertexToRemove = current.getData();
                break;
            }
            prev = current;
            current = current.getNext();
        }        
        if (vertexToRemove == null) return;
        current = this.listVertex.head;
        while (current != null) {
            Vertex<T> vertex = current.getData();
            Node<Edge<T>> edgeNode = vertex.listAdj.head;
            Node<Edge<T>> edgePrev = null;
            
            while (edgeNode != null) {
                if (edgeNode.getData().getDestination().equals(vertexToRemove)) {
                    if (edgePrev == null) {
                        vertex.listAdj.head = edgeNode.getNext();
                    } else {
                        edgePrev.setNext(edgeNode.getNext());
                    }
                } else {
                    edgePrev = edgeNode;
                }
                edgeNode = edgeNode.getNext();
            }
            current = current.getNext();
        }
        if (prev == null) {
            this.listVertex.head = this.listVertex.head.getNext();
        } else {
            prev.setNext(current.getNext());
        }
    }
    public void removeEdge(T v, T z) {  // elimina una arista entre dos vertices
        Vertex<T> vertexV = null, vertexZ = null;
        Node<Vertex<T>> current = this.listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) {
                vertexV = current.getData();
            }
            if (current.getData().getData().equals(z)) {
                vertexZ = current.getData();
            }
            current = current.getNext();
        }
        if (vertexV == null || vertexZ == null) return;
        Node<Edge<T>> edgeNode = vertexV.listAdj.head;
        Node<Edge<T>> edgePrev = null;
        
        while (edgeNode != null) {
            if (edgeNode.getData().getDestination().equals(vertexZ)) {
                if (edgePrev == null) {
                    vertexV.listAdj.head = edgeNode.getNext();
                } else {
                    edgePrev.setNext(edgeNode.getNext());
                }
                break;
            }
            edgePrev = edgeNode;
            edgeNode = edgeNode.getNext();
        }
        edgeNode = vertexZ.listAdj.head;
        edgePrev = null;
        
        while (edgeNode != null) {
            if (edgeNode.getData().getDestination().equals(vertexV)) {
                if (edgePrev == null) {
                    vertexZ.listAdj.head = edgeNode.getNext();
                } else {
                    edgePrev.setNext(edgeNode.getNext());
                }
                break;
            }
            edgePrev = edgeNode;
            edgeNode = edgeNode.getNext();
        }
    }
    public void dfs(T v) {  // recorrido DFS (profundidad) desde un vertice
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
            System.out.println("vertice no existe ");
            return;
        }
        
        LinkedLista<Vertex<T>> visited = new LinkedLista<>();
        StackLink<Vertex<T>> stack = new StackLink<>();
        
        stack.push(start);
        visited.insertLast(new Node<>(start));
        
        while (!stack.isEmpty()) {
            try {
                Vertex<T> vertex = stack.pop();
                System.out.print(vertex.getData() + " ");
                
                Node<Edge<T>> adj = vertex.listAdj.head;
                while (adj != null) {
                    Vertex<T> neighbor = adj.getData().getDestination();
                    if (!visited.exist(neighbor)) {
                        stack.push(neighbor);
                        visited.insertLast(new Node<>(neighbor));
                    }
                    adj = adj.getNext();
                }
            } catch (ExceptionIsEmpty e) {
                break;
            }
        }
        System.out.println();
    }
    public String toString() {
        return this.listVertex.toString();
    }
    public void bfs(T v) {   // recorrido BFS (anchura) desde un vertice
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
            System.out.println("vertex no existe.  ");
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
    public LinkedLista<T> bfsPath(T v, T z) {   // encuentra el camino mas corto entre dos vertices usando BFS
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
    public void insertEdgeWeight(T v, T z, int w) {   // inserta una arista con peso entre dos vertices
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
    public boolean isConexo() {     // verifica si el grafo es conexo (todos los vertices estan conectados)
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
    public LinkedLista<T> shortPath(T v, T z) {   // encuentra el camino mas corto usando Dijkstra
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
            }
        }
        return path;
    }
    public String getGraphType() {   // devuelve el tipo de grafo (completo, ciclo, rueda, camino)
        if (isComplete()) return "Completo (K" + listVertex.lenght() + ")";
        if (isCycle()) return "Ciclo (C" + listVertex.lenght() + ")";
        if (isWheel()) return "Rueda (W" + (listVertex.lenght()-1) + ")";
        if (isPath()) return "Camino (P" + listVertex.lenght() + ")";
        return "Grafo general";
    }
    private boolean isComplete() {  // verifica si el grafo es completo (todos los vertices conectados entre si)
        int n = listVertex.lenght();
        int expectedEdges = n * (n - 1) / 2;
        int actualEdges = 0;
        Node<Vertex<T>> current = listVertex.head;
        while (current != null) {
            actualEdges += current.getData().listAdj.lenght();
            current = current.getNext();
        }
        actualEdges = actualEdges / 2;        
        return actualEdges == expectedEdges;
    }
    
    private boolean isCycle() {     // verifica si el grafo es un ciclo (todos los vertices con grado 2)
        if (listVertex.lenght() < 3) return false;
        Node<Vertex<T>> current = listVertex.head;
        while (current != null) {
            if (current.getData().listAdj.lenght() != 2) return false;
            current = current.getNext();
        }
        return isConexo();
    }
    
    private boolean isWheel() {   // verifica si el grafo es una rueda (un centro conectado a un ciclo)
        int n = listVertex.lenght();
        if (n < 4) return false;
        
        int centerCount = 0;
        int rimCount = 0;
        
        Node<Vertex<T>> current = listVertex.head;
        while (current != null) {
            int degree = current.getData().listAdj.lenght();
            if (degree == n-1) {
                centerCount++;
            } else if (degree == 3) {
                rimCount++;
            } else {
                return false;
            }
            current = current.getNext();
        }
        
        return centerCount == 1 && rimCount == n-1;
    }
    
    private boolean isPath() { // verifica si el grafo es un camino (vertices con grado 1 o 2)
        if (listVertex.lenght() < 2) return true;        
        int endCount = 0;
        int middleCount = 0;        
        Node<Vertex<T>> current = listVertex.head;
        while (current != null) {
            int degree = current.getData().listAdj.lenght();
            if (degree == 1) {
                endCount++;
            } else if (degree == 2) {
                middleCount++;
            } else {
                return false;
            }
            current = current.getNext();
        }
        
        return endCount == 2 && middleCount == listVertex.lenght() - 2 && isConexo();
    }    
    public int getNodeDegree(T v) { // obtiene el grado de un vertice (numero de aristas)
        Node<Vertex<T>> current = listVertex.head;
        while (current != null) {
            if (current.getData().getData().equals(v)) {
                return current.getData().listAdj.lenght();
            }
            current = current.getNext();
        }
        return -1;
    }
}