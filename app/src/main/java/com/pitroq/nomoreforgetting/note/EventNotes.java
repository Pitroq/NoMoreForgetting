package com.pitroq.nomoreforgetting.note;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EventNotes {
    private static ArrayList<EventNote> events = new ArrayList<>();
    public static String filesDir;

    public static void add(String title, String description, String date, String time) {
        events.add(new EventNote(events.size(), title, description, date, time));
        saveToFile();
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
                saveToFile();
                return;
            }
        }
    }

    public static Boolean togglePinnedStatus(int eventId) {
        for (EventNote event: events) {
            if (event.id == eventId) {
                event.isPinned = !event.isPinned;
                saveToFile();
                return event.isPinned;
            }
        }
        return null;
    }

    public static void saveToFile() {
        XStream xstream = new XStream();

        File file = new File(filesDir, "events.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            xstream.toXML(events, new FileWriter(file));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFromFile() {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);

        File file = new File(filesDir, "events.xml");
        if (!file.exists()) return;
        ArrayList<EventNote> fileEvents = null;
        try {
            fileEvents = ((ArrayList<?>) xstream.fromXML(new FileReader(file))).stream()
                    .filter(EventNote.class::isInstance)
                    .map(EventNote.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        events = fileEvents;
    }
}
