package com.pitroq.nomoreforgetting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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

    public static void fillTextNotesListView(View view, FragmentActivity activity, Context context, int listViewId, ArrayList<TextNote> notes, Fragment fragment) {
        ListView notesListView = view.findViewById(listViewId);
        TextNotesListAdapter notesListAdapter = new TextNotesListAdapter(activity, notes);
        notesListAdapter.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.edit_event) {
                Toast.makeText(context, "Edit note: " + item.getContentDescription(), Toast.LENGTH_SHORT).show();
            }
            if (itemId == R.id.delete_event) {
                TextNotes.delete(Integer.parseInt((String) item.getContentDescription()));
                Toast.makeText(context, "Note has been deleted", Toast.LENGTH_SHORT).show();

                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            if (itemId == R.id.toggle_pin_event) {
                Boolean status = TextNotes.togglePinnedStatus(Integer.parseInt((String) item.getContentDescription()));
                assert status != null;
                if (status) {
                    Toast.makeText(context, "Note has been pinned", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Note has been unpinned", Toast.LENGTH_SHORT).show();
                }

                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            return false;
        });
        notesListView.setAdapter(notesListAdapter);
        ListViewUtils.setDynamicHeight(notesListView);
    }

    public static void fillEventNotesListView(View view, FragmentActivity activity, Context context, int listViewId, ArrayList<EventNote> events, Fragment fragment) {
        ListView eventsListView = view.findViewById(listViewId);
        EventNotesListAdapter eventsListAdapter = new EventNotesListAdapter(activity, events);
        eventsListAdapter.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.edit_event) {
                Toast.makeText(context, "Edit event: " + item.getContentDescription(), Toast.LENGTH_SHORT).show();
            }
            if (itemId == R.id.delete_event) {
                EventNotes.delete(Integer.parseInt((String) item.getContentDescription()));
                Toast.makeText(context, "Event has been deleted", Toast.LENGTH_SHORT).show();

                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            if (itemId == R.id.toggle_pin_event) {
                Boolean status = EventNotes.togglePinnedStatus(Integer.parseInt((String) item.getContentDescription()));
                assert status != null;
                if (status) {
                    Toast.makeText(context, "Event has been pinned", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Event has been unpinned", Toast.LENGTH_SHORT).show();
                }

                MainActivity.reloadFragmentLayout(activity, fragment);
            }
            return false;
        });
        eventsListView.setAdapter(eventsListAdapter);
        ListViewUtils.setDynamicHeight(eventsListView);
    }

}
