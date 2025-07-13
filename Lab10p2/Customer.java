
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    private String name;
    private String ssn;
    
    /**
     * @param name of customer.
     * @param ssn of customer.
     */
    public Customer(String name, String ssn)
    {
        this.name = name;
        this.ssn = ssn;
        
        if (name.equals(null))
        {
            this.name = " ";
        }
        else if (ssn.equals(null))
        {
            this.ssn = " ";
        }
        else
        {
            this.name = name;
            this.ssn = ssn;
        }
    }
    
    /**
     * @param customer is part of the Constructor.
     */
    public Customer(Customer customer)
    {
        name = customer.name;
        ssn = customer.ssn;
        
        if (name.equals(null))
        {
            this.name = " ";
        }
        else if (ssn.equals(null))
        {
            this.ssn = " ";
        }
        else
        {
            this.name = customer.name;
            this.ssn = customer.ssn;
        }
    }
    
    /**
     * @return String
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return String
     */
    public String getSsn()
    {
        return ssn;
    }
    
    /**
     * @param name of customer
     */
    public void setName(String name)
    {
        if (name.equals(null))
        {
            this.name = " ";
        }
        else
        {
            this.name = name;
        }
    }
    
    /**
     * @param ssn of customer
     */
    public void setSsn(String ssn)
    {
        if (ssn.equals(null))
        {
            this.ssn = " ";
        }
        else
        {
            this.ssn = ssn;
        }
    }
    
    /**
     * @return String
     */
    public String toString()
    {
        String str = "Name: " + name + " - SSN: " + ssn;
        return str;
    }
}
