package example.com.android.booklistingapp;

/**
 * Created by Sharma786 on 04/05/2017.
 */

public class Book {
    String author;
    String title;


    public Book(String x, String y) {
        title = x;
        author = y;

    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }


}
