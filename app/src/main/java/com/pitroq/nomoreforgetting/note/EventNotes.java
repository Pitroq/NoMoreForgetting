package com.pitroq.nomoreforgetting.note;

import java.util.ArrayList;

public class EventNotes {
    private static final ArrayList<EventNote> events = new ArrayList<>();

    public static void add(String title, String description, String date, String time) {
        events.add(new EventNote(events.size(), title, description, date, time));
    }

    public static String[] getTitles(ArrayList<EventNote> events) {
        String[] titles = new String[events.size()];

        for (int i = 0; i < events.size(); i++) {
            titles[i] = events.get(i).title;
        }
        return titles;
    }

    public static ArrayList<EventNote> getPinned() {
        ArrayList<EventNote> pinnedNotes = new ArrayList<>();
        for (EventNote event : events) {
            if (event.isPinned) {
                pinnedNotes.add(event);
            }
        }
        return pinnedNotes;
    }

    public static ArrayList<EventNote> getAll() {
        return events;
    }

    public static void delete(int eventId) {
        for (EventNote event : events) {
            if (event.id == eventId) {
                events.remove(event);
                return;
            }
        }
    }

    public static Boolean togglePinnedStatus(int eventId) {
        for (EventNote event: events) {
            if (event.id == eventId) {
                event.isPinned = !event.isPinned;
                return event.isPinned;
            }
        }
        return null;
    }
}
