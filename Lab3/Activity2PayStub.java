
import java.util.Scanner;
/**
 * Short, one-line description of Activity2PayStub class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (Katie Schwerer) 
 * @version (a version number or a date)
 */
public class Activity2PayStub
{
    public static final double OVERTIME_RATE = 1.5;
    public static final double SS_RATE = .10;
    public static final double TAX_RATE = .20;

    private String name;
    private String ssn;
    private int regHours;
    private int overtimeHours;
    private double hourlyPayRate;
    private double oTPay;
    private double regularPay;
    private double oTtotal;
    private double overTimePay;
    private double grossPay;
    private double withholding;
    private double federalTax;
    private double netPay;
    /**
     * It all starts with the main method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        //Create an Activity2Paystub object
        //a2ps is an Activity2PayStub object
        Activity2PayStub a2ps = new Activity2PayStub();

        //call the methods inside of an Activity2PayStub object
        a2ps.getInput(keyboard);
        a2ps.calculate();
        a2ps.printPayStub();
    }

    /**
     * getInput the Scanner keyboard.
     * 
     * @param keyboard input
     */
    public void getInput(Scanner keyboard)
    {
        // Asking Name
        System.out.print("What is your name? ");
        name = keyboard.nextLine();

        //Asking Social Security Number
        System.out.print("What is your Social Security Number? ");
        ssn = keyboard.nextLine();

        // Asking for Regular Hours
        System.out.print("How many hours do you normally worked? ");
        regHours = keyboard.nextInt();

        // Asking for Overtime Hours
        System.out.print("How many hours did you work overtime? ");
        overtimeHours = keyboard.nextInt();

        // Asking for Hourly Pay Rate
        System.out.print("What is your hourly pay rate? ");
        hourlyPayRate = keyboard.nextDouble();
    }

    /**
     * calculates the values.
     * 
     */
    public void calculate()
    {
        oTPay = hourlyPayRate * OVERTIME_RATE;
        oTtotal = overtimeHours * oTPay;
        regularPay = regHours * hourlyPayRate;
        overTimePay = overtimeHours * OVERTIME_RATE;
        grossPay = regularPay + oTtotal;
        withholding = grossPay * SS_RATE;
        federalTax = (grossPay - withholding) * TAX_RATE;
        netPay = grossPay - (withholding + federalTax);
    }

     /**
     * print out all the data for the person.
     */
    public void printPayStub()
    {
        System.out.print("___________________________________________________"
                                       + "_____________________\n");
        String format = "Name: %-37s SSN: %-11s\n";
        System.out.printf(format, name, ssn);
        String format2 = "Regular Hours: %-8d Reg Rate: $%-8.2f ";
        System.out.printf(format2, regHours, hourlyPayRate);
        String format3 = "Reg Pay: $%-8.2f\n";
        System.out.printf(format3, regularPay);
        String format4 = "Overtime Hours: %-7d OT Rate: $%-9.2f "
                                       + "OT Pay: $%-8.2f\n";
        System.out.printf(format4, overtimeHours, oTPay, oTtotal);
        String format5 = "Gross Pay: $%-8.2f\n";
        System.out.printf(format5, grossPay);
        String format6 = "SS Withholding: $%-8.2f\n";
        System.out.printf(format6, withholding);
        String format7 = "Federal Tax: $%-8.2f\nNet Pay: $%-8.2f";
        System.out.printf(format7, federalTax, netPay);
        System.out.print("\n__________________________________________________"
                                        + "______________________");
    }
}
