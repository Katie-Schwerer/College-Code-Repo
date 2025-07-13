
/**
 * Write a description of class ISP here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ISP
{
    private char pkg;
    private double hoursUsed;
    private double providedHours;
    private double additionalHoursCost;
    private double sum;
    private double differenceB;
    private double differenceC;

    /**
     * Default Constructor.
     */
    public ISP()
    {
        pkg = 'A';
        hoursUsed = 0;
    }

    /**
     * Constructor with parameters.
     * 
     * @param userPkg for pkg
     * @param userHoursUsed for hoursUsed
     */
    public ISP(char userPkg, double userHoursUsed)
    {
        pkg = userPkg;
        hoursUsed = userHoursUsed;
    }

    /**
     * @return pkg
     */
    public char getPkg()
    {
        return pkg;
    }

    /**
     * @return hoursUsed
     */
    public double getHoursUsed()
    {
        return hoursUsed;
    }

    /**
     * @param typePKG to test if the value
     */
    public void setPkg(char typePKG)
    {
        pkg = typePKG;
        if (typePKG != 'A' ||  typePKG != 'B' ||  typePKG != 'C')
        {
            System.out.println("Not a valid package.");
        }
    }

    /**
     * @param hours to test the value
     */
    public void setHoursUsed(double hours)
    {
        hoursUsed = hours;
        if (hours < 0)
        {
            System.out.println("Not a valid amount of hours.");
        }
    }

    /**
     * @return sum or number
     */
    public double calculateCharges()
    {
        if (pkg == 'A')
        {
            if (hoursUsed > 10)
            {
                providedHours = hoursUsed - 10;
                additionalHoursCost = providedHours * 2;
                sum = 9.95 + additionalHoursCost;
                return sum;
            }
            else
            {
                return 9.95;
            }
        }
        else if (pkg == 'B')
        {
            if (hoursUsed > 20)
            {
                providedHours = hoursUsed - 20;
                additionalHoursCost = providedHours * 1;
                sum = 14.95 + additionalHoursCost;
                return sum;
            }
            else
            {
                return 14.95;
            }
        }
        else if (pkg == 'C')
        {
            return 19.95;
        }
        else
        {
            System.out.println("Not Valid");
            return 0;
        }
    }

    /**
     * @return String 
     */
    public String printSavings()
    {
        if (pkg == 'A')
        {
            if (hoursUsed > 10)
            {
                if (hoursUsed >= 13 && hoursUsed <= 20)
                {
                    providedHours = hoursUsed - 10;
                    additionalHoursCost = providedHours * 2.;
                    sum = 9.95 + additionalHoursCost;
                    double difference = sum - 14.95;
                    System.out.printf("You would have saved"  
                        + " $%.2f by choosing package B",
                        difference);
                    String message = "";
                    return message;
                }
                else if (hoursUsed > 20 && hoursUsed < 26)
                {
                    providedHours = hoursUsed - 10;
                    additionalHoursCost = providedHours * 2;
                    sum = 9.95 + additionalHoursCost;
                    double pHB = hoursUsed - 20;
                    double aHCB = pHB * 1;
                    double sumB = 14.95 + aHCB;
                    double difference2 = sum - sumB;
                    double difference3 = sum - 19.95;
                    System.out.printf("You would have saved"
                        + " $%.2f by choosing package B", 
                        difference2);
                    System.out.printf("You would have saved" 
                        + " $%.2f by choosing package C", 
                        difference3);
                    String message = "";
                    return message;
                }
                else
                {
                    providedHours = hoursUsed - 10;
                    additionalHoursCost = providedHours * 2;
                    sum = 9.95 + additionalHoursCost;
                    double pHB = hoursUsed - 20;
                    double aHCB = pHB * 1;
                    double sumB = 14.95 + aHCB;
                    double difference2 = sum - sumB;
                    double difference3 = sum - 19.95;
                    System.out.printf("You would have saved "
                        + "$%.2f by choosing package B",
                        difference2);
                    System.out.printf("You would have saved "
                        + "$%.2f by choosing package C",
                        difference3);
                    String message = "";
                    return message;
                }
            }
        }
        else if (pkg == 'B')
        {
            if (hoursUsed > 20)
            { 
                if (hoursUsed >= 26)
                {
                    providedHours = hoursUsed - 20;
                    additionalHoursCost = providedHours * 1;
                    sum = 14.95 + additionalHoursCost;
                    double difference = sum - 19.95;
                    System.out.printf("You would have saved " 
                        + "$%.2f by choosing package C", 
                        difference);
                    String message = "";
                    return message;
                }
            }
        }
        return null;
    }
}
