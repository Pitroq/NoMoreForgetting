package com.pitroq.nomoreforgetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
                ((TextView) view.findViewById(R.id.text_view)).setText("Events list");
            }
            else if (id == R.id.notes_list_option) {
                ((TextView) view.findViewById(R.id.text_view)).setText("Notes list");
            }
        });

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}