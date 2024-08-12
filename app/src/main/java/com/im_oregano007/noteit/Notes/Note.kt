package com.im_oregano007.noteit.Notes

import androidx.room.Entity
import java.util.Date

@Entity(tableName = "notes")
data class Note(val id : Int, val title : String, val value : String, val date: Date)
