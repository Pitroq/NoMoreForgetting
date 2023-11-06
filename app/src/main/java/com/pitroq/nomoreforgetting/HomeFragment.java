package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

public class HomeFragment extends Fragment {
    public HomeFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String[] eventTitles = { "First event", "Second event", "Third event", "Fourth event", "Fifth event", "Sixth event" };
        String[] eventDates = { "12.10.2023", "13.10.2023", "14.10.2023", "15.10.2023", "16.10.2023", "17.10.2023" };
        String[] noteTitles = { "First note", "Second note", "Third note", "Fourth note", "Fifth note", "Sixth note" };

        ListView eventsListView = view.findViewById(R.id.events_list_view);
        EventsListAdapter eventsListAdapter = new EventsListAdapter(getActivity(), eventTitles, eventDates);
        eventsListView.setAdapter(eventsListAdapter);
        ListUtils.setDynamicHeight(eventsListView);

        ListView notesListView = view.findViewById(R.id.notes_list_view);
        NotesListAdapter notesListAdapter = new NotesListAdapter(getActivity(), noteTitles);
        notesListView.setAdapter(notesListAdapter);
        ListUtils.setDynamicHeight(notesListView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}