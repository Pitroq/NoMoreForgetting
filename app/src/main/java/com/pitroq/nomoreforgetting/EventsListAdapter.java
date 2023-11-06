package com.pitroq.nomoreforgetting;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class EventsListAdapter extends ArrayAdapter<String> {
    private final Activity activity;
    private final String[] titles;
    private final String[] dates;

    public EventsListAdapter(Activity activity, String[] titles, String[] dates) {
        super(activity, R.layout.event_list_item, titles);
        this.activity = activity;
        this.titles = titles;
        this.dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView != null) {
            itemView = convertView;
        }
        else {
            itemView = activity.getLayoutInflater().inflate(R.layout.event_list_item, parent, false);
        }

        ImageView itemMenu = itemView.findViewById(R.id.item_menu);
        TextView titleText = itemView.findViewById(R.id.title);
        TextView dateText = itemView.findViewById(R.id.date);

        titleText.setText(titles[position]);
        dateText.setText(dates[position]);

        itemMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(activity.getApplicationContext(), v);
            popup.getMenuInflater().inflate(R.menu.list_item_menu, popup.getMenu());

            popup.show();
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.edit_event) {
                    Toast.makeText(getContext(), "Edit position: " + position, Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.delete_event) {
                    Toast.makeText(getContext(), "Delete position: " + position, Toast.LENGTH_SHORT).show();
                }
                return false;
            });
        });
        return itemView;
    }
}