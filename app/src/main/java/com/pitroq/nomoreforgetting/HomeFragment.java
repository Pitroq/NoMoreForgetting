package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pitroq.nomoreforgetting.note.EventNotes;
import com.pitroq.nomoreforgetting.note.TextNotes;

public class HomeFragment extends Fragment {
    public HomeFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if (!ListViewUtils.fillEventNotesListView(view, getActivity(), getContext(), R.id.event_notes_list_view, EventNotes.getPinned(), this)) {
            view.findViewById(R.id.no_pinned_event_notes_info).setVisibility(View.VISIBLE);
        }
        else {
            view.findViewById(R.id.no_pinned_event_notes_info).setVisibility(View.GONE);
        }


        if (!ListViewUtils.fillTextNotesListView(view, getActivity(), getContext(), R.id.text_notes_list_view, TextNotes.getPinned(), this)) {
            view.findViewById(R.id.no_pinned_text_notes_info).setVisibility(View.VISIBLE);
        }
        else {
            view.findViewById(R.id.no_pinned_text_notes_info).setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}