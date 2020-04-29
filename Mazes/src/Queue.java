
//This class establishes each method for the stack
import java.util.Arrays;

public class Queue<E> implements QueueADT {

	private static int capacity = 400; // I had difficulties expanding the capacity, so I set it to 400
	private E[] queue;

	private int front;
	private int back;
	private int size;

	public Queue() {
		this(capacity);
	}

	public Queue(int capacity) {
		queue = (E[]) new Object[capacity];
		this.capacity = capacity;
		front = back = -1;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enqueue(Object e) {
		if (size == 0) {
			queue[0] = (E) e;
			front = back = 0;
		} else {
			if (size == capacity) {
				expandArray();
			}
			back = (back + 1) % capacity;
			queue[back] = (E) e;
		}
		size++;
	}

	@Override
	public E peek() {
		if (isEmpty())
			return null;
		return queue[front];
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			return null;
		E temp = queue[front];
		queue[front] = null;
		front = (front + 1) % capacity;
		size--;
		return temp;
	}

	private void expandArray() {
		System.out.println("Expanding: current capacity " + capacity);

		E[] temp = (E[]) new Object[2 * capacity];
		int counter = front;
		for (int i = 0; i < size; i++) {
			temp[i] = queue[counter];
			counter = (counter + 1) % capacity;
		}
		queue = temp;
		capacity = 2 * capacity;
		front = 0;
		back = size - 1;
	}

	@Override
	public String toString() {
		return "ArrayQueue [queue=" + Arrays.toString(queue) + "]";
	}
}
