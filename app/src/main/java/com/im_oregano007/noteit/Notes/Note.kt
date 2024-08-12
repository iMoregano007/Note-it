package com.im_oregano007.noteit.Notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    var title : String,
    var value : String, val date: Date)
