package com.im_oregano007.noteit.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesDetailViewModelFactory(val notesRepository: NotesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesDetailViewModel(notesRepository) as T
    }
}