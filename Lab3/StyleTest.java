
// Junit testing framework.
import static org.junit.Assert.fail;  
//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

// Redirecting System.in and System.out
import java.io.PrintStream;            
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

// For checkstyle testing
import java.util.List;      
import java.io.File;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.Checker;
import org.xml.sax.InputSource;
import java.net.URL;


/**
 * StyleTest.java
 *
 * This is an advanced class used to unit test student work for CS 1440 Lab 3.  
 * Students are welcome to view the code as an example of "good" code.  At a 
 * "high level" 1440 students can understand that this code tests the code they 
 * are writing in a "companion" class.  They can see the inputs going into a 
 * test and the results expected.  However, there is much sophisticated stuff 
 * going on "under the hood."  Students should NOT be discouraged if they don't 
 * "get" the details.  
 *
 * @author  Jay Fenwick
 * @version Spring 2014
 */

public class StyleTest
{
    private static final String CHECKER_XML = 
        "http://student2.cs.appstate.edu/classes/JavaCodingStyle/"
        + "cs_appstate2.xml";
    private static final String FILE_TO_TEST = "Style.java";
    private static final int MAX_PRELAB_GRADE = 3;

    //30 seconds max per method tested
    @Rule
    public Timeout globalTimeout = new Timeout(30000);   
    
    private PrintStream oldSystemOut;
    private InputStream oldSystemIn;
    private ByteArrayOutputStream outContent;
    private int checkstyleErrorsAllowed = 0;
    private int grade = 0;

    /**
     * Default constructor initializes fields.
     */
    public StyleTest()
    {
        oldSystemOut = null;
        oldSystemIn = null;
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.  Sets up new I/O streams.
     */
    public void setUp()
    {
        oldSystemIn = System.in;
        oldSystemOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method. 
     */
    public void tearDown()
    {
        resetStreams();
    }
    
    /**
     * Resets I/O streams.
     */
    private void resetStreams() 
    {    
        System.setOut(oldSystemOut);
        System.setIn(oldSystemIn);
    }

    /**
     * Performs a Checkstyle test.
     */
    @Test
    public void testCheckstyle() 
    {
        testPrevious();
     
        testSilent();
        
        // only make it here if haven't done a "fail" so then it's success!
        printGrade(MAX_PRELAB_GRADE);
    }
    
    /**
     * Tests previous activities for this lab exercise.
     */
    public void testPrevious()
    {
    }
    
    /**
     * Silently performs the heart of our testing.
     */
    public void testSilent()
    {
        setUp();
        
        /* Files */
        List<File> files = new ArrayList<File>();
        files.add(new File(FILE_TO_TEST));

        /* Listener */
        ByteArrayOutputStream sos = new ByteArrayOutputStream();
        AuditListener listener = new DefaultLogger(sos, false);

        /* Configuration */
        InputSource inputSource;
        Configuration configuration;
        try 
        {
            URL xmlConfigFile = new URL(CHECKER_XML);
            inputSource = new InputSource(xmlConfigFile.openStream());
            configuration = ConfigurationLoader.loadConfiguration(inputSource, 
                new PropertiesExpander(System.getProperties()), false);
        }
        catch (Exception e) 
        {
            /* Next version should hide all this inside of a userlib jar */
            System.err.println("WARNING: Unable to read online checkstyle "
                + "checks file. Using hardcoded 1/28/14 version of "
                + "cs_appstate2.xml.");

            String xmlConfig = getXmlConfig();

            inputSource = new InputSource(
                            new ByteArrayInputStream(xmlConfig.getBytes()));
            try 
            {
                configuration = ConfigurationLoader.loadConfiguration(
                    inputSource, 
                    new PropertiesExpander(System.getProperties()), false);
            }
            catch (Exception e2)
            {
                System.err.println("WARNING: Unable to configure checkstyle "
                    + "manually for testing.");
                tearDown();
                return;
            }
        }

        /* Create checker */
        Checker checker = null;
        int errors = 0;
        try
        {
            checker = new Checker();
            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(configuration);
            checker.addListener(listener);

            /* Invoke the checkstyle processing. */
            errors = checker.process(files);
            
            /* Clean up */
            checker.destroy();
        }
        catch (Exception e)
        {
            System.err.println("WARNING: Unable to execute checkstyle for"
                + "testing.");
            tearDown();
            return;
        }
        
        if (errors > checkstyleErrorsAllowed) 
        {
            tearDown();
            printGrade(0);
            fail(errors + " check style errors found." + sos.toString());
        }
        
        tearDown();
    }

        
    /**
     * Store the grade then print the report.
     * 
     * @param grade is the point value earned from the tests
     */
    private void printGrade(int grade)    
    {
        this.grade = grade;
        resetStreams();
        System.out.println(gradeReport());
    }    
    
    /**
     * Creates a string with the grades for output.
     * 
     * @return a String containing the grader report
     */
    private String gradeReport()
    {
        String report = "\n____________________________________________\n";
        report += "LAB 3 GRADE:\n";
        report += "PreLab: Quiz: ..... ?? of 7 (consult AsULearn)\n";
        report += "PreLab: StyleTest..  " + grade + " of 3\n";
        report += "Activity 1      ...  0 of 15\n";
        report += "Activity 2      ...  0 of 15\n";
        report += "PostLab         ...  0 of 20\n";
        report += "TotalGrade... " + grade + " of 53\n";
        report += "\nNo test will be graded until the previous tests "
                    + "are passed.\n";        
        report += "For example, Activity1 and subsequent grades will "
                    + "be 0 until StyleTest\n";
        report += "passes all tests.\n";        
                
        return report;
    }   

    /**
     * Local method to build XML for checkstyle.
     * 
     * @return header string for XML file
     */
    private String getXmlHeaderConfig()
    {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        s += "<!DOCTYPE module PUBLIC \"-//Puppy Crawl//DTD Check "
             + "Configuration 1.3//EN\" "
             + "\"http://www.puppycrawl.com/dtds/configuration_1_3.dtd\""
             + ">\n";
        return s;
    }
    
    /**
     * Local method to build XML for checkstyle.
     * 
     * @return checkstyle module definition for magic numbers
     */
    private String getMagicNumbersConfig()
    {
        String s = "<module name=\"MagicNumber\">\n";
        s += "  <property name=\"ignoreNumbers\" value=\"-1,0,1,2,3,4,5,"
            + "6,7,8,9,10,11,12,13,14,15,16,20,32,50,100,212\"/>\n";
        s += "</module>\n";
        
        return s;
    }
    
    /**
     * Local method to build XML for checkstyle.
     * 
     * @return checkstyle module definition for whitespace around operators
     */
    private String getWhitespacecConfig()
    {
        String s = "<module name=\"WhitespaceAfter\"/>\n";
        s += "<module name=\"WhitespaceAround\">\n";
        s += "<property name=\"tokens\" value=\"ASSIGN, BAND, BAND_ASSIGN, "
            + "BOR, BOR_ASSIGN, BSR, BSR_ASSIGN, BXOR, BXOR_ASSIGN, COLON, "
            + "DIV, DIV_ASSIGN, EQUAL, GE, GT, LAND, LCURLY, LE, "
            + "LITERAL_ASSERT, LITERAL_CATCH, LITERAL_DO, LITERAL_ELSE, "
            + "LITERAL_FINALLY, LITERAL_FOR, LITERAL_IF, LITERAL_RETURN, "
            + "LITERAL_SYNCHRONIZED, LITERAL_TRY, LITERAL_WHILE, LOR, LT, "
            + "MINUS, MINUS_ASSIGN, MOD, MOD_ASSIGN, NOT_EQUAL, PLUS, "
            + "PLUS_ASSIGN, RCURLY, SL, SLIST, SL_ASSIGN, SR, SR_ASSIGN, "
            + "STAR, STAR_ASSIGN, TYPE_EXTENSION_AND, QUESTION\"/>\n";
        s += "</module>\n";
        
        return s;
    }
    
    /**
     * Local method to build XML for checkstyle.
     * 
     * @return XML configuration string
     */
    private String getXmlConfig() 
    {
        String s = getXmlHeaderConfig();
        s += "<module name=\"Checker\">\n";
        s += "<module name=\"NewlineAtEndOfFile\">\n";
        s += "  <property name=\"severity\" value=\"warning\"/>\n";
        s += "  <property name=\"lineSeparator\" value=\"lf\"/>\n";
        s += "</module>\n";
        s += "<module name=\"TreeWalker\">\n";
        s += "<module name=\"DeclarationOrder\"/>\n";
        s += "<module name=\"AvoidStarImport\"/>\n";
        s += "<module name=\"RedundantImport\"/>\n";
        s += "<module name=\"UnusedImports\"/>\n";
        s += "<module name=\"Indentation\">\n";
        s += "  <property name=\"caseIndent\" value=\"4\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LineLength\">\n";
        s += "  <property name=\"severity\" value=\"warning\"/>\n";
        s += "  <property name=\"tabWidth\" value=\"4\"/>\n";
        s += "  <property name=\"ignorePattern\" value=\"^$\"/>\n";
        s += "  <property name=\"max\" value=\"80\"/>\n";
        s += "</module>\n";
        s += "<module name=\"JavadocStyle\"/>\n";
        s += "<module name=\"JavadocType\">\n";
        s += "  <property name=\"authorFormat\" value=\"\\S\"/>\n";
        s += "  <property name=\"versionFormat\" value=\"\\S\"/>\n";
        s += "</module>\n";
        s += "<module name=\"JavadocMethod\"/>\n";
        s += "<module name=\"TrailingComment\"/>\n";
        s += "<module name=\"MultipleVariableDeclarations\"/>\n";
        s += "<module name=\"MethodLength\">\n";
        s += "  <property name=\"severity\" value=\"warning\"/>\n";
        s += "  <property name=\"countEmpty\" value=\"false\"/>\n";
        s += "  <property name=\"max\" value=\"100\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LeftCurly\">\n";
        s += "  <property name=\"option\" value=\"nl\"/>\n";
        s += "</module>\n";
        s += "<module name=\"RightCurly\">\n";
        s += "  <property name=\"option\" value=\"alone\"/>\n";
        s += "</module>\n";
        s += "<module name=\"NeedBraces\"/>\n";
        s += "<module name=\"EmptyBlock\"/>\n";
        s += "<module name=\"MissingSwitchDefault\"/>\n";
        s += "<module name=\"DefaultComesLast\"/>\n";
        s += "<module name=\"FallThrough\"/>\n";
        s += "<module name=\"MethodParamPad\"/>\n";
        s += "<module name=\"NoWhitespaceAfter\"/>\n";
        s += "<module name=\"NoWhitespaceBefore\"/>\n";
        s += "<module name=\"OperatorWrap\"/>\n";
        s += "<module name=\"ParenPad\"/>\n";
        s += "<module name=\"TypecastParenPad\"/>\n";
        s += getWhitespacecConfig();
        s += "<module name=\"PackageName\"/>\n";
        s += "<module name=\"TypeName\"/>\n";
        s += "<module name=\"ConstantName\">\n";
        s += "  <property name=\"format\" value=\"^[A-Z][A-Z0-9_]*$\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LocalFinalVariableName\">\n";
        s += "  <property name=\"format\" value=\"^[A-Z][A-Z0-9_]*$\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LocalVariableName\"/>\n";
        s += "<module name=\"MemberName\"/>\n";
        s += "<module name=\"MethodName\"/>\n";
        s += "<module name=\"ParameterName\"/>\n";
        s += "<module name=\"StaticVariableName\"/>\n";
        s += getMagicNumbersConfig();
        s += "<module name=\"SimplifyBooleanReturn\"/>\n";
        s += "</module>\n</module>\n";
        
        return s;
    }
}


