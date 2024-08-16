package com.im_oregano007.noteit.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.im_oregano007.noteit.Notes.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val notesRepository: NotesRepository) : ViewModel() {

    var notesLiveData : LiveData<List<Note>> = notesRepository.getAllNotes()

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            notesRepository.deleteNote(note.id)
        }
    }





}