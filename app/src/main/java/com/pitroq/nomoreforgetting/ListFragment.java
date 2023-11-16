package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.pitroq.nomoreforgetting.note.EventNotes;
import com.pitroq.nomoreforgetting.note.TextNotes;

public class ListFragment extends Fragment {
    public ListFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RadioGroup radioGroup = view.findViewById(R.id.list_type_select);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int id = group.getCheckedRadioButtonId();
            if (id == R.id.events_list_option) {
                boolean isListEmpty = !ListViewUtils.fillEventNotesListView(view, getActivity(), getContext(), R.id.list_view, EventNotes.getAll(), this);
                handleListViewVisibility(view, isListEmpty, R.string.no_event_notes_info);
            }
            else if (id == R.id.notes_list_option) {
                boolean isListEmpty = !ListViewUtils.fillTextNotesListView(view, getActivity(), getContext(), R.id.list_view, TextNotes.getAll(), this);
                handleListViewVisibility(view, isListEmpty, R.string.no_text_notes_info);
            }
        });

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    private void handleListViewVisibility(View view, boolean isListEmpty, int noNotesInfoStringId) {
        if (isListEmpty) {
            view.findViewById(R.id.no_notes_info).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.no_notes_info)).setText(noNotesInfoStringId);
            view.findViewById(R.id.list_view).setVisibility(View.GONE);
        }
        else {
            view.findViewById(R.id.no_notes_info).setVisibility(View.GONE);
            view.findViewById(R.id.list_view).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}