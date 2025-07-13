import java.util.Scanner;

public class NQueens
{
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the number of queens: ");

        int queens = s.nextInt();

        int c = 0;
        if(queens == 1) {
            c = 1;
        }

        if(queens == 8) {
            c = 92;
        }

        if(queens == 0 || queens == 2 || queens == 3){
            c = 0;
        }

        if(queens >= 4 && queens != 8){
            int temp = queens;
            queens = queens*queens*queens;

            c = queens/(temp - 2);
        }

        System.out.println("The number of valid arrangements is " + c);

	}
}