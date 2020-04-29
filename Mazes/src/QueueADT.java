//This class is the ADT of the queue object
public interface QueueADT<E> {
  int size();
  boolean isEmpty();
  void enqueue(E e);
  E peek();
  E dequeue();
}

