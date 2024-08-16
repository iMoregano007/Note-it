package com.im_oregano007.noteit

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.im_oregano007.noteit.MVVM.NotesDetailViewModel
import com.im_oregano007.noteit.MVVM.NotesDetailViewModelFactory
import com.im_oregano007.noteit.MVVM.NotesRepository
import com.im_oregano007.noteit.Notes.Note
import com.im_oregano007.noteit.Notes.NotesDB
import com.im_oregano007.noteit.databinding.ActivityNotesDetailBinding
import java.util.Date

class NotesDetail : AppCompatActivity() {
    lateinit var binding: ActivityNotesDetailBinding
    lateinit var notesDB: NotesDB
    lateinit var notesRepository: NotesRepository
    lateinit var notesDetailViewModel: NotesDetailViewModel
    lateinit var existingNote: Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notes_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notesDB = NotesDB.getDB(this)
        notesRepository = NotesRepository(notesDB.notesDao())
        notesDetailViewModel = ViewModelProvider(this,NotesDetailViewModelFactory(notesRepository))[NotesDetailViewModel::class.java]
//        binding.viewM = notesDetailViewModel
        val isEdit : Boolean = intent.getBooleanExtra("isEdit",false)
        val noteId : Int = intent.getIntExtra("noteId",0)
        updateUI(isEdit)
        if(isEdit){
            notesDetailViewModel.getSingleNote(noteId)
            notesDetailViewModel.noteLiveData.observe(this, Observer {
                existingNote = it!!
                updateUI(isEdit,existingNote)
            })
        }


        window.statusBarColor = ContextCompat.getColor(this,R.color.primaryVariant)

        binding.done.setOnClickListener {
            val inputTitleS = binding.inputTitle.text.toString()
            val inputValueS = binding.inputValue.text.toString()
            val note = Note(noteId,inputTitleS,inputValueS, Date())
            notesDetailViewModel.insertNote(isEdit,note)
//            lifecycleScope.launch(Dispatchers.IO){
//                notesDB.notesDao().insertNote(note)
//            }
//            Toast.makeText(this@NotesDetail,"Note Added",Toast.LENGTH_SHORT).show()
            onBackPressedDispatcher.onBackPressed()
        }
        binding.editBtn.setOnClickListener {
            binding.editBtn.visibility = View.GONE
            binding.done.visibility = View.VISIBLE
            binding.inputTitle.isEnabled = true
            binding.inputValue.isEnabled = true
        }

    }

    fun updateUI(isEdit : Boolean, note: Note? = null){
        if(isEdit){
            if(note == null){
                binding.progressBar.visibility = View.VISIBLE
                binding.inputLayout.visibility = View.GONE

            } else{
                binding.progressBar.visibility = View.GONE
                binding.inputLayout.visibility = View.VISIBLE
                binding.inputTitle.text = Editable.Factory.getInstance().newEditable(note.title)
                binding.inputValue.text = Editable.Factory.getInstance().newEditable(note.value)
                binding.inputTitle.isEnabled = false
                binding.inputValue.isEnabled = false

            }
            binding.done.visibility = View.GONE
            binding.editBtn.visibility = View.VISIBLE
        } else{
            binding.progressBar.visibility = View.GONE
            binding.inputLayout.visibility = View.VISIBLE
        }
    }
}