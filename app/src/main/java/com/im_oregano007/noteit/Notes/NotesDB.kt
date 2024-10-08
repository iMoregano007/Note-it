package com.im_oregano007.noteit.Notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.im_oregano007.noteit.Converters

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
public abstract class NotesDB : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object{

        @Volatile
        private var INSTANCE : NotesDB? = null

        fun getDB(context: Context) : NotesDB {

            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NotesDB::class.java,
                        "notes_db")
                        .build()
                }

            }

            return INSTANCE!!
        }
    }

}