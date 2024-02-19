package com.example.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.retrofit.QuoteDataBase;
import com.example.retrofit.fragment.QuotesFragment;
import com.example.retrofit.R;
import com.example.retrofit.fragment.SelectedQuotes;
import com.example.retrofit.service.APIService;
import com.example.retrofit.service.Login;
import com.example.retrofit.service.Root;
import com.example.retrofit.service.UserToken;
import com.google.android.material.tabs.TabLayout;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    static public QuoteDataBase dataBase;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new QuoteDataBase(getApplicationContext());

        Fragment fragment = new SelectedQuotes();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, fragment).commit();

        ((TabLayout) findViewById(R.id.tabLayout)).addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;

                switch (tab.getPosition()){
                    case 0:
                        fragment = new SelectedQuotes();
                        break;
                    case 1:
                        fragment = new QuotesFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        dataBase.closeDataBase();
        super.onDestroy();
    }
}