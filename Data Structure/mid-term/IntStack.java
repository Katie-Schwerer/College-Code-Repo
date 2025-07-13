public class IntStack {
	// May create private data here.
    private final int maxNumber = 50000; //The Max Number for our array
    private int top; // the top of the stack
    private int stack[]; //Array that will add for our stack.

	public IntStack() {
		// TODO: Code to initialize your stack.
        top = -1;
        stack = new int[maxNumber];
	}

	public void push(int x) {
	// TODO: Code to push an item x onto the stack. The stack wlil never contain more than 100 elements.
        top += 1;
        stack[top] = x;
	}

	public int pop() {
		// TODO: Code to pop and retrun an item from the top of the stack. If the stack is empty, return -1.
        if (top < 0) { // If their is nothing in the array
            return -1;
        } 
        else {
            int value = stack[top];
            top -= 1;
            return value;
        }
	}

}