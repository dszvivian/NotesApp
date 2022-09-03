package com.example.notesapp


import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : Fragment(R.layout.activity_main), NoteClickInterface, NoteClickDeleteInterface {

    lateinit var viewModal: NoteViewModel
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = activity

        notesRV = view.findViewById(R.id.notesRV)
        addFAB = view.findViewById(R.id.idFAB)


        notesRV.layoutManager = LinearLayoutManager(context)


        val noteRVAdapter = NoteRVAdapter(context!!.applicationContext, this, this)


        notesRV.adapter = noteRVAdapter


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(context.application)
        ).get(NoteViewModel::class.java)


        viewModal.allNotes.observe(context, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })



        addFAB.setOnClickListener {
            val intent = Intent(context, AddEditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(context, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDescription", note.desc)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.delete(note)
        // displaying a toast message
        Toast.makeText(context, "${note.title} Deleted", Toast.LENGTH_LONG).show()
    }
}