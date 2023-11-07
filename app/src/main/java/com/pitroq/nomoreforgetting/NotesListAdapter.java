package com.pitroq.nomoreforgetting;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class NotesListAdapter extends ArrayAdapter<String> {
    private final Activity activity;
    private final String[] titles;
    private final static int listViewLayout = R.layout.note_list_item;
    private final static int listItemMenu = R.menu.list_item_menu;
    private PopupMenu.OnMenuItemClickListener listener;

    public NotesListAdapter(Activity activity, String[] titles) {
        super(activity, listViewLayout, titles);
        this.activity = activity;
        this.titles = titles;
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

        titleText.setText(titles[position]);

        itemMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(activity.getApplicationContext(), v);
            popup.getMenuInflater().inflate(listItemMenu, popup.getMenu());

            popup.getMenu().getItem(0).setContentDescription(String.valueOf(position + 1000));
            popup.getMenu().getItem(1).setContentDescription(String.valueOf(position + 1000));

            popup.setOnMenuItemClickListener(listener);
            popup.show();
        });
        return itemView;
    }

}