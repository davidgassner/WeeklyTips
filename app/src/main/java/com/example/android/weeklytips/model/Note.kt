package com.example.android.weeklytips.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(@PrimaryKey(autoGenerate = true)
                  var noteId: Int,
                @ColumnInfo
                  var noteDate: Date?,
                @ColumnInfo
                  var noteText: String) {

    constructor(): this(0, null, "")

    constructor(noteText: String) : this() {
        this.noteText = noteText
        noteDate = Date()
    }

}