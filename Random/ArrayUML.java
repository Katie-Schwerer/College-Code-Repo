
/**
 * Write a description of class ArrayUML here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayUML
{
    public static void main(String[] args)
    {
        final int SIZE = 8;
        float[] y = new float[4];
        double[] z = new double[7];
        long[] w = new long[5];
        char[] u = new char[SIZE];
        
        int[] x = {4 , 56, 45, 67, 8, 109};
        
        for(int index = 0; index < x.length; index++)
        {
            System.out.println("The index " + index + " has " + x[index] + " in it.");
        }
        
        int[] y2 = new int[6];
        for(int i = 0; i < x.length; i++)
        {
            y2[i] = x[i];
        }
        
        for (int index : y2)
        {
            System.out.print(index + " ");
        }
        System.out.println();
        
        int highest = x[0];
        int lowest = x[0];
        
        for (int index = 1; index < x.length; index++)
        {
            if (x[index] < lowest)
            {
                lowest = x[index];
            }
            else if ( x[index] > highest)
            {
                highest = x[index];
            }
        }
        
        System.out.println(lowest + " " + highest);
    }
}
