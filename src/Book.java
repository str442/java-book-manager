/**
 * This class represents a book. It has a title and an author.
 * 
 * @author Guilherme (str442 on GitHub)
 */


public class Book{
    private final String title;
    private final String author;

    /**
     * Creates a book
     * @param title Title of the book
     * @param author Author of the book
     * @requires title != null && author != null
     */
    public Book(String title, String author){
        this.author = author;
        this.title = title;
    }
    /**
     * Returns the author of the book
     * @return the author of the book
     */
    public String getAuthor(){
        return this.author;
    }
    /**
     * Returns the title of the book
     * @return the title of the book
     */
    public String getTitle(){
        return this.title;
    }

    @Override
    public String toString(){
        return "\"" + this.title + "\" by " + this.author;
    }

    
}