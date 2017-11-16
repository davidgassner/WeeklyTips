package com.example.android.weeklytips.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.weeklytips.model.Note;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insertAll(Note... notes);

    @Query("SELECT * FROM notes ORDER BY noteDate")
    List<Note> getAll();

    @Query("SELECT * FROM notes WHERE noteId = :noteId")
    Note findById(int noteId);

    @Delete
    void deleteNotes(Note... notes);

    @Delete
    void deleteNotes(List<Note> notes);

    @Query("DELETE FROM notes")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM notes")
    int getCount();

}