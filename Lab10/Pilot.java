
/**
 * Write a description of class Pilot here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pilot
{
    private String name;
    private String license;

    /**
     * Constructor.
     */
    public Pilot()
    {
        name = "";
        license = "";
    }
    
    /**
     * @param name and 
     * @param license for pilot.
     */
    public Pilot(String name, String license)
    {
        this.name = name;
        this.license = license;
        
        if (name.equals(null))
        {
            name = "";
        }
        else if (license.equals(null))
        {
            license = "";
        }
        else
        {
            this.name = name;
            this.license = license;
        }
    }

    /**
     * set name.
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return license
     * set licesne.
     */
    public String getLicense()
    {
        return license;
    }

    /**
     * @param name to help with name.
     */
    public void setName(String name)
    {
        if (name == (null))
        {
            this.name = "";
        }
        else
        {
            this.name = name;
        }
    }
    
    /**
     * @param license to help.
     */
    public void setLicense(String license)
    {
        if (license == null)
        {
            this.license = "";
        }
        else
        {
            this.license = license;
        }
    }
    
    /**
     * @return object
     */
    public Pilot copy()
    {
        Pilot pilot1 = new Pilot(name, license);
        return pilot1;
    }
    
    /**
     * @return string
     * to print out string.
     */
    public String toString()
    {
        String id = "Name: " + name + " - License: " + license;
        return id;
    }
    
    /**
     * @return boolean
     * @param pilot is an object.
     */
    public boolean equals(Pilot pilot)
    {
        return pilot.name.equals(name) && pilot.license.equals(license);
    }
}
