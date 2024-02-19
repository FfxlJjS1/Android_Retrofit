package com.example.retrofit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.retrofit.service.Quote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteDataBase {
    private final SQLiteDatabase db;

    public QuoteDataBase(Context context) {
        db = context.openOrCreateDatabase("app.db", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Quotes (ID INTEGER PRIMARY KEY, Body VARCHAR(255) NOT NULL, Author VARCHAR(100), DateSelected DATE)");
    }

    public void AddSelected(Quote quote) {
        String date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        }

        db.execSQL("INSERT INTO Quotes VALUES (" + quote.id + ", \"" + quote.body + "\", \"" + quote.author + "\", " + date + ")");
    }

    public List<Quote> getSelectors() {
        List<Quote> quotes = new ArrayList<Quote>();

        Cursor cursor = db.rawQuery("SELECT * FROM Quotes", null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Quote quote = new Quote();

                quote.id = Integer.parseInt(cursor.getString(0));
                quote.body = cursor.getString(1);
                quote.author = cursor.getString(2);
                quote.dateSelected = cursor.getString(3);

                quotes.add(quote);

                cursor.moveToNext();
            }
        }

        cursor.close();

        return quotes;
    }

    public void unselected(Quote quote) {
        db.execSQL("DELETE FROM Quotes WHERE ID=" + quote.id);
    }

    public void closeDataBase() {
        db.close();
    }

    public boolean isSelected(Quote quote) {
        List<Quote> quotes = getSelectors();

        for (Quote arrayQuote : quotes)
            if(arrayQuote.id == quote.id)
                return true;

        return false;
    }
}