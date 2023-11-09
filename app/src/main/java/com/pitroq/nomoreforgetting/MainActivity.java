package com.pitroq.nomoreforgetting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuInflater;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFrameLayout(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home_item) {
                setFrameLayout(new HomeFragment());
            }
            else if (itemId == R.id.add_new_item) {
                setFrameLayout(new AddNewFragment());
            }
            else if (itemId == R.id.list_item) {
                setFrameLayout(new ListFragment());
            }
            return true;
        });
    }

    private void setFrameLayout(Fragment object) {
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.frameLayout, object)
        .commit();
    }

}