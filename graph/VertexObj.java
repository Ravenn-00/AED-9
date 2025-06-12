package graph;

public class VertexObj<V, E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    public V getInfo() { return info; }
    public int getPosition() { return position; }
    
    public boolean isEqual(VertexObj<V, E> other) { // compara si dos vertices son iguales (misma info)
        return this.info.equals(other.info);
    }
    
    public String toString() {
        return info.toString();
    }
}