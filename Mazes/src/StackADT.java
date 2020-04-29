//This class is the ADT of the stack object
public interface StackADT<E> {
  int size();
  boolean isEmpty();
  void push(E e);
  E peek();
  E pop();
}

