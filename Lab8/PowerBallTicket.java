import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
/**
 * Write a description of class PowerBallTicket here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PowerBallTicket
{
    /**
     * @param args for main.
     * @throws IOException for main.
     */
    public static void main(String[] args) throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("What file do you want to open?");
        String filename = keyboard.nextLine();
        PrintWriter outputFile = new PrintWriter(filename);
        
        System.out.println("How many lottery tickets do you want?");
        int numberOfTickets = keyboard.nextInt();
        
        PowerBall pb = new PowerBall();
        
        
        for (int t = 1; t <= numberOfTickets; t++)
        {
            pb.generateLotteryPicks();
            outputFile.println(pb.toString());
        }
        outputFile.close();
        
    }
}
