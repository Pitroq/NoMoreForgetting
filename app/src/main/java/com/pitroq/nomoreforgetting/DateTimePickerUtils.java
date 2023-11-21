package com.pitroq.nomoreforgetting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class DateTimePickerUtils {

    private static String formatTime(int hour, int minute) {
        String hourString = String.valueOf(hour);
        String minuteString = String.valueOf(minute);

        if (hour < 10) {
            hourString = "0" + hour;
        }
        if (minute < 10) {
            minuteString = "0" + minute;
        }

        return hourString + ":" + minuteString;
    }

    private static String formatDate(int year, int month, int day) {
        month += 1;
        String yearString = String.valueOf(year);
        String monthString = String.valueOf(month);
        String dayString = String.valueOf(day);

        if (month < 10) {
            monthString = "0" + month;
        }
        if (day < 10) {
            dayString = "0" + dayString;
        }


        return dayString + "-" + monthString + "-" + yearString;
    }

    public static void createTimePicker(View fragmentView, Context context, int editTextId) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (view, hour, minute) -> {
            ((EditText) fragmentView.findViewById(editTextId)).setText(formatTime(hour, minute));
        }, 0,0, true);
        timePickerDialog.show();
    }

    public static void createDatePicker(View fragmentView, Context context, int editTextId) {
        final Calendar calendar = Calendar.getInstance();
        int presentYear = calendar.get(Calendar.YEAR);
        int presentMonth = calendar.get(Calendar.MONTH);
        int presentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, day) -> {
            ((EditText) fragmentView.findViewById(editTextId)).setText(formatDate(year, month, day));
        }, presentYear, presentMonth, presentDay);
        datePickerDialog.show();
    }

}
