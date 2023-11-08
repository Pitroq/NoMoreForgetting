package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    public ListFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RadioGroup radioGroup = view.findViewById(R.id.list_type_select);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int id = group.getCheckedRadioButtonId();
            if (id == R.id.events_list_option) {
                String[] eventTitles = { "First event", "Second event", "Third event", "Fourth event", "Fifth event", "Sixth event" };
                String[] eventDates = { "12.10.2023", "13.10.2023", "14.10.2023", "15.10.2023", "16.10.2023", "17.10.2023" };
                ListView eventsListView = view.findViewById(R.id.list_view);
                EventsListAdapter eventsListAdapter = new EventsListAdapter(getActivity(), eventTitles, eventDates);
                eventsListAdapter.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.edit_event) {
                        Toast.makeText(getContext(), "Edit event: " + item.getContentDescription(), Toast.LENGTH_SHORT).show();
                    }
                    if (itemId == R.id.delete_event) {
                        Toast.makeText(getContext(), "Delete event: " + item.getContentDescription(), Toast.LENGTH_SHORT).show();
                    }
                    return false;
                });
                eventsListView.setAdapter(eventsListAdapter);
                ListUtils.setDynamicHeight(eventsListView);
            }
            else if (id == R.id.notes_list_option) {
                String[] noteTitles = { "First note", "Second note", "Third note", "Fourth note", "Fifth note", "Sixth note" };
                ListView notesListView = view.findViewById(R.id.list_view);
                NotesListAdapter notesListAdapter = new NotesListAdapter(getActivity(), noteTitles);
                notesListAdapter.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.edit_event) {
                        Toast.makeText(getContext(), "Edit note: " + item.getContentDescription(), Toast.LENGTH_SHORT).show();
                    }
                    if (itemId == R.id.delete_event) {
                        Toast.makeText(getContext(), "Delete note: " + item.getContentDescription(), Toast.LENGTH_SHORT).show();
                    }
                    return false;
                });
                notesListView.setAdapter(notesListAdapter);
                ListUtils.setDynamicHeight(notesListView);
            }
        });

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}