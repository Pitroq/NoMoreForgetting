package com.pitroq.nomoreforgetting;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.pitroq.nomoreforgetting.note.EventNote;
import com.pitroq.nomoreforgetting.note.EventNotes;
import com.pitroq.nomoreforgetting.note.TextNote;
import com.pitroq.nomoreforgetting.note.TextNotes;

import java.util.ArrayList;

public class ListViewUtils {
    public static void setDynamicHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = height + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static boolean fillTextNotesListView(View view, FragmentActivity activity, Context context, int listViewId, ArrayList<TextNote> notes, Fragment fragment) {
        if (notes.isEmpty()) {
            return false;
        }

        ListView notesListView = view.findViewById(listViewId);
        TextNotesListAdapter notesListAdapter = new TextNotesListAdapter(activity, notes);
        notesListAdapter.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.edit_event) {
                TextNote textNote = TextNotes.get(Integer.parseInt((String) item.getContentDescription()));
                assert textNote != null;

                Bundle bundle = new Bundle();
                bundle.putInt("textNoteId", Integer.parseInt((String) item.getContentDescription()));
                EditTextNoteFragment editTextNoteFragment = new EditTextNoteFragment();
                editTextNoteFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, editTextNoteFragment).commit();
            }
            if (itemId == R.id.delete_event) {
                TextNotes.delete(Integer.parseInt((String) item.getContentDescription()));
                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            if (itemId == R.id.toggle_pin_event) {
                TextNotes.togglePinnedStatus(Integer.parseInt((String) item.getContentDescription()));
                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            return false;
        });
        notesListView.setAdapter(notesListAdapter);
        ListViewUtils.setDynamicHeight(notesListView);
        return true;
    }

    public static boolean fillEventNotesListView(View view, FragmentActivity activity, Context context, int listViewId, ArrayList<EventNote> events, Fragment fragment) {
        if (events.isEmpty()) {
            return false;
        }

        ListView eventsListView = view.findViewById(listViewId);
        EventNotesListAdapter eventsListAdapter = new EventNotesListAdapter(activity, events);

        eventsListAdapter.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.edit_event) {
                EventNote eventNote = EventNotes.get(Integer.parseInt((String) item.getContentDescription()));
                assert eventNote != null;

                Bundle bundle = new Bundle();
                bundle.putInt("eventNoteId", Integer.parseInt((String) item.getContentDescription()));
                EditEventNoteFragment editEventNoteFragment = new EditEventNoteFragment();
                editEventNoteFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, editEventNoteFragment).commit();
            }
            if (itemId == R.id.delete_event) {
                EventNotes.delete(Integer.parseInt((String) item.getContentDescription()));
                Toast.makeText(context, "Event has been deleted", Toast.LENGTH_SHORT).show();

                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            if (itemId == R.id.toggle_pin_event) {
                EventNotes.togglePinnedStatus(Integer.parseInt((String) item.getContentDescription()));
                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            return false;
        });
        eventsListView.setAdapter(eventsListAdapter);
        ListViewUtils.setDynamicHeight(eventsListView);
        return true;
    }

}
