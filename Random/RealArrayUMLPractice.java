
/**
 * Write a description of class RealArrayUMLPractice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RealArrayUMLPractice
{
    private double[] testScores;
    
    public RealArrayUMLPractice(double[] scoreArray)
    {
        testScores = scoreArray;
    }
    
    public double getLowestScore()
    {
        double lowest = testScores[0];
        for (int index = 1; index < testScores.length; index++)
        {
            if (testScores[index] < lowest)
            {
                lowest = testScores[index];
            }
        }
        return lowest;
    }
    
    public double getAverage()
    {
        double sum = 0;
        for (int index = 0; index < testScores.length; index++)
        {
            sum += testScores[index];
        }
        double lowest = getLowestScore();
        sum =- lowest;
        return sum / (testScores.length - 1);
    }
}
