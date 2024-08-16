package com.im_oregano007.noteit.MVVM

import androidx.lifecycle.LiveData
import com.im_oregano007.noteit.Notes.Note
import com.im_oregano007.noteit.Notes.NotesDao

class NotesRepository(val notesDao: NotesDao) {

    fun getAllNotes() : LiveData<List<Note>> {
       return notesDao.getAllNotes()
    }

    suspend fun updateNote(note: Note){
        notesDao.updateNote(note)
    }

    suspend fun insertNote(note: Note){
        notesDao.insertNote(note)
    }

    suspend fun deleteNote(id : Int){
        notesDao.deleteNote(id)
    }

    fun getNote(id : Int) : LiveData<Note> {
        return notesDao.getNote(id)
    }


}