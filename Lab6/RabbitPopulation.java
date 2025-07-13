
/**
 * Write a description of class RabbitPopulation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RabbitPopulation
{
    private int initialPopulation;
    private int sixMonthPopulation;

    /**
     * Default Constructor.
     */
    public RabbitPopulation()
    {
        initialPopulation = 0;
        sixMonthPopulation = 0;
    }

    /**
     * @param currentPopulation the current
     * @param futurePopulation the future
     */
    public RabbitPopulation(int currentPopulation, int futurePopulation)
    {
        setInitialPopulation(currentPopulation);
        setSixMonthPopulation(futurePopulation);
    }

    /**
     * @return initialPopulation
     */
    public int getInitialPopulation()
    {
        return initialPopulation;
    }

    /**
     * @return sixMonthPopulation
     */
    public int getSixMonthPopulation()
    {
        return sixMonthPopulation;
    }

    /**
     * @param population to set IP
     */
    public void setInitialPopulation(int population)
    {
        if (population < 0)
        {
            initialPopulation = 0;
        }
        else
        {
            initialPopulation = population;
        }
    }

    /**
     * @param growth for the new population
     */
    public void setSixMonthPopulation(int growth)
    {
        if (growth < 0)
        {
            sixMonthPopulation = 0;
        }
        else
        {
            sixMonthPopulation = growth;
        }
    }

    /**
     * @return percentage
     */
    public double calculateGrowthRate()
    {
        double populationDifference = (double) sixMonthPopulation 
               - (double) initialPopulation;
        double percentage = populationDifference / (double) initialPopulation;
        return percentage;
    }

    /**
     * @return predicted population
     */
    public int calculate12MonthPopulation()
    {
        double populationDifference = sixMonthPopulation - initialPopulation;
        double percentage = populationDifference / initialPopulation;
        double next6Months = percentage * sixMonthPopulation;
        int predictions = (int) ((next6Months + sixMonthPopulation) + .5);
        System.out.printf("%d", predictions);
        return predictions;
    }

}
