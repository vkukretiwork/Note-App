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

class MainActivity : AppCompatActivity(), ITitleAdapter {

    private val adapter = TitleAdapter(this,this)
    private lateinit var viewModel : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTitles.adapter = adapter
        rvTitles.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(ViewModel::class.java)
        viewModel.allTitle.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miAdd -> {
                showAddTitleDialog()
            }
            R.id.miDeleteAll-> {
                viewModel.deleteAllTitles()
            }
        }
        return true
    }

    private fun showAddTitleDialog(){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_note,null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddNote)

        with(builder){
            setTitle("Add note")
            setPositiveButton("ok"){ dialog, which ->
                val text = editText.text.toString()
                if(text.isNotEmpty()) {
                    viewModel.insertTitle(Title(text))
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

    override fun onItemClicked(title: Title) {
        viewModel.deleteTitle(title)
    }

}