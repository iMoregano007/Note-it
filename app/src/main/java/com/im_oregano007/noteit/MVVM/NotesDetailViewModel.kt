package com.im_oregano007.noteit.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.im_oregano007.noteit.Notes.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesDetailViewModel(val notesRepository: NotesRepository) : ViewModel(){

    lateinit var noteLiveData : LiveData<Note>
//    init {
//        mutableLiveData.value = notesRepository.getNote()
//    }
    fun getSingleNote(id : Int){
        noteLiveData = notesRepository.getNote(id)
    }

    fun insertNote(isEdit : Boolean, newNote: Note? = null){


        if (newNote != null){
            viewModelScope.launch(Dispatchers.IO){
                if(!isEdit){
                    notesRepository.insertNote(newNote)
                } else{
                    notesRepository.updateNote(newNote)
                }

            }
        }
    }

}