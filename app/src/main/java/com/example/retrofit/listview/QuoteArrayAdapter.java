package com.example.retrofit.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.retrofit.activity.MainActivity;
import com.example.retrofit.service.Quote;
import com.example.retrofit.R;

import java.util.List;

public class QuoteArrayAdapter extends ArrayAdapter<Quote> {
    final LayoutInflater inflater;
    final int layout;
    final List<Quote> quotes;

    public QuoteArrayAdapter(Context context, int resource, List<Quote> quotes){
        super(context, resource, quotes);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.quotes = quotes;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);

        TextView body = view.findViewById(R.id.RetrofitText);
        TextView author = view.findViewById(R.id.authorTextView);
        ImageView likeIm = view.findViewById(R.id.likeImage);

        Quote quote = quotes.get(position);

        body.setText("«"+quote.body+"»");
        author.setText(quote.author);

        if(MainActivity.dataBase.isSelected(quote))
            likeIm.setImageResource(R.drawable.liked);
        else
            likeIm.setImageResource(R.drawable.like);

        likeIm.setOnClickListener(v -> {
            if(!MainActivity.dataBase.isSelected(quote)) {
                MainActivity.dataBase.AddSelected(quote);

                likeIm.setImageResource(R.drawable.liked);
            }
            else{
                MainActivity.dataBase.unselected(quote);

                likeIm.setImageResource(R.drawable.like);
            }
        });

        return view;
    }
}
