public class BST {
	private Node root; // The root node of the tree.
    private Node pre;
    private Node suc;

	public BST() {
		root = null;
	}

	/**
		Inserts a time, along with the req_id. The tree is keyed on time, while req_id provides a pointer to the request.
		This is a public wrapper function that calls the recursive insert method.
		Note that the insert method should return the root node of the subtree in which we insert the key (and its value).
	**/
	public void insert(int time, int req_index) {
		root = insert(time, req_index, root);
	}

    private Node insert(int time, int req_index, Node T) {
        if (T == null) {
            return new Node(time, req_index);
        }

        if(time <= T.getTime()) {
            Node left = T.getLeft();
            T.setLeft(insert(time, req_index, left));
        } else {
            Node right = T.getRight();
            T.setRight(insert(time, req_index, right));
        }

        return T;
    }

	/**
		Returns a pointer to the Node that is the predecessor of time. The predecessor element is the largest
		element within the tree that is smaller or equal to time. This is the deepest ancestor with this property.
		Please return the predecessor element. You may return null if time does not have a predecessor.
	**/
	public Node pred(int time) {
		// TODO: code for pred here.
        return pred(root, pre, time);  // Replace this line with returning the actual predecessor.
	}

    private Node pred(Node T, Node P, int time) {
        if(T == null) {
            return P;
        }

        if(T.getTime() == time) {
            if(T.getLeft() != null) {
                return max();
            }
        } else if(time < T.getTime()) {
            return pred(T.getLeft(), P, time);
        } else {
            P = T;
            return pred(T.getRight(), P, time);
        }

        return P;
    }

	/**
		Returns a pointer to the Node that is the successor of time. The successor element is the largest
		element within the tree that is larger or equal to time. This is the deepest ancestor with this property.
		Please return the successor element. You may return null if time does not have a successor.
	**/
	public Node succ(int time) {
		// TODO: code for succ here.
		return succ(root, suc, time); // Replace this line with returning the actual successor.
	}

    private Node succ(Node T, Node succ, int time) {
        if(root == null) {
            return succ;
        }

        if(root.getTime() == time) {
            if(root.getRight() != null) {
                return min();
            }
        } else if(time > T.getTime()) {
            return succ(root.getRight(), succ, time);
        } else {
            succ = root;
            return succ(root.getLeft(), succ, time);
        }
        return succ;
    }

	/**
		Returns the minimum element in the binary search tree or null if the tree is empty.
	**/
	public Node min() {
		// TODO: Code for min here.
        Node min = root.getLeft();
        while(min != null) {
            min = root.getLeft();
        }

		return min; // Replace this line with returning the actual minimum.
	}

	/**
		Returns the maximum element in the binary search tree or null if the tree is empty.
	**/
	public Node max() {
		// TODO: Code for max here.
        Node max = root.getRight(); 
        while(max != null) {
            max = root.getRight();
        }
		return max; // Replace this line with returning the actual maximum.
	}

	/**
		Remove the node that contains this time. If this time is not present in the structure, this method does nothing.
	**/
	public void delete(int time) {
		// TODO: Code for delete here.
        root = delete(time, root);
	}

    private Node delete(int time, Node T) {

        if(T == null) {
            return T;
        }

        if(time < T.getTime()) {
            Node left = T.getLeft();
            T.setLeft(delete(time, left));
        } else if(time > T.getTime()) {
            Node right = T.getRight();
            T.setRight(delete(time, right));
        } else {
            if(T.getLeft() == null) {
                return T.getRight();
            }
            else if (T.getRight() == null) {
                return T.getLeft();
            }

            int value = min().getTime();

            Node right = T.getRight();

            T.setRight(delete(value, right));

        }

        return T;

    }
	
	public boolean find(int time) {
        return find(root, time);
    }

    private boolean find(Node T, int time) {
        if(T == null) {
            return false;
        } if (T.getTime() == time) {
            return true;
        } if (time < T.getTime()) {
            return find(T.getLeft(), time);
        } 

        return find(T.getRight(), time);
    }

	/**
		Prints the contents of the tree in sorted order.
	**/
	public void print() {
		// TODO: Code for print here.
        print(root);
	}

    private void print(Node r) {
        if(r == null) return;

        print(r.getLeft());
        System.out.println(r.toString());
        print(r.getRight());
    }
	
}