package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
                ListViewUtils.fillEventNotesListView(view, getActivity(), getContext(), R.id.list_view, EventNotes.getAll(), this);
            }
            else if (id == R.id.notes_list_option) {
                ListViewUtils.fillTextNotesListView(view, getActivity(), getContext(), R.id.list_view, TextNotes.getAll(), this);
            }
        });

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}