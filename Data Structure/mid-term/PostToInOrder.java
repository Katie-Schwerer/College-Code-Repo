import java.util.Scanner;

public class PostToInOrder {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        IntStack st = new IntStack();
        BinarySearchTree t = new BinarySearchTree();

        while (s.hasNext()) {
			int key = s.nextInt();
            st.push(key);
        }

        int leaf = st.pop();

        while(leaf != -1) {
            if (!t.find(leaf)) {
				t.insert(leaf);
            }
            leaf = st.pop();
        }

        t.print();
    }
}