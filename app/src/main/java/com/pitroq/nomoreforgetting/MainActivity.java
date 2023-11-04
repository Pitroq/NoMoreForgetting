package com.pitroq.nomoreforgetting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home_item) {
                changeFragment(new HomeFragment());
            }
            else if (itemId == R.id.add_new_item) {
                changeFragment(new AddNewFragment());
            }
            else if (itemId == R.id.list_item) {
                changeFragment(new ListFragment());
            }
            return true;
        });
    }

    private void changeFragment(Fragment object) {
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.frameLayout, object)
        .commit();
    }
}