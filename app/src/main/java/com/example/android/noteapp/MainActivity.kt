package com.example.android.noteapp

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var titleList = mutableListOf<Title>()
    private val adapter = TitleAdapter(titleList)
    private lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {

        })


        rvTitles.adapter = adapter
        rvTitles.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miAdd -> {
                showAddNoteDialog()
            }
            R.id.miDelete -> {

            }
            R.id.miDeleteAll-> {
                titleList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        return true
    }

    private fun showAddNoteDialog(){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_note,null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddNote)

        with(builder){
            setTitle("Add note")
            setPositiveButton("ok"){ dialog, which ->
                val text = editText.text.toString()
                if(text != "") {
                    val title = Title(1, text)
                    titleList.add(title)
                    adapter.notifyItemInserted(titleList.size-1)
                }else{
                    Toast.makeText(context, "Can not add empty note", Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("cancel"){ dialog, which ->

            }
            setView(dialogLayout)
            show()
        }
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
          imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

}