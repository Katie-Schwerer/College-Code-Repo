
/**
 * Write a description of class Course here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Course
{
    private String courseName;
    private String instructor;
    private TextBook textBook;
    private double[] grades;

    /**
     * @param courseName for name.
     * @param instructor for teacher
     * @param textBook for textbook
     */
    public Course(String courseName, String instructor, TextBook textBook)
    {
        this.courseName = courseName;
        this.instructor = instructor;
        this.textBook = textBook;
    }

    /**
     * @return courseName
     */
    public String getName()
    {
        return courseName;
    }

    /**
     * @return instructor
     */
    public String getInstructor()
    {
        return instructor;
    }

    /**
     * @return textBook
     */
    public TextBook getTextBook()
    {
        return new TextBook(textBook);
    }

    /**
     * @return String text
     */
    public String toString()
    {
        return "Course Name: " + courseName + " Instructor: " + instructor
               + "TextBook: " + textBook.toString();
    }

    /**
     * @param grades is an array
     */
    public void setGrades(double[] grades)
    {
        this.grades = grades;
    }

    /**
     * @return lowest value
     */
    public double getMinimum()
    {
        double lowest = grades[0];
        for (int grad = 1; grad < grades.length; grad++)
        {
            if (lowest > grades[grad])
            {
                lowest = grades[grad];
            }
        }
        return lowest;
    }

    /**
     * @return highest value
     */
    public double getMaximum()
    {
        double highest = grades[0];
        for (int g = 1; g < grades.length; g++)
        {
            if (highest < grades[g])
            {
                highest = grades[g];
            }
        }
        return highest;
    }

    /**
     * @return Average of the grades
     */
    public double getAverage()
    {
        double total = 0;
        for (int index = 0; index < grades.length; index++)
        {
            total += grades[index];
        }
        if (grades.length == 0)
        {
            return 0.0;
        }
        else
        {
            return (total / grades.length);
        }
    }

}
