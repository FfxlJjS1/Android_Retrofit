package com.example.retrofit.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofit.R;
import com.example.retrofit.listview.QuoteArrayAdapter;
import com.example.retrofit.service.APIService;
import com.example.retrofit.service.Quote;
import com.example.retrofit.service.Root;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuotesFragment extends Fragment {
    public static final String BASE_URL = "https://favqs.com/api/";

    private ListView listView;

    private static ArrayList<Quote> quotes;
    private ArrayAdapter<Quote> adapter;
    private View inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater.inflate(R.layout.fragment_quotes, container, false);

        listView = this.inflater.findViewById(R.id.listView);

        if(quotes == null) {
            quotes = new ArrayList<>();

            for(int i = 0; i < 10; i++)
                newQuote();
        }

        adapter = new QuoteArrayAdapter(getContext(), R.layout.list_item, quotes);

        newQuote();

        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (listView.getLastVisiblePosition() - listView.getHeaderViewsCount() -
                        listView.getFooterViewsCount()) >= (adapter.getCount()-1)){
                    newQuote();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        // Inflate the layout for this fragment
        return this.inflater;
    }

    private void newQuote(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<Root> rootCall = apiService.loadRoot();
        rootCall.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Quote quote = response.body().quote;

                quotes.add(quote);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}