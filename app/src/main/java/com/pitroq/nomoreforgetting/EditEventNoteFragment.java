package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.pitroq.nomoreforgetting.note.EventNote;
import com.pitroq.nomoreforgetting.note.EventNotes;


public class EditEventNoteFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.getRootView().findViewById(R.id.save_button).setVisibility(View.VISIBLE);
        if (getArguments() == null) {
            return;
        }
        int id = getArguments().getInt("eventNoteId");
        EventNote eventNote = EventNotes.get(id);
        assert eventNote != null;
        ((EditText) view.findViewById(R.id.title)).setText(eventNote.title);
        ((EditText) view.findViewById(R.id.description)).setText(eventNote.description);
        ((EditText) view.findViewById(R.id.date)).setText(eventNote.date);
        ((EditText) view.findViewById(R.id.time)).setText(eventNote.time);

        view.getRootView().findViewById(R.id.save_button).setOnClickListener(v -> {
            String newTitle = ((EditText) view.findViewById(R.id.title)).getText().toString();
            String newDescription = ((EditText) view.findViewById(R.id.description)).getText().toString();
            String newDate = ((EditText) view.findViewById(R.id.date)).getText().toString();
            String newTime = ((EditText) view.findViewById(R.id.time)).getText().toString();

            eventNote.title = newTitle;
            eventNote.description = newDescription;
            eventNote.date = newDate;
            eventNote.time = newTime;

            EventNotes.saveToFile();
        });

        view.findViewById(R.id.select_time_button).setOnClickListener(v -> DateTimePickerUtils.createTimePicker(view, getContext(), R.id.time));
        view.findViewById(R.id.select_date_button).setOnClickListener(v -> DateTimePickerUtils.createDatePicker(view, getContext(), R.id.date));


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_event_note, container, false);
    }
}