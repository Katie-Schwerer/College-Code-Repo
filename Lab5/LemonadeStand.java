
/**
 * LemonadeStand.java
 * 
 */

//Put any imports below this line.

/**
 * LemonadeStand class here.
 * 
 * Constuctors, fields and Method.
 *
 * @author (Katie Schwerer) 
 * @version (Version 1)
 */
public class LemonadeStand
{
    private int lemons;
    private int gallonsOfWater;
    private int cupsOfSugar;
    private int emptyGlasses;
    private int glassesOfLemonade;
    private double price;
    private double income;

    /**
     * Constructor.
     */
    public LemonadeStand()
    {
        lemons = 0;
        gallonsOfWater = 0;
        cupsOfSugar = 0;
        emptyGlasses = 0;
        glassesOfLemonade = 0;
        price = 0;
        income = 0;
    }

    /**
     * @param initLemon gets lemons
     * @param initGallons gets Gallons
     * @param initCupsOfSugar gets Sugar
     * @param initEmptyGlasses gets Empty
     * @param initPrice gets Price
     */
    public LemonadeStand(int initLemon, int initGallons, int initCupsOfSugar,
           int initEmptyGlasses, double initPrice)
    {
        setLemons(initLemon);
        setGallonsOfWater(initGallons);
        setCupsOfSugar(initCupsOfSugar);
        setEmptyGlasses(initEmptyGlasses);
        setPrice(initPrice);
        glassesOfLemonade = 0;
        income = 0;
    }

    /**
     * @return 1
     */
    public int getLemons()
    {
        return lemons;
    }

    /**
     * @return 1
     */
    public int getGallonsOfWater()
    {
        return gallonsOfWater;
    }

    /**
     * @return 1
     */
    public int getCupsOfSugar()
    {
        return cupsOfSugar;
    }

    /**
     * @return 1
     */
    public int getEmptyGlasses()
    {
        return emptyGlasses;
    }

    /**
     * @return 1
     */
    public int getGlassesOfWater()
    {
        return 1;
    }

    /**
     * @return 1
     */
    public int getGlassesOfLemonade()
    {
        return glassesOfLemonade;
    }

    /**
     * @return 1
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @return 1
     */
    public double getIncome()
    {
        return income;
    }

    /**
     * @param newLemons gets Lemon
     */
    public void setLemons(int newLemons)
    {
        lemons = newLemons;
        if (lemons < 0)
        {
            lemons = 0;
        }
    }

    /**
     * @param newGallonsOfWater gets water
     */
    public void setGallonsOfWater(int newGallonsOfWater)
    {
        gallonsOfWater = newGallonsOfWater;
        if (gallonsOfWater < 0)
        {
            gallonsOfWater = 0;
        }
    }

    /**
     * @param newCupsOfSugar gets Sugar
     */
    public void setCupsOfSugar(int newCupsOfSugar)
    {
        cupsOfSugar = newCupsOfSugar;
        if (cupsOfSugar < 0)
        {
            cupsOfSugar = 0;
        }
    }

    /**
     * @param newEmptyGlasses gets Empty Glasses
     */
    public void setEmptyGlasses(int newEmptyGlasses)
    {
        emptyGlasses = newEmptyGlasses;
        if (emptyGlasses < 0)
        {
            emptyGlasses = 0;
        }
    }

    /**
     * @param newGlassesOfLemonade gets Lemonade
     */
    public void setGlassesOfLemonade(int newGlassesOfLemonade)
    {
        glassesOfLemonade = newGlassesOfLemonade;
        if (glassesOfLemonade < 0)
        {
            glassesOfLemonade = 0;
        }
    }

    /**
     * @param newPrice sets Price
     */
    public void setPrice(double newPrice)
    {
        price = newPrice;
        if (price < 0)
        {
            price = 0;
        }
    }

    /**
     * @param newIncome sets Income
     */
    public void setIncome(double newIncome)
    {
        income = newIncome;
        if (income < 0)
        {
            income = 0;
        }
    }

    /**
     * @return 1
     */
    public int makeLemonade()
    {
        if (lemons >= 6 && gallonsOfWater >= 1 && cupsOfSugar >= 1
            && emptyGlasses >= 8) 
        {
            lemons -= 6;
            gallonsOfWater -= 1;
            cupsOfSugar -= 1;
            emptyGlasses -= 8;
            glassesOfLemonade += 8;
            return glassesOfLemonade;
        }
        else
        {
            return glassesOfLemonade;
        }
    }

    /**
     * @return 1
     */
    public int sellLemonade()
    {
        if (glassesOfLemonade > 0)
        {
            glassesOfLemonade -= 1;
            income += price;
            return 1;
        }
        else
        {
            if (makeLemonade() > 0)
            {
                glassesOfLemonade -= 1;
                income += price;
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

    /**
     * @return 1
     * @param requestedGlasses sell Glasses
     */
    public int sellMoreLemonade(int requestedGlasses)
    {
        if (glassesOfLemonade > 0)
        {
            if (requestedGlasses <= 8 && glassesOfLemonade >= requestedGlasses)
            {
                glassesOfLemonade -= requestedGlasses;
                income += price * requestedGlasses;
                return requestedGlasses;
            }
            else
            {
                if (glassesOfLemonade >= 8 && requestedGlasses > 8)
                {
                    glassesOfLemonade -= 8;
                    income += price * 8;
                    return 8;
                }
                else if (requestedGlasses > glassesOfLemonade)
                {
                    if (makeLemonade() >= 8)
                    {
                        glassesOfLemonade -= requestedGlasses;
                        income += price * requestedGlasses;
                        return requestedGlasses;
                    }
                    else
                    {
                        income += price * glassesOfLemonade;
                        int sold = glassesOfLemonade;
                        glassesOfLemonade = 0;
                        return sold;
                    }
                }
            }
        }
        else if (makeLemonade() > 0)
        {
            glassesOfLemonade -= requestedGlasses;
            income += price * requestedGlasses;
            return requestedGlasses;
        }
        else
        {
            return 0;
        }
        return 0;
    }

    /**
     * @param args main.
     */
    public static void main(String[] args)
    {
        LemonadeStand ls = new LemonadeStand(7, 2, 2, 9, 1.0);
        int cupsMade = ls.makeLemonade();
        int cupsSold = ls.sellLemonade();
        System.out.println(cupsMade);
        System.out.println(cupsSold);
    }
}
