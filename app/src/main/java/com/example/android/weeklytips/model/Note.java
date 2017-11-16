package com.example.android.weeklytips.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.text.DateFormat;
import java.util.Date;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteId;
    @ColumnInfo()
    private Date noteDate;
    @ColumnInfo
    private String noteText;

    public Note() {
    }

    @Ignore
    public Note(String noteText) {
        this.noteText = noteText;
        noteDate = new Date();
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Override
    public String toString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        return "Note{" +
                "noteId=" + noteId +
                ", noteDate=" + df.format(noteDate) +
                ", noteText='" + noteText + '\'' +
                '}';
    }
}
