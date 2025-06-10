package list;

public class PriorityQueueLinked<E> implements PriorityQueue<E, Integer> {
    private QueueLink<E>[] queues;
    private int priorities;
    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int priorities) {
        if (priorities < 0) {
            throw new IllegalArgumentException("prioridad menor 0");
        }
        this.priorities = priorities;
        this.queues = new QueueLink[priorities];
        for (int i = 0; i < priorities; i++) {
            this.queues[i] = new QueueLink<>();
        }
    }
    @Override
    public void enqueue(E x, Integer priority) throws IllegalArgumentException {
        if (priority < 0 || priority >= priorities) {
            throw new IllegalArgumentException("error prioridad");
        }
        queues[priority].enqueue(x);
    }
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = priorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("cola prio vacia");
    }
    @Override
    public E front() throws ExceptionIsEmpty {
        for (int i = priorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].front();
            }
        }
        throw new ExceptionIsEmpty("cola prio vacia");
    }
    @Override
    public E back() throws ExceptionIsEmpty {
        for (int i = 0; i < priorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].back();
            }
        }
        throw new ExceptionIsEmpty("cola prio vacia");
    }
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < priorities; i++) {
            if (!queues[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        String s = "priorityQueue: \n";
        for (int i = priorities - 1; i >= 0; i--) {
            s += "prio " + i + ": " + queues[i].toString() + "\n";
        }
        return s;
    }
}