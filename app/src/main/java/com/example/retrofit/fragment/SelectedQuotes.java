package com.example.retrofit.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.retrofit.R;
import com.example.retrofit.activity.MainActivity;
import com.example.retrofit.listview.QuoteArrayAdapter;
import com.example.retrofit.service.Quote;

import java.util.List;

public class SelectedQuotes extends Fragment {
    private View layoutInflater;
    private ListView listViewSelected;
    private List<Quote> quotes;
    private ArrayAdapter<Quote> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater = inflater.inflate(R.layout.fragment_selected_quotes, container, false);

        listViewSelected = layoutInflater.findViewById(R.id.selectedListView);

        quotes = MainActivity.dataBase.getSelectors();

        adapter = new QuoteArrayAdapter(getContext(), R.layout.list_item, this.quotes);

        listViewSelected.setAdapter(adapter);

        return layoutInflater;
    }
}