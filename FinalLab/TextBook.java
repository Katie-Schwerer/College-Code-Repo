
/**
 * Write a description of class TextBook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TextBook
{
    private String title;
    private String author;
    private String publisher;

    /**
     * @param title for tile
     * @param author for 
     * @param publisher for
     */
    public TextBook(String title, String author, String publisher)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    /**
     * @param object2 for object.
     */
    public TextBook(TextBook object2)
    {
        title = object2.title;
        author = object2.author;
        publisher = object2.publisher;
    }

    /**
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @return author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * @return publisher
     */
    public String getPublisher()
    {
        return publisher;
    }

    /**
     * @param title for the title of the textbook.
     * @param author for the of the textbook.
     * @param publisher for the publisher.
     */
    public void set(String title, String author, String publisher)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    /**
     * @return the books content.
     */
    public String toString()
    {
        return "Book: " + title + " Author: " + author + " Publisher: " 
                + publisher;
    }
}
