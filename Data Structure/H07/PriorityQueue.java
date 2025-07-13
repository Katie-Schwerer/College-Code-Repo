
public class PriorityQueue {
	private Interval [] heap; // An array that encodes a max-heap data structure.
	private int size;	// The size of allocated buffer for the heap.
	private int numElements;	// The number of elements currently stored. 

	/**
		Constructor: s is the initial size of the heap.
	*/
	public PriorityQueue(int s) {
		size = s;
		heap = new Interval[size + 1];	// 1 extra element allows us to use 1-based indexing. The max heap stores intervals keyed on their lengths.
		numElements = 1;
		heap[0] = new Interval(0, 0);
	}
	
	private void siftup(int i) {
		int parent = parent(i);
        Interval temp = heap[i];
        heap[i] = heap[parent];
        heap[parent] = temp;		
	}
	
	private void siftdownleft(int i) {
		int left = left(i);
		Interval temp = heap[i];
		heap[i] = heap[left];
		heap[left] = temp;
	}
	
	private void siftdownright(int i) {
		int right = right(i);
		Interval temp = heap[i];
		heap[i] = heap[right];
		heap[right] = temp;
	}
	
	private int parent(int i) {
		return i / 2;
	}
	
	private int left(int i) {
		return 2 * i;
	}
	
	private int right(int i) {
		return (2 * i) + 1;
	}
	
	private void heapify(int i) {
		if(heap[i].compareTo(heap[left(i)]) < 0 || heap[i].compareTo(heap[right(i)]) < 0) {
			
			if(heap[left(i)].compareTo(heap[right(i)]) > 0) {
				siftdownleft(i);
				heapify(left(i));
			} else {
				siftdownright(i);
				heapify(right(i));
			}
		}
			
	}
	/**
		Inserts a new Interval k into the heap. Automatically expands the heap if the buffer allocated is full.
	TODO: Please complete this method.
	*/
	public void insert(Interval k) {
		if (numElements == size) {
			// Expand the buffer allocated for the heap to another buffer that is twice as big.
			Interval [] oldheap = heap;
			int oldSize = size;
			
			numElements = 0;
			size = size * 2;
			heap = new Interval[size];
			
			Interval in;
			for(int i = 0; i < oldSize; i++) {
				in = oldheap[i];
				insert(in);
			}
				
		}
		// Insert without buffer expansion here.
		heap[numElements++] = new Interval(k.getStart(), k.getEnd());
		
		
	
	}

	/**
		Returns the maximum Interval from the heap (usually the one with the largest length. See the compareTo function of Interval for more details on the comparison.
	TODO: Please complete this method.
	*/
	public Interval remove_max() {
		if (numElements == 1) {
			return null;
		} // Retuns null if heap is empty.
		// Remove_max code here.
		Interval max = heap[1];
		heap[1] = heap[numElements--];
		
		
		return max; // Replace this statement with returning the max element (root) in the heap.
	}

	/**
		This function prints the contents of the array that encodes a heap.
	*/
	public void print() {
		System.out.println("Printing heap:");
		for (int i = 1; i < numElements; ++i)
			System.out.println(heap[i]);
	}

}
