package com.pitroq.nomoreforgetting;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.pitroq.nomoreforgetting.note.TextNote;
import com.pitroq.nomoreforgetting.note.TextNotes;

import java.util.ArrayList;

public class TextNotesListAdapter extends ArrayAdapter<String> {
    private final Activity activity;
    private final static int listViewLayout = R.layout.text_note_list_item;
    private final static int listItemMenu = R.menu.list_item_menu;
    private PopupMenu.OnMenuItemClickListener listener;
    private final ArrayList<TextNote> notes;

    public TextNotesListAdapter(Activity activity, ArrayList<TextNote> notes) {
        super(activity, listViewLayout, TextNotes.getTitles(notes));
        this.activity = activity;
        this.notes = notes;
    }

    public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView != null) {
            itemView = convertView;
        }
        else {
            itemView = activity.getLayoutInflater().inflate(listViewLayout, parent, false);
        }

        ImageView itemMenu = itemView.findViewById(R.id.item_menu);
        TextView titleText = itemView.findViewById(R.id.title);

        TextNote textNote = notes.get(position);
        titleText.setText(textNote.title);

        itemMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(activity.getApplicationContext(), v);
            popup.getMenuInflater().inflate(listItemMenu, popup.getMenu());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popup.setForceShowIcon(true);
            }

            popup.getMenu().findItem(R.id.edit_event).setContentDescription(String.valueOf(textNote.id));
            popup.getMenu().findItem(R.id.delete_event).setContentDescription(String.valueOf(textNote.id));
            popup.getMenu().findItem(R.id.toggle_pin_event).setContentDescription(String.valueOf(textNote.id));

            if (textNote.isPinned) {
                popup.getMenu().findItem(R.id.toggle_pin_event).setTitle("Unpin");
                popup.getMenu().findItem(R.id.toggle_pin_event).setIcon(R.drawable.icon_full_star);
            }
            else {
                popup.getMenu().findItem(R.id.toggle_pin_event).setTitle("Pin");
                popup.getMenu().findItem(R.id.toggle_pin_event).setIcon(R.drawable.icon_border_star);
            }

            popup.setOnMenuItemClickListener(listener);
            popup.show();
        });
        return itemView;
    }
}