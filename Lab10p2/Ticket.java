
/**
 * Write a description of class Ticket here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ticket
{
    private Customer customer;
    private Flight flight;
    
    /**
     * @param customer is the customer
     * @param flight is the flight
     */
    public Ticket(Customer customer, Flight flight)
    {
        this.customer = new Customer(customer);
        this.flight = flight.copy();
    }
    
    /**
     * @return customer
     */
    public Customer getCustomer()
    {
        return new Customer(customer);
    }
    
    /**
     * @return flight
     */
    public Flight getFlight()
    {
        return flight.copy();
    }
    
    /**
     * @param customer is the customer
     */
    public void setCustomer(Customer customer)
    {
        this.customer = new Customer(customer);
    }
    
    /**
     * @param flight is the flight
     */
    public void setFlight(Flight flight)
    {
        this.flight = flight.copy();
    }
    
    /**
     * @return a string
     */
    public String toString()
    {
        String str = "******** TICKET ********\n* Name: " + customer.getName()
                   + "\n* SSN: " + customer.getSsn() + "\n* Flight: " 
                   + flight.getFlightNumber() + "\n* Date: " + flight.getDate()
                   + "\n* Destination: " + flight.getDestination()
                   + "\n************************";
        return str;
    }
}
