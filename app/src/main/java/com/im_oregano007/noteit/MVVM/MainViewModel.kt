package com.im_oregano007.noteit.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.im_oregano007.noteit.Notes.Note

class MainViewModel(val notesRepository: NotesRepository) : ViewModel() {

    var notesLiveData : LiveData<List<Note>> = notesRepository.getAllNotes()





}