package list;

public class LinkedLista<T> {
    public Node<T> head;

    public LinkedLista() {
        this.head = null;
    }
    public LinkedLista(Node<T> _head) {
        this.head = _head;
    }
    public boolean isEmpty() {
        return this.head == null;
    }
    public int lenght() {
        int count = 0;
        Node<T> current = this.head;
        while (current != null) {
            current = current.getNext();
            count += 1;
        }
        return count;
    }
    public void insertFirst(Node<T> _node) {
        _node.setNext(this.head);
        this.head = _node;
    }
    public void insertLast(Node<T> _node) {
        if (this.isEmpty()) {
            this.head = _node;
            return;
        }
        Node<T> current = this.head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(_node);
    }
    public void removeLast() {
        if (this.isEmpty()) {
            return;
        }
        Node<T> current = this.head;
        Node<T> parent = this.head;
        while (current.getNext() != null) {
            parent = current;
            current = current.getNext();
        }
        parent.setNext(null);
    }    
    public void destroy() {
        this.head = null;
    }
    public void print() {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        int i = 0;
        Node<T> current = this.head;
        while (current != null) {
            System.out.println("Elemento " + i + ":  " 
            + current.getData());
            current = current.getNext();
            i += 1;
        }
    }
    public boolean exist(T _data) {
        Node<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(_data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public boolean exist(Node<T> _node) {
        Node<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(_node.getData())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public int search(T _data) {
        int i = 0;
        Node<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(_data)) {
                return i;
            }
            i += 1;
            current = current.getNext();
        }
        return -1;
    }
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = this.head;
        Node<T> next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        this.head = prev;
    }
    public void remove(T _data) {
        if (this.isEmpty()) {
            return;
        }
        if (this.head.getData().equals(_data)) {
            this.head = this.head.getNext();
            return;
        }
        Node<T> current = this.head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(_data)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }
    @Override
    public String toString() {
        String s = "";
        Node<T> current = this.head;
        while (current != null) {
            s += current.getData();
            if (current.getNext() != null)
                s += " -> ";
            current = current.getNext();
        }
        return s;
    }
}