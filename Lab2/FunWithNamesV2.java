
/**
 * Write a description of class FunWithNamesV2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FunWithNamesV2
{
    public static void main(String[] args)
    {
        System.out.println("Enter your first name: ");
        String firstName = Given.getString();
        
        System.out.println("Enter your first name: ");
        String middleName = Given.getString();
        
        System.out.println("Enter your first name: ");
        String lastName = Given.getString();
        
        String fn=firstName.toLowerCase();
        String mn=middleName.toLowerCase();
        
        String fullName=firstName+" "+middleName+" "+lastName;
        int characterCount=firstName.length()+middleName.length()+lastName.length();
        String login=lastName.toLowerCase()+fn.charAt(0)+mn.charAt(0);
        
        System.out.println("Name: "+fullName);
        System.out.println("Number of characters in full name: "+characterCount);
        System.out.println("Login id: "+login);

    }
}
