package com.pitroq.nomoreforgetting.note;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EventNotes {
    private static ArrayList<EventNote> eventNotes = new ArrayList<>();
    public static String filesDir;

    public static void add(String title, String description, String date, String time) {
        eventNotes.add(new EventNote(eventNotes.size(), title, description, date, time));
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
        ArrayList<EventNote> pinnedEventNotes = new ArrayList<>();
        for (EventNote eventNote : eventNotes) {
            if (eventNote.isPinned) {
                pinnedEventNotes.add(eventNote);
            }
        }
        return pinnedEventNotes;
    }

    public static ArrayList<EventNote> getAll() {
        return eventNotes;
    }

    public static void delete(int eventNoteId) {
        for (EventNote event : eventNotes) {
            if (event.id == eventNoteId) {
                eventNotes.remove(event);
                saveToFile();
                return;
            }
        }
    }

    public static void togglePinnedStatus(int eventId) {
        for (EventNote eventNote: eventNotes) {
            if (eventNote.id == eventId) {
                eventNote.isPinned = !eventNote.isPinned;
                saveToFile();
                return;
            }
        }
    }

    public static void saveToFile() {
        XStream xstream = new XStream();

        File file = new File(filesDir, "events.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            xstream.toXML(eventNotes, new FileWriter(file));
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

        eventNotes = fileEvents;
    }

    public static EventNote get(int eventNoteId) {
        for (EventNote eventNote: eventNotes) {
            if (eventNote.id == eventNoteId) {
                return eventNote;
            }
        }
        return null;
    }
}
