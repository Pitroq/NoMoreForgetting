package com.pitroq.nomoreforgetting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class AddNewFragment extends Fragment {
    public AddNewFragment() {

    }

    private void createTimePicker(View fragmentView) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view, hour, minute) -> {
            String hourString = String.valueOf(hour);
            String minuteString = String.valueOf(minute);
            if (hour == 0) {
                hourString = "00";
            }
            if (minute == 0) {
                minuteString = "00";
            }

            String formated = hourString + ":" + minuteString;
            ((EditText) fragmentView.findViewById(R.id.time)).setText(formated);
        }, 0,0, true);
        timePickerDialog.show();
    }

    private void createDatePicker(View fragmentView) {
        final Calendar calendar = Calendar.getInstance();
        int presentYear = calendar.get(Calendar.YEAR);
        int presentMonth = calendar.get(Calendar.MONTH);
        int presentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year, month, day) -> {
            String formated = day + "-" + (month + 1) + "-" + year;
            ((EditText) fragmentView.findViewById(R.id.date)).setText(formated);
        }, presentYear, presentMonth, presentDay);
        datePickerDialog.show();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.getRootView().findViewById(R.id.save_button).setOnClickListener(v -> {
            System.out.println("SAVING");
        });

        view.findViewById(R.id.select_date_button).setOnClickListener(v -> createDatePicker(view));
        view.findViewById(R.id.select_time_button).setOnClickListener(v -> createTimePicker(view));

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

            }
        });
        spinner.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new, container, false);
    }
}