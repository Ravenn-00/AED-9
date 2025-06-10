package list
;
public interface Queue<E> {
    void enqueue(E x) throws ExceptionIsEmpty;
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
}