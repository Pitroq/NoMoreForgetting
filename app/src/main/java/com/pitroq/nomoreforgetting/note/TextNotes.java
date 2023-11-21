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

public class TextNotes {
    private static ArrayList<TextNote> textNotes = new ArrayList<>();
    public static String filesDir;

    public static void add(String title, String description) {
        textNotes.add(new TextNote(textNotes.size(), title, description));
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
        ArrayList<TextNote> pinnedTextNotes = new ArrayList<>();
        for (TextNote textNote : textNotes) {
            if (textNote.isPinned) {
                pinnedTextNotes.add(textNote);
            }
        }
        return pinnedTextNotes;
    }

    public static ArrayList<TextNote> getAll() {
        return textNotes;
    }

    public static void delete(int noteId) {
        for (TextNote note : textNotes) {
            if (note.id == noteId) {
                textNotes.remove(note);
                saveToFile();
                return;
            }
        }
    }

    public static void togglePinnedStatus(int noteId) {
        for (TextNote textNote: textNotes) {
            if (textNote.id == noteId) {
                textNote.isPinned = !textNote.isPinned;
                saveToFile();
                return;
            }
        }
    }

    public static void saveToFile() {
        XStream xstream = new XStream();

        File file = new File(filesDir, "notes.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            xstream.toXML(textNotes, new FileWriter(file));
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

        textNotes = fileNotes;
    }

    public static TextNote get(int textNoteId) {
        for (TextNote textNote: textNotes) {
            if (textNote.id == textNoteId) {
                return textNote;
            }
        }
        return null;
    }
}
