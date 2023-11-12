package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    public HomeFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListViewUtils.fillEventNotesListView(view, getActivity(), getContext(), R.id.event_notes_list_view, EventNotes.getPinned(), this);
        ListViewUtils.fillTextNotesListView(view, getActivity(), getContext(), R.id.text_notes_list_view, TextNotes.getPinned(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}