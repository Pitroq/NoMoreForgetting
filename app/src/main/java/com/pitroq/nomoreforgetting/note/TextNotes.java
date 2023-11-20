package com.pitroq.nomoreforgetting.note;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TextNotes {
    private static ArrayList<TextNote> notes = new ArrayList<>();
    public static String filesDir;

    public static void add(String title, String description) {
        notes.add(new TextNote(notes.size(), title, description));
        saveToFile();
    }

    public static String[] getTitles(ArrayList<TextNote> notes) {
        String[] titles = new String[notes.size()];

        for (int i = 0; i < notes.size(); i++) {
            titles[i] = notes.get(i).title;
        }

        return titles;
    }

    public static ArrayList<TextNote> getPinned() {
        ArrayList<TextNote> pinnedNotes = new ArrayList<>();
        for (TextNote note : notes) {
            if (note.isPinned) {
                pinnedNotes.add(note);
            }
        }
        return pinnedNotes;
    }

    public static ArrayList<TextNote> getAll() {
        return notes;
    }

    public static void delete(int noteId) {
        for (TextNote note : notes) {
            if (note.id == noteId) {
                notes.remove(note);
                saveToFile();
                return;
            }
        }
    }

    public static Boolean togglePinnedStatus(int noteId) {
        for (TextNote note: notes) {
            if (note.id == noteId) {
                note.isPinned = !note.isPinned;
                saveToFile();
                return note.isPinned;
            }
        }
        return null;
    }

    public static void saveToFile() {
        XStream xstream = new XStream();

        File file = new File(filesDir, "notes.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            xstream.toXML(notes, new FileWriter(file));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFromFile() {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);

        File file = new File(filesDir, "notes.xml");
        if (!file.exists()) return;

        ArrayList<TextNote> fileNotes = null;
        try {
            fileNotes = ((ArrayList<?>) xstream.fromXML(new FileReader(file))).stream()
                .filter(TextNote.class::isInstance)
                .map(TextNote.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        notes = fileNotes;
    }
}
