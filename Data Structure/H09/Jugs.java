import java.util.Scanner; 

public class Jugs
{
    public int A;
    public int B;
    public int C;
    public boolean visited[][];
	public String print[];

    public Jugs(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
        visited = new boolean[C][C];
		print = new String[7];
    }

    public int fill1(int max) {
        max = A;
        return max;
    }

    public int j1;
    public int j2;

    public void pour(int jug1, int jug2) {
        while (jug1 != 0 || jug2 != B) {
            jug2 += 1;
            jug1 -= 1;
        }

        j1 = jug1;
        j2 = jug2;
    }

    public int empty(int jug2) {
        return 0;
    }
    
    
    public boolean isValid(int a, int b, int c) {

        if(a > c || b > c) {
            return false;
        } else if(a + b < c) {
            return false;
        } else if(c == 0 || a == 0 || b == 0) {
            return false;
        } else if(b == a) {
            return false;
        }

        return true;

    }

    public void dfs(int a, int b, int c) {
        int jug1 = 0;
        int jug2 = 0;

        jug1 = fill1(a);
        print[0] = "[a = " + jug1 + ", b = " + jug2 + "]";


        pour(jug1, jug2);
        jug2 = j2;
        jug1 = j1;
        print[1] = "[a = " + jug1 + ", b = " + jug2 + "]";

        if(b > a) {
            jug1 = fill1(a);
            print[2] = "[a = " + jug1 + ", b = " + jug2 + "]";

            pour(jug1, jug2);
            jug2 = j2;
            jug1 = j1;
            print[3] = "[a = " + jug1 + ", b = " + jug2 + "]";

            jug2 = empty(0);
            print[4] = "[a = " +  jug1 + ", b = " + jug2 + "]";

            pour(jug1, jug2);
            jug2 = j2;
            jug1 = j1;
            print[5] = "[a = " + jug1 + ", b = " + jug2 + "]";

            jug1 = fill1(a);
            print[6] = "[a = " + jug1 + ", b = " + jug2 + "]";
        } else {
            jug2 = empty(0);
            print[2] = "[a = " +  jug1 + ", b = " + jug2 + "]";

            pour(jug1, jug2);
            jug2 = j2;
            jug1 = j1;
            print[3] = "[a = " + jug1 + ", b = " + jug2 + "]";

            jug1 = fill1(a);
            print[4] = "[a = " + jug1 + ", b = " + jug2 + "]";
        }

    }
	
	public void print() {
		for(int i = 0; i < 7; i++) {
			System.out.println(print[i]);
		}
	}

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter A: ");
        int A = s.nextInt();
        System.out.println("Enter B: ");
        int B = s.nextInt();
        System.out.println("Enter C: ");
        int C = s.nextInt();

        Jugs j =  new Jugs(A, B, C);

        if(!(j.isValid(A, B, C))) {
            System.out.println("Impossible!");
        } else {
            System.out.println("Yay! Found a solution.");
            j.dfs(A, B, C);
        }
		
	}
}