
/**
 * Write a description of class FunWithNames here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FunWithNames
{
    public static void main(String[] args)
    {
        String firstName="Donald";
        String middleName="Richard";
        String lastName="Dirka";
        
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
