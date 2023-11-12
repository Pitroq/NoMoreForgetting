package com.pitroq.nomoreforgetting;

import java.util.ArrayList;

public class TextNotes {
    private static final ArrayList<TextNote> notes = new ArrayList<>();

    public static void add(String title, String description) {
        notes.add(new TextNote(notes.size(), title, description));
    }

    public static String[] getTitlesFrom(ArrayList<TextNote> notes) {
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
                return;
            }
        }
    }

    public static Boolean togglePinnedStatus(int noteId) {
        for (TextNote note: notes) {
            if (note.id == noteId) {
                note.isPinned = !note.isPinned;
                return note.isPinned;
            }
        }
        return null;
    }
}
