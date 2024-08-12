package com.im_oregano007.noteit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.im_oregano007.noteit.Notes.Note
import com.im_oregano007.noteit.Notes.NotesDB
import com.im_oregano007.noteit.databinding.ActivityNotesDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class NotesDetail : AppCompatActivity() {
    lateinit var binding: ActivityNotesDetailBinding
    lateinit var notesDB: NotesDB
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


        binding.done.setOnClickListener {
            val inputTitleS = binding.inputTitle.text.toString()
            val inputValueS = binding.inputValue.text.toString()
            val note = Note(0,inputTitleS,inputValueS, Date())
            lifecycleScope.launch(Dispatchers.IO){
                notesDB.notesDao().insertNote(note)
            }
            Toast.makeText(this@NotesDetail,"Note Added",Toast.LENGTH_SHORT).show()
            onBackPressedDispatcher.onBackPressed()
        }
    }
}