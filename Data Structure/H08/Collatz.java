import java.util.Scanner;

public class Collatz
{

    public int length = 0;
    public int array[] = new int[10000001];

    public int collatzLength(int num, int Rnum) {
        if (num ==1) {
            length += 1;
            array[Rnum] = length;
            return length;
        }else if(num < 10000001 && num > 0 && array[num] != 0) {
            length += array[num];
            array[Rnum] += length;
            return length;
        }else if(num % 2 == 0) {
            length += 1;
            return collatzLength(num/2, Rnum); 
        } else {
            length += 1;
            return collatzLength(3 * num + 1, Rnum);
        }
    }

    public static void main(String[] args) {

        Collatz c = new Collatz();

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the range: ");
        int low = s.nextInt();
        int high = s.nextInt();

        int range = high - low;

        int index = 0;
        int biggestLength = 0;
        int l = 0;

        for(int i = low; i <= high; i++) {
                l =  c.collatzLength(i, i);
                if(l > biggestLength) {
                    index  = i;
                    biggestLength = l;
                }
                c.length = 0;
            }

        System.out.println("The number with the maximum Collatz length in the range: " + index);
        System.out.println("Its Collatz length: " + biggestLength);
		
	}
}