package list;

public class LinkedList<T> {
    public Node<T> head;
    public Node<T> var;
    public LinkedList() {
        this.head = null;
        this.var = null;
    }
    public LinkedList(Node<T> _head) {
        this.head = _head;
        this.var = _head;
    }
    public boolean isEmpty()    {
        return this.head == null;
    }
    public int lenght() {
        int count = 0;
        while (this.var != null) {
            this.var = this.var.getNext();
            count += 1;
        }
        this.var = this.head;
        System.out.println("lenght  " + count);;
        return count;
    }
    public void insertFirst(Node<T> _node)  {
        if(this.isEmpty())   {
            System.out.println("Empty list");
            return;
        }
        System.out.println("insert first:  " + _node.getData());
        _node.setNext(this.head);
        this.head = _node;
        this.var = _node;
    }
    public void insertLast(Node<T> _node)  {
        if(this.isEmpty())   {
            System.out.println("Empty list");
            return;
        }
        System.out.println("insert last:  " + _node.getData());
        while(this.var.getNext() != null)   {
            this.var = this.var.getNext();
        }
        this.var.setNext(_node);
        this.var = this.head;
    }
    public void destroy()   {
        System.out.println("destroy list");
        this.head = null;
        this.var = null;
    }
    public void print() {
        if(this.isEmpty())   {
            System.out.println("Empty list");
            return;
        }
        int i = 0;
        System.out.println("printing.........");
        while(this.var != null)   {
            System.out.println("Elemento " + i + ":  " + this.var.getData());
            this.var = this.var.getNext();
            i += 1;
        }
        this.var = this.head;
    }
    public boolean exist(T _data)    {
        if(this.isEmpty())   {
            System.out.println("Empty list");
            return false;
        }
        while(this.var != null)   {
            if(this.var.getData() == _data) {
                this.var = this.head;
                System.out.println(_data + " exists");
                return true;
            }
            this.var = this.var.getNext();
        }
        this.var = this.head;
        System.out.println(_data + " doenst exists");
        return false;
    }
    public int search(T _data)    {
        if(this.isEmpty())   {
            System.out.println("Empty list");
            return -1;
        }
        int i = 0;
        while(this.var != null)   {
            if(this.var.getData() == _data) {
                this.var = this.head;
                System.out.println("search "+ _data + " found at " + i);
                return i;
            }
            i += 1;
            this.var = this.var.getNext();
        }
        this.var = this.head;
        System.out.println("search " + _data + " failed");
        return -1;
    }
    public void reverse() {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return;
        }    
        Node<T> prev = null;
        Node<T> next = null;    
        while (this.var != null) {
            next = this.var.getNext();
            this.var.setNext(prev);
            prev = this.var;
            this.var = next;
        }    
        this.head = prev;
        this.var = this.head;
        System.out.println("List reversed");
    }
    public void remove(T _data)   {
        if(this.isEmpty())   {
            System.out.println("Empty list");
            return;
        }
        if(this.head.getData() == _data) {
            this.head = this.head.getNext();
            this.var = this.head.getNext();
            System.out.println("remove " + _data);
            return;
        }
        while (this.var.getNext() != null) {
            if (this.var.getNext().getData() == _data) {
                this.var.setNext(this.var.getNext().getNext());
                this.var = this.head;
                System.out.println("remove " + _data);
                return;
            }
            this.var = this.var.getNext();
        }
        System.out.println("remove, doens exist");
        this.var = this.head;
    }
    public static void main(String args[]) {
        Node<Integer> N1 = new Node<Integer>(10);
        Node<Integer> N2 = new Node<Integer>(20);
        Node<Integer> N3 = new Node<Integer>(30);
        Node<Integer> N4 = new Node<Integer>(40);
        Node<Integer> N5 = new Node<Integer>(50);

        N1.setNext(N2);
        N2.setNext(N3);
        N3.setNext(N4);
        N4.setNext(N5);

        LinkedList<Integer> Lista = new LinkedList<Integer>(N1);
        Lista.lenght();
        Lista.print();
        Lista.search(50);
        Lista.search(25);
        
        Lista.exist(50);
        Lista.exist(25);
        Lista.remove(40);
        Lista.print();
        Lista.insertFirst(new Node<Integer>(05));
        Lista.print();
        Lista.insertLast(new Node<Integer>(60));
        Lista.print();
        Lista.lenght();
        Lista.reverse();
        Lista.print();
        Lista.destroy();
        Lista.lenght();
    }
}
