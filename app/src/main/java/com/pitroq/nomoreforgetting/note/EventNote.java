package com.pitroq.nomoreforgetting.note;

public class EventNote {
    public final int id;
    public String title;
    public Boolean isPinned = false;
    public String description;
    public String date;
    public String time;

    public EventNote(int id, String title, String description, String date, String time) {
        this.id = id + 1000;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }
}
