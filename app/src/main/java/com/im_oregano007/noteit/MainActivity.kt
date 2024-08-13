package com.im_oregano007.noteit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.im_oregano007.noteit.MVVM.MainViewModel
import com.im_oregano007.noteit.MVVM.MainViewModelFactory
import com.im_oregano007.noteit.MVVM.NotesRepository
import com.im_oregano007.noteit.Notes.NotesDB
import com.im_oregano007.noteit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var notesDB: NotesDB
    lateinit var notesRepository: NotesRepository
    lateinit var mainViewModel: MainViewModel
    var notesAdapter: NotesAdapter = NotesAdapter(this, emptyList())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.primaryVariant)

        notesDB = NotesDB.getDB(this)
        notesRepository = NotesRepository(notesDB.notesDao())

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(notesRepository))[MainViewModel::class.java]


        mainViewModel.notesLiveData.observe(this, Observer {
            Log.e("Observer", "onCreate: $it ")
            notesAdapter.updatedNotes(it)

//            if (notesAdapter != null){
//                notesAdapter!!.updatedNotes(it)
//            } else {
//                notesAdapter = NotesAdapter(this,it)
//                setUpRecyclerV()
//            }
        })


        setUpRecyclerV()

        binding.addNewNote.setOnClickListener {
            val intent = Intent(this,NotesDetail::class.java)
            startActivity(intent)
        }
    }

    private fun setUpRecyclerV() = binding.recyclerView.apply {
        layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter = notesAdapter
        setHasFixedSize(true)
    }




}