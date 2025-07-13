
/**
 * Write a description of class Flight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Flight
{
    private int flightNumber;
    private String destination;
    private Pilot pilot;
    private String date;

    /**
     * No-args Constructor.
     */
    public Flight()
    {
        flightNumber = 0;
        destination = " ";
        pilot = new Pilot();
        date = " ";
    }

    /**
     * @param flightNumber is the number of flight
     * @param destination is where the customer going
     * @param pilot is the pilot
     * @param date is the date is going the date
     */
    public Flight(int flightNumber, String destination, Pilot pilot, 
                  String date)
    {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.pilot = pilot.copy();
        this.date = date;
    }

    /**
     * @return a number
     */
    public int getFlightNumber()
    {
        return flightNumber;
    }

    /**
     * @return String
     */
    public String getDestination()
    {
        return destination;
    }

    /**
     * @return a new pilot
     */
    public Pilot getPilot()
    {
        return pilot.copy();
    }

    /**
     * @return a date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * @param flightNumber of the flight
     */
    public void setFlightNumber(int flightNumber)
    {
        if (flightNumber < 0)
        {
            this.flightNumber = 000;
        }
        else
        {
            this.flightNumber = flightNumber;
        }
    }

    /**
     * @param destination of customer
     */
    public void setDestination(String destination)
    {
        if (destination.equals(null))
        {
            this.destination = " ";
        }
        else
        {
            this.destination = destination;
        }
    }

    /**
     * @param pilot of the flight
     */
    public void setPilot(Pilot pilot)
    {
        this.pilot = pilot.copy();
    }

    /**
     * @param date of the flight
     */
    public void setDate(String date)
    {
        if (date.equals(null))
        {
            this.date = " ";
        }
        else
        {
            this.date = date;
        }
    }

    /**
     * @return String
     */
    public String toString()
    {
        String str = "Flt#: " + flightNumber + "\tDest: " + destination
            + "\tPlt: " + pilot.getName() + "\tDate: "
            + date;
        return str;
    }

    /**
     * @return Flight
     */
    public Flight copy()
    {
        pilot.copy();
        return new Flight(flightNumber, destination, pilot, date);
    }
}
