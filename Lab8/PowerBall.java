import java.util.Random;
/**
 * Write a description of class PowerBall here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PowerBall
{
    public static final int MAX_LOTTERY_NUMBER = 59;
    public static final int MAX_POWER_BALL_NUMBER = 35;

    private Random randomGenerator;
    private int number0;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int powerBall;

    /**
     * PowerBall Constructor created.
     */
    public PowerBall()
    {
        randomGenerator = new Random();
        number0 = 0;
        number1 = 0;
        number2 = 0;
        number3 = 0;
        number4 = 0;
        powerBall = 0;
    }

    /**
     * @return randomGenerator object.
     */
    public Random getRandomGenerator()
    {
        return randomGenerator;
    }

    /**
     * @param random sets to its field.
     */
    public void setRandomGenerator(Random random)
    {
        randomGenerator = random;
    }

    /**
     * @return number0.
     */
    public int getNumber0()
    {
        return number0;
    }

    /**
     * @return number1.
     */
    public int getNumber1()
    {
        return number1;
    }

    /**
     * @return number2.
     */
    public int getNumber2()
    {
        return number2;
    }
    
    /**
     * @return number3.
     */
    public int getNumber3()
    {
        return number3;
    }
    
    /**
     * @return number4.
     */
    public int getNumber4()
    {
        return number4;
    }
    
    /**
     * @return powerBall.
     */
    public int getPowerBall()
    {
        return powerBall;
    }
    
    /** 
     * @param num0 for a number.
     */
    public void setNumber0(int num0)
    {
        number0 = num0;
    }
    
    /** 
     * @param num1 for a number.
     */
    public void setNumber1(int num1)
    {
        number1 = num1;
    }
    
    /** 
     * @param num2 for a number.
     */
    public void setNumber2(int num2)
    {
        number2 = num2;
    }
    
    /** 
     * @param num3 for a number.
     */
    public void setNumber3(int num3)
    {
        number3 = num3;
    }
    
    /** 
     * @param num4 for a number.
     */
    public void setNumber4(int num4)
    {
        number4 = num4;
    }
    
    /** 
     * @param pb for a number.
     */
    public void setPowerBall(int pb)
    {
        powerBall = pb;
    }

    /**
     * @return generate number.
     */
    public int generateLotteryNumber()
    {
        int random = randomGenerator.nextInt(MAX_LOTTERY_NUMBER) + 1;
        return random;
    }

    /**
     * @return generate Power Balls.
     */
    public int generatePowerBallNumber()
    {
        int random = randomGenerator.nextInt(MAX_POWER_BALL_NUMBER) + 1;
        return random;
    }

    /**
     * @return a boolean answer.
     * @param newNumber to check if duplicate.
     */
    public boolean isDuplicateLotteryNumber(int newNumber)
    {
        return (newNumber == number0 || newNumber == number1
            || newNumber == number2 || newNumber == number3 
            || newNumber == number4);
    }

    /**
     * @return int.
     */
    public int nextLotteryNumber()
    {
        int randomNumber = generateLotteryNumber();
        while (isDuplicateLotteryNumber(randomNumber))
        {
            randomNumber = generateLotteryNumber();
        }
        return randomNumber;
    }

    /**
     * reset method.
     */
    public void reset()
    {
        number0 = 0;
        number1 = 0;
        number2 = 0;
        number3 = 0;
        number4 = 0;
        powerBall = 0;
    }

    /**
     * generate lottery picks.
     */
    public void generateLotteryPicks()
    {
        reset();
        
        number0 = nextLotteryNumber();
        number1 = nextLotteryNumber();
        number2 = nextLotteryNumber();
        number3 = nextLotteryNumber();
        number4 = nextLotteryNumber();
        powerBall = generatePowerBallNumber();
    }

    /**
     * @return a string with values.
     */
    public String toString()
    {
        return String.format("%02d, %02d, %02d, %02d, %02d, Powerball %02d",
                             number0, number1, number2, number3, number4, 
                             powerBall);
    }
}
