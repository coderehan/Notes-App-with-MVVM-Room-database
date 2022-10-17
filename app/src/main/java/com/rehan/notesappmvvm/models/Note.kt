package com.rehan.notesappmvvm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "text") val text: String,        // @ColumnInfo is used to give the column name
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}
