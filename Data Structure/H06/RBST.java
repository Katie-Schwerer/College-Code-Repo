import java.util.Random;

public class RBST {
	
	private Node root;		// Head node of the tree.
	private Random rand;	// A random object - required to randomly insert nodes into the tree.
	private int r = 0;
	
	// Constructors
	public RBST() {
		root = null;
		rand = new Random();
	}
	public RBST(Node _root) {
		root = _root;
		rand = new Random();
	}

	/**
		Wraper print method to print the contents of the tree. Calls the private print method.
	 */
	public void print() {
		print(root);
		System.out.println();
	}
	/**
		Print method to print the contents of the tree.
	*/
	private void print(Node T) {
		// TODO: An inorder traversal to print the sequence.
		if(T == null) return;
		print(T.getLeft());
		System.out.println(T.getTeam());
		print(T.getRight());
	}

	/**
		Wrapper for insertNormal method.
	*/
	public void insertNormal(int team, int rank) {
		root = insertNormal(root, team, rank);
	}
	/**
		Insert the data team at position rank into node T. This is the normal insert routine without any balancing.
	*/
	private Node insertNormal(Node T, int team, int rank) {
		// TODO: Base cases here. Inserting into a null tree.
		if(T == null) {
			return new Node(team);
		}

		assert (rank >= 1 && rank <= T.getSize() + 1) : "rank should be between 1 and size of the tree <" + (T.getSize()+1) + ">";
		
		// TODO:Recursive case. Recursively insert into left tree if rank <= rank of root. Otherwise insert into right tree.
		if(rank < T.getSize()) {
			T.setLeft(insert(T.getLeft(), team, rank));
		} else {
			T.setRight(insert(T.getRight(), team, rank));
		}

		return T;	// Need to return the actual tree. 
	}
	
	/**
		Split the tree at psition rank. It returns RET, a RBST array of length two. RET[0] is the left side of the split,
		and RET[1] is the right side of the split. This is a wrapper method that calls the private split method.
	*/
	public RBST[] split(int rank) {
		Node [] ret = split(root, rank);
		RBST [] RET = {null, null};
		RET[0] = new RBST(ret[0]);
		RET[1] = new RBST(ret[1]);
		return RET;
	}
	/**
		The private split method that splits tree T at position rank. 
		It returns an array ret, of two nodes -- ret[0] is the root of the left tree, and
		ret[1] is the root of the right tree of the split.
	*/
	private Node[] split(Node T, int rank) {
		Node [] ret = {null, null};	// ret[0] is the root node to the left side of the split, ret[1] is the right side.	
		
		// TODO: Fill your code here for the split method. It is easy to implement this recursively.
		// Your base case will be an empty tree. Your recursive case will have three cases -- think 
		// what happens if the rank of the root == rank, or if rank is smaller or larger than the rank
		// of the root.
		if(T == null) {
			return new Node[]{null, null};
		} else if (T.getSize() <= rank) {
			ret = split(T.getRight(), rank);
			T.setRight(ret[0]);
			ret[0] = T;
			return ret;
		} else {
			ret = split(T.getLeft(), rank);
			T.setLeft(ret[1]);
			ret[1] = T;
			return ret;
		}
		
		
	}
	
	/**
		Insert the data team at position rank in the tree. This is a wrapper method that calls the private insert method.
	*/
	public void insert(int team, int rank) {
		root = insert(root, team, rank);
	}
	/**
		The private insert method, that inserts the data team at position rank in the tree rooted at node T. 
		team is inserted at the root with probability 1/(T.getSize()+1). This is done by splitting the tree T
		at position rank-1, creating a new node for team, and attaching the left and right sides of the split as
		the two subtrees of the new node. Otherwise, with probability 1 - 1/(T.getSize()+1), insert recursively
		at either the left tree (rank <= rank of root) or at the right tree (rank > rank of root).
	*/
	private Node insert(Node T, int team, int rank) {
		// TODO: Base case here. Inserting into a null tree.
		if(T == null) {
			return new Node(team);
		}

		assert (rank >= 1 && rank <= T.getSize() + 1) : "rank should be between 1 and size of the tree <" + (T.getSize()+1) + ">";
	
		// TODO:Recursive case. With probability 1 / (T.getSize() + 1), the new node becomes the root. Otherwise
		// recursively insert into left or right subtrees depending upon the rank.
		r = 1 / (T.getSize() + 1);
		if (r == rank) {
			split(rank-1);
			insert(T, team, rank - 1);
		} else if (rank <= T.getSize()) {
			T.setLeft(insert(T.getLeft(), team, rank));
		} else {
			T.setRight(insert(T.getRight(), team, rank));
		}

		return T;	// Need to return the actual tree. 
	}

	/**
		Return the node at position rank in the tree. This is a wrapper method that calls the private select method.
	*/	
	
	
	public Node select(int rank) {
		return select(root, rank);
	}
	/**
		The select method that returns the node in the tree at position rank. 
	*/
	private Node select(Node T, int rank) {
		r = getSize() + 1;
		if(rank == r) {
			return T;
		}
		// TODO: Base case. Return null if the tree is empty.
		

		assert (rank >= 1 && rank <= T.getSize()) : "rank should be between 1 and size of the tree <" + T.getSize() + "> ";
		
		// TODO: Recursive case. Return T if rank is equal to the rank of the root. Else
		// revursively select in either the left tree (rank < rank of root) or the right
		// tree (rank > rank of the root).
		if(rank < r) {
			return select(T.getLeft(), rank);
		} else {
			return select(T.getRight(), rank);
		}
	// Need to change this to return the correct node.
	}

	/**
		Returns the size of the tree.
	*/
	public int getSize() {
		if (root == null) return 0;
		return root.getSize();
	}
}
