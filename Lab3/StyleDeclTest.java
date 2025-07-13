/**
 * StyleDeclTest.java
 * 
 * This file contains the single class StyleDeclTest that is used to verify
 * student code for the Lab 03 Prelab activity.
 */

/* JUnit testing needs these. */
import static org.junit.Assert.fail;  
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;


/**
 * The test class StyleDeclTest.
 *
 * This is an advanced class used to unit test student work for CS 1440 Lab 3.  
 * Students are welcome to view the code as an example of "good" code.  At a 
 * "high level" 1440 students can understand that this code tests the code they
 * are writing in a "companion" class.  They can see the inputs going into a 
 * test and the results expected.  However, there is much sophisticated stuff 
 * going on "under the hood."  Students should NOT be discouraged if they 
 * don't "get" the details.  
 *
 * @author  Jay Fenwick
 * @version Spring 2014
 */

public class StyleDeclTest
{
    private static final String CLASS_TO_TEST = "Style";
    private Class c = null;
    
    /**
     * Default constructor.
     */
    public StyleDeclTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        try
        {
            c = Class.forName(CLASS_TO_TEST);
        }
        catch (Exception e) 
        {
            c = null;
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test the expected method declarations.  
     * 
     * We are only interested in the main method for now.
     */
    @Test
    public void testStyleDeclarations()
    {
        testSilent();
    }
    
    /**
     * Silently (no output) performs test.  Failure causes JUnit failure.  
     * Success just returns to caller who ultimately is an @Test Junit test.
     */
    public void testSilent()
    {
        if (c == null) 
        {
            fail("StyleDeclTest.: "
                 + "unable to perform test. Did you name the class correctly?");
        }
        
        Method[] methods = c.getDeclaredMethods();
        
        // main method
        boolean proper = false;
        
        // static=0x08 public=0x01
        int expectedMods = 0x09;
        for (Method m : methods) 
        {
            int methodMods = m.getModifiers() & expectedMods;
            String rtnType = m.getReturnType().toString();
            Class<?>[] paramTypes = m.getParameterTypes();
            if (methodMods == expectedMods 
                && "main".equals(m.getName()) 
                && "void".equals(rtnType) 
                && paramTypes.length == 1 
                && "class [Ljava.lang.String;".equals(paramTypes[0].toString()))
            {
                proper = true;
            }
        }
        if (!proper)
        {
            fail("StyleDeclTest: "
                 + "main method missing or declared improperly.");
        }
    }
}



