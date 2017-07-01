package example.com.android.booklistingapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sharma786 on 05/05/2017.
 */

public class JSONStringCreator{
     String x;

    JSONStringCreator(String temp)
    {
        x=temp;
    }
    public  String execute()
    {
        try{
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q="+x);
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String webPage = "", data = "";

            while ((data = reader.readLine()) != null) {
                webPage += data + "\n";
            }
            x = webPage;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }
}
