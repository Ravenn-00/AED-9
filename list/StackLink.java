package list;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;
    public StackLink() {
        this.top = null;
    }
    @Override
    public void push(E x) {
        Node<E> var = new Node<>(x, top);
        top = var;
    }
    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("stack vacio");
        }
        E data = top.getData();
        top = top.getNext();
        return data;
    }
    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("stack vacio");
        }
        return top.getData();
    }
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    @Override
    public String toString() {
        String s = "stack: ";
        Node<E> var = top;
        while (var != null) {
            s += var.getData() + " ";
            var = var.getNext();
        }
        return s;
    }
}