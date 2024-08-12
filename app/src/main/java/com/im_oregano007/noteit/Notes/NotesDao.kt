package com.im_oregano007.noteit.Notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("DELETE FROM notes WHERE id=:idToDelete")
    suspend fun deleteNote(idToDelete : Int )


    @Query("SELECT * FROM notes WHERE id=:idToFind LIMIT 1")
    fun getNote(idToFind : Int) : LiveData<Note>

    @Update
    suspend fun updateNote(note: Note)


}