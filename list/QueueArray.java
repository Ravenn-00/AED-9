package list;

public class QueueArray<E> implements Queue<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public QueueArray() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E x) throws ExceptionIsEmpty {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size += 1;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("queue vacia");
        }
        E data = first.data;
        first = first.next;
        size -= 1;
        return data;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("queue vacia");
        }
        return first.data;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("queue vacia");
        }
        return last.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String s = "queue: ";
        Node<E> current = first;
        while (current != null) {
            s += current.data + " ";
            current = current.next;
        }
        return s;
    }
}