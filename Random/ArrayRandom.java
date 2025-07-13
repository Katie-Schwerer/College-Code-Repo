
/**
 * Write a description of class ArrayRandom here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayRandom
{
    public static void main(String[] args)
    {
        final int ARRAY_SIZE = 5;
        int[] array1 = {2, 4, 6, 8, 10};
        int[] array2 = new int[ARRAY_SIZE];
        
        for (int index = 0; index < array1.length; index++)
        {
            array2[index] = array1[index];
        }
        
        System.out.println("Contents of 1");
        for (int value : array1)
        {
            System.out.print(value + " ");
        }
        System.out.println();
        
        System.out.println("Contents of 2");
        for (int value : array2)
        {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
