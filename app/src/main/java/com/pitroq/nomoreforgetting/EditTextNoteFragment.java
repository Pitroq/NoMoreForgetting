package com.pitroq.nomoreforgetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pitroq.nomoreforgetting.note.EventNotes;
import com.pitroq.nomoreforgetting.note.TextNote;
import com.pitroq.nomoreforgetting.note.TextNotes;

public class EditTextNoteFragment extends Fragment {

    public EditTextNoteFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.getRootView().findViewById(R.id.save_button).setVisibility(View.VISIBLE);
        if (getArguments() == null) {
            return;
        }
        int id = getArguments().getInt("textNoteId");
        TextNote textNote = TextNotes.get(id);
        assert textNote != null;
        ((EditText) view.findViewById(R.id.title)).setText(textNote.title);
        ((EditText) view.findViewById(R.id.description)).setText(textNote.description);

        view.getRootView().findViewById(R.id.save_button).setOnClickListener(v -> {
            String newTitle = ((EditText) view.findViewById(R.id.title)).getText().toString();
            String newDescription = ((EditText) view.findViewById(R.id.description)).getText().toString();

            textNote.title = newTitle;
            textNote.description = newDescription;

            TextNotes.saveToFile();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_text_note, container, false);
    }
}