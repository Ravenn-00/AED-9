package list;

public class Node<T> {
    public T data;
    public Node<T> next;
    public Node()   {
        this.data = null;
        this.next = null;
    }
    public Node(T _data)    {
        this.data = _data;
        this.next = null;
    }
    public Node(T _data, Node<T> _node)    {
        this.data = _data;
        this.next = _node;
    }
    public Node<T> getNext()    {
        return this.next;
    }
    public void setNext(Node<T> _node)    {
        this.next = _node;
    }
    public T getData()    {
        return this.data;
    }
    public void setData(T _data)    {
        this.data = _data;
    }
}

