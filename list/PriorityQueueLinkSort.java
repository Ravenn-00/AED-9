package list;

class PriorityQueueLinkSort<E,N> implements PriorityQueue<E,N> {
    class EntryNode{
        E data;
        N priority;
        EntryNode(E data, N priority){
            this.data = data;
            this.priority = priority;
        }
    }
    private Node<EntryNode> first;
    private Node<EntryNode> last;
    public PriorityQueueLinkSort (){
        this.first = null;
        this.last = null;
    }
    public void enqueue(E x, N pr){
    // The list must be ordered by the priority of the elements.
    // The higher the priority, the element is further to the front.
    }
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cannot remove from an empty queue");
        E aux = this.first.getData().data;
        this.first = this.first.getNext();
        if (this.first == null)
            this.last = null;
        return aux;
    }
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("queue vacia");
        }
        return this.last.getData().data;
    }
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("queue vacia");
        }
        return this.first.getData().data;
    }
    public boolean isEmpty() {
        return this.first == null;
    }
    @Override
    public String toString(){
        String s = "Queue: ";
        Node<EntryNode> var = this.first;
        while (var != last) {
            var = var.getNext();
            s += var.getData() + " ";
        }
        return s;
    }
}