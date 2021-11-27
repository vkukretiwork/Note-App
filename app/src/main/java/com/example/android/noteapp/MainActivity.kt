package com.example.android.noteapp

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        ITestAdapter {

    private lateinit var viewModel : ViewModel
    private lateinit var testAdapter : TitleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(ViewModel::class.java)

        testAdapter = TitleAdapter(this,this)

        rvTitles.adapter = testAdapter
        rvTitles.layoutManager = LinearLayoutManager(this)

        viewModel.allTilesWithSubtitles.observe(this, { objList ->
            objList?.let {
                testAdapter.updateList(it)
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
                viewModel.deleteAllNotes()
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
    private fun showAddSubtitleDialog(title : Title) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_note,null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddNote)

        with(builder){
            setTitle("Add note")
            setPositiveButton("ok"){ dialog, which ->
                val text = editText.text.toString()
                if(text.isNotEmpty()) {
                    val subtitle = Subtitle(text , title.idTitle)
                    viewModel.insertSubtitle(subtitle)


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
    private fun showEditTitleDialog(title : Title) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_note,null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddNote)
        editText.setText(title.titleNote)

        with(builder){
            setTitle("Edit note")
            setPositiveButton("ok"){ dialog, which ->
                val text = editText.text.toString()
                if(text.isNotEmpty()) {
                    title.titleNote = text
                    viewModel.updateTitle(title)
                }else{
                    Toast.makeText(context, "Can not update to empty note", Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("cancel"){ dialog, which ->

            }
            setView(dialogLayout)
            show()
        }
    }
    private fun showEditSubtitleDialog(subtitle : Subtitle) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_note,null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddNote)
        editText.setText(subtitle.subtitleNote)

        with(builder){
            setTitle("Edit note")
            setPositiveButton("ok"){ dialog, which ->
                val text = editText.text.toString()
                if(text.isNotEmpty()) {
                    subtitle.subtitleNote = text
                    viewModel.updateSubtitle(subtitle)
                }else{
                    Toast.makeText(context, "Can not update to empty note", Toast.LENGTH_SHORT).show()
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


    override fun onCrossButtonClickedTest(title: Title) {
        viewModel.deleteTitleAndItsSubtitles(title)
    }

    override fun onTitleClickedTest(title: Title) {
        showEditTitleDialog(title)
    }

    override fun onSubtitleCrossButtonClicked(subtitle: Subtitle) {
        viewModel.deleteSubtitle(subtitle)
    }

    override fun onSubtitleClicked(subtitle: Subtitle) {
        showEditSubtitleDialog(subtitle)
//        Toast.makeText(this,"you clicked on subtitle : ${subtitle.subtitleNote}",Toast.LENGTH_SHORT).show()
    }

    override fun onSubtitleAddButtonClicked(title: Title) {
        showAddSubtitleDialog(title)
    }

}