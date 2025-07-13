import java.util.Scanner;

public class IntegerDivision {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        int div = sc.nextInt();

        IntTable t = new IntTable(div, amount);

        while(sc.hasNext()) {
            int key = sc.nextInt();
            t.insert(key);
        }

        t.printAmount();
    }






}