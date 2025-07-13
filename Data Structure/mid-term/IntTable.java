public class IntTable {
    private Node [] table;
    private int div;
    private int amount;

    public IntTable(int div, int amount) {
        this.amount = amount;
        table = new Node[amount];
        this.div = div;
    }

    public void insert(int key) {
        int index = hash(key);
        table[index] = new Node(key, table[index]);
    }

    public boolean find(int key) {
        int i = hash(key);
		for (Node curr = table[i]; curr != null; curr = curr.next)
			if (curr.key == key) {
                return true;
            }
		return false;
    }

    public void printAmount() {
        int count = 0;
        int finalCount = 0;
        for (int i = 0; i < amount; i++) {
			for (Node curr = table[i]; curr != null; curr = curr.next) {
				count += 1;
            }
        }

        System.out.println(count);
    }


    private int hash(int key) {
        return key / div;
    }
}