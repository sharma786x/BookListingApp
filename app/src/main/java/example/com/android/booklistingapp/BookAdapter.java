package example.com.android.booklistingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Sharma786 on 04/05/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listlayout, null, false);
        }

        switch (position) {
            case 0:
                convertView.setBackgroundColor(Color.parseColor("#ffe3f2fd"));
                break;
            case 1:
                convertView.setBackgroundColor(Color.parseColor("#ffbbdefb"));
                break;
            case 2:
                convertView.setBackgroundColor(Color.parseColor("#ff90caf9"));
                break;
            case 3:
                convertView.setBackgroundColor(Color.parseColor("#ff64b5f6"));
                break;
            case 4:
                convertView.setBackgroundColor(Color.parseColor("#ff42a5f5"));
                break;
            case 5:
                convertView.setBackgroundColor(Color.parseColor("#ff2196f3"));
                break;
            case 6:
                convertView.setBackgroundColor(Color.parseColor("#ff1e88e5"));
                break;
            case 7:
                convertView.setBackgroundColor(Color.parseColor("#ff1976d2"));
                break;
            case 8:
                convertView.setBackgroundColor(Color.parseColor("#ff1565c0"));
                break;
            case 9:
                convertView.setBackgroundColor(Color.parseColor("#ff0d47a1"));
                break;
            case 10:
                convertView.setBackgroundColor(Color.parseColor("#ff1a237e"));
                break;
        }

        TextView textView1 = (TextView) convertView.findViewById(R.id.tv1);
        textView1.setText(book.getTitle());

        TextView textView2 = (TextView) convertView.findViewById(R.id.tv2);
        textView2.setText("BY " + book.getAuthor());

        return convertView;
    }


}
