package com.pitroq.nomoreforgetting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pitroq.nomoreforgetting.note.EventNotes;
import com.pitroq.nomoreforgetting.note.TextNotes;

import java.util.Calendar;

public class AddNewFragment extends Fragment {
    public AddNewFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.getRootView().findViewById(R.id.save_button).setVisibility(View.VISIBLE);

        Spinner spinner = view.findViewById(R.id.type);

        view.getRootView().findViewById(R.id.save_button).setOnClickListener(v -> {
            String type = spinner.getSelectedItem().toString();
            String title = ((EditText) view.findViewById(R.id.title)).getText().toString();
            String description = ((EditText) view.findViewById(R.id.description)).getText().toString();
            if (type.equals("Note")) {
                TextNotes.add(title, description);
            }
            else {
                String date = ((EditText) view.findViewById(R.id.date)).getText().toString();
                String time = ((EditText) view.findViewById(R.id.time)).getText().toString();
                EventNotes.add(title, description, date, time);
            }

            ((BottomNavigationView) view.getRootView().findViewById(R.id.bottom_navigation_view)).setSelectedItemId(R.id.home_item);

        });

        view.findViewById(R.id.select_time_button).setOnClickListener(v -> DateTimePickerUtils.createTimePicker(view, getContext(), R.id.time));
        view.findViewById(R.id.select_date_button).setOnClickListener(v -> DateTimePickerUtils.createDatePicker(view, getContext(), R.id.date));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.noteTypes, android.R.layout.simple_spinner_dropdown_item);
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

            }
        });
        spinner.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new, container, false);
    }
}