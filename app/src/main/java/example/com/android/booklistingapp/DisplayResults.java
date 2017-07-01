package example.com.android.booklistingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayResults extends AppCompatActivity {

    TextView emptyView;
    ArrayList<Book> bookArrayList;
    ListView listView;
    BookAdapter bookAdapter;
    String searchQuery;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.activity_main);

        emptyView = (TextView) findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);

        bookArrayList = new ArrayList<Book>();
        bookAdapter = new BookAdapter(this, bookArrayList);

        Intent intent = getIntent();
        searchQuery = intent.getStringExtra("searchQuery");

        MyTask myTask = new MyTask();
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                myTask.execute();
            } else {
                emptyView.setText(getResources().getString(R.string.noInternet));
                progressBar.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class MyTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            int j = 0;

            try {
                JSONStringCreator jsonStringCreator = new JSONStringCreator(searchQuery);
                JSONObject jsonObject = new JSONObject(jsonStringCreator.execute());
                JSONArray items = jsonObject.getJSONArray("items");
                j = items.length();
                String authors = "", title = "", imageUrl = null;

                for (int k = 0; k < j; k++) {
                    authors = "";
                    JSONObject itemsElements = items.getJSONObject(k);
                    JSONObject volumeInfo = itemsElements.getJSONObject("volumeInfo");

                    title = volumeInfo.getString("title");

                    if (volumeInfo.has("authors")) {
                        JSONArray authorJsonArray = volumeInfo.getJSONArray("authors");
                        for (int z = 0; z < authorJsonArray.length(); z++) {

                            authors = authorJsonArray.getString(z) + "\n" + authors;
                        }

                    } else
                        authors = "unknown";

                    Book book = new Book(title, authors);
                    bookArrayList.add(book);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            listView.setAdapter(bookAdapter);
            emptyView.setText(getResources().getString(R.string.noItems));
            progressBar.setVisibility(View.GONE);

        }
    }
}
