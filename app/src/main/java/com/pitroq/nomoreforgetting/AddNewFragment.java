package com.pitroq.nomoreforgetting;

import android.icu.text.SymbolTable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class AddNewFragment extends Fragment {
    public AddNewFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Spinner spinner = view.findViewById(R.id.type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.types, android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = (String) parent.getSelectedItem();
                if (selectedType.equals("Event")) {
                    view.getRootView().findViewById(R.id.event_form).setVisibility(View.VISIBLE);
                }
                else if (selectedType.equals("Note")) {
                    view.getRootView().findViewById(R.id.event_form).setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("Nothing selected");
            }
        });
        spinner.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new, container, false);
    }
}