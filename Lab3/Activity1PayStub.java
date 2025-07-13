import java.util.Scanner;
/**
 * Activity1PayStub class is part of Lab 3 and
 * creates a simple pay stub.
 *
 * @author (Katie Schwerer)
 * @version (September 17, 2019)
 */

public class Activity1PayStub
{
    public static final double OVERTIME_RATE = 1.5;
    public static final double SS_RATE = .10;
    public static final double TAX_RATE = .20;
    /**
     * It all starts with the main method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        String name; 
        String ssn;  
        int regHours; 
        int overtimeHours; 
        double hourlyPayRate;

        Scanner keyboard = new Scanner(System.in);

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

        double oTPay = hourlyPayRate * OVERTIME_RATE;
        double oTtotal = overtimeHours * oTPay;
        double regularPay = regHours * hourlyPayRate;
        double overTimePay = overtimeHours * OVERTIME_RATE;
        double grossPay = regularPay + oTtotal;
        double withholding = grossPay * SS_RATE;
        double federalTax = (grossPay - withholding) * TAX_RATE;
        double netPay = grossPay - (withholding + federalTax);

        System.out.print("_____________________________________________"
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
                                                      + "________________");
    }
}
