package example.com.android.booklistingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        final EditText etSearch = (EditText) findViewById(R.id.etSearchQuery);
        ImageButton searchButton = (ImageButton) findViewById(R.id.searchButton);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = etSearch.getText().toString();
                Intent intent = new Intent(getBaseContext(), DisplayResults.class);
                intent.putExtra("searchQuery", search);
                startActivity(intent);
            }
        });
    }
}
