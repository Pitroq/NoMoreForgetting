package com.pitroq.nomoreforgetting.note;

public class TextNote {
    public int id;
    public String title;
    public Boolean isPinned = false;
    public String description;

    public TextNote(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
