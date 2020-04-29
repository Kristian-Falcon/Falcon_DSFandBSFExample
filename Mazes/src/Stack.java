//This class establishes each method for the stack
public class Stack<E> implements StackADT<E> {
	private static int capacity = 2;
	private E[] stack; // generic array used for storage
	private int t = -1; // index of the peek element in stack

	// Constructs empty stack
	public Stack() {
		this(capacity);
	}

	// Constructs array stack of a specified capacity
	public Stack(int capacity) {
		stack = (E[]) new Object[capacity];
		this.capacity = capacity;
	}

	@Override
	public int size() {
		return (t + 1);
	}

	@Override
	public boolean isEmpty() {
		return (t == -1);
	}

	// Adds element to the peek of the array
	@Override
	public void push(E e) {
		if (size() == stack.length)
			expandArray();
		stack[++t] = e; // increment t before storing new item
	}
	
	//Expands the array capacity if too many elements are entered
	private void expandArray() {
//		System.out.println("Expanding: current capacity " + capacity); //Used for debugging
		capacity = 2 * capacity;
		E[] temp = (E[]) new Object[capacity];
		for (int i = 0; i < t + 1; i++)
			temp[i] = stack[i];
		stack = temp;
	}

	//Returns the peek element without removing it
	@Override
	public E peek() {
		if (isEmpty())
			return null;
		return stack[t];
	}

	//Removes the peek element
	@Override
	public E pop() {
		if (isEmpty())
			return null;
		E answer = stack[t];
		stack[t] = null; // dereference to help garbage collection
		t--;
		return answer;
	}

	//Prints all elements of the stack 
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		for (int j = t; j >= 0; j--) {
			sb.append(stack[j]);
			if (j > 0)
				sb.append(", ");
		}
		sb.append(")");
		return sb.toString();
	}
}
