package com.im_oregano007.noteit

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.im_oregano007.noteit.MVVM.MainViewModel
import com.im_oregano007.noteit.Notes.Note
import com.im_oregano007.noteit.databinding.NoteItemBinding
import java.text.SimpleDateFormat

class NotesAdapter(val context: Context, var noteList : List<Note>) : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(note: Note){
            binding.titleV.text = note.title
            binding.valveText.text = note.value
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val formattedDate = dateFormat.format(note.date)
            binding.dateV.text = formattedDate
        }
    }

    fun updatedNotes(notesList: List<Note>){
        this.noteList = notesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder( NoteItemBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("Adapter", "onBindViewHolder: is it coming here?")
        val cNote : Note = noteList[position]
        holder.bind(cNote)
        holder.binding.root.setOnClickListener {
            val intent = Intent(context,NotesDetail::class.java)
            intent.putExtra("isEdit",true)
            intent.putExtra("noteId",cNote.id)
            context.startActivity(intent)
        }
        holder.binding.deleteBtn.setOnClickListener {
            if(context is MainActivity){
                context.deleteNote(cNote)
                notifyItemRemoved(position)
//                notifyItemChanged(position)
//                need to check
            }
//            notifyDataSetChanged()
        }
//        cNote?.let{holder.binding.n = it}
        when(cNote.id.rem(5)){
            1 -> {holder.binding.mainLayout.setBackgroundResource(R.drawable.background_1)}
            2 -> {holder.binding.mainLayout.setBackgroundResource(R.drawable.background_2)}
            3 -> {holder.binding.mainLayout.setBackgroundResource(R.drawable.background_3)}
            4 -> {holder.binding.mainLayout.setBackgroundResource(R.drawable.background_4)}
            else -> {holder.binding.mainLayout.setBackgroundResource(R.drawable.background_5)}
        }
    }
}