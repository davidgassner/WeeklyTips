package com.example.android.weeklytips.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.android.weeklytips.model.Note

@Dao
interface NotesDao {

    @Insert
    fun insertAll(vararg notes: Note)

    @Query("SELECT * FROM notes ORDER BY noteDate")
    fun getAll(): List<Note>

    @Query("SELECT * FROM notes WHERE noteId = :arg0")
    fun findById(arg0: Int): Note

    @Delete
    fun deleteNotes(vararg notes: Note)

    @Delete
    fun deleteNotes(notes: List<Note>)

    @Query("DELETE FROM notes")
    fun deleteAll(): Int

    @Query("SELECT COUNT(*) FROM notes")
    fun getCount(): Int

}

