import java.util.Scanner;
/**
 * Write a description of class ISPMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ISPMain
{
    /**
     * @param args in System
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Choose a package from down below");
        System.out.println("Package A: For $9.95 per month providing"
            + " 10 hours of access with $2.00 per additional hours");
        System.out.println("Package B: For $14.95 per month providing"
            + " 20 hours of access with $1.00 per additional hours");
        System.out.println("Package C: For $19.95 per month with "
                       + "unlimited access.\n");

        System.out.println("\nWhat package do you have?");
        String answer = keyboard.nextLine();
        char pack = answer.charAt(0);

        double hours = 0;
        if (pack != 'C')
        {
            System.out.println("\nHow many hours have you used?");
            hours = keyboard.nextDouble();
        }

        ISP isp = new ISP(pack, hours);
        System.out.println(isp.calculateCharges());
        System.out.println(isp.printSavings());
    }
}
