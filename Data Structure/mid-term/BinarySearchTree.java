public class BinarySearchTree {

    private LeafNode root; //Top of the tree
    private int height = 0;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        root = insert(root,key);
    }

    public LeafNode insert(LeafNode T, int key) {
        if(T == null) return new LeafNode(key);

        if (key <= T.value) {
            T.left = insert(T.left, key);
        } else {
            T.right =  insert(T.right, key);
        }
        return T;
    }

    public boolean find(int key) {
        return find(root, key);
    }

    private boolean find(LeafNode T, int key) {
        if(T == null) {
            return false;
        } if (T.value == key) {
            return true;
        } if (key < T.value) {
            return find(T.left, key);
        } 

        return find(T.right, key);
    }

    public void print() {
        print(root);
    }

    private int count = 0;

    public void print(LeafNode r) {
        if(r == null) return;
        print(r.left);
        System.out.println(r.value);
        print(r.right);
    }


}