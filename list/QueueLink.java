package list;

public class QueueLink<E> implements Queue<E> {
    private Node<E> first;
    private Node<E> last;
    public QueueLink(){
        this.first = null;
        this.last = null;
    }
    public void enqueue(E x) {
        Node<E> aux = new Node<E>(x);
        if (this.isEmpty())
            this.first = aux;
        else
            this.last.setNext(aux);
        this.last = aux;
    }
    public E dequeue() throws ExceptionIsEmpty {
        if (this.isEmpty()) throw new ExceptionIsEmpty("Queue vacia");
        E data = first.getData();
        first = first.getNext();
        if (first == null) 
            last = null;
        return data;
    }
    public E back() throws ExceptionIsEmpty {
        if (this.isEmpty()) throw new ExceptionIsEmpty("Queue vacia");
        return last.getData();
    }
    public E front() throws ExceptionIsEmpty {
        if (this.isEmpty()) throw new ExceptionIsEmpty("Queue vacia");
        return first.getData();    
    }
    public boolean isEmpty() {
        return first == null;
    }
    @Override
    public String toString(){
        String s = "Queue: ";
        Node<E> var = first;
        while (var != null) {
            s += var.getData() + " ";
            var = var.getNext();
        }
        return s;
    }
}