package gps.x.an3000qiapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import gps.x.an3000qiapplication.R
import gps.x.an3000qiapplication.adapters.CharacterAdapter
import gps.x.an3000qiapplication.controller.CharacterController
import gps.x.an3000qiapplication.view.interfaces.MainView
import gps.x.an3000qiapplication.model.Character
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView
import gps.x.an3000qiapplication.view.containts.CHARACTER_ID_BUNDLE
import kotlinx.android.synthetic.main.internetfaik.*

class MainActivity : AppCompatActivity(), MainView {

    private val adapter = CharacterAdapter(this, mutableListOf()) { id -> clickListener(id) }
    private val characterController by lazy { CharacterController(this) }
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private var loadedCharacters: MutableList<Character> = mutableListOf()
    private val scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            characterController.getCharacters(page)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        characterController.getCharacters(1)
        reload_button.setOnClickListener {
            characterController.getCharacters(1)
            progress_circular.visibility = View.VISIBLE
            fail.visibility = View.GONE
        }
        search_img_button.setOnClickListener {
            characterController.getCharacterByName(edit_character_name.text.toString())
            progress_circular.visibility = View.VISIBLE
            lv.visibility = View.GONE
        }
        edit_character_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (editable.isNullOrEmpty()) swapLoadingToList(loadedCharacters)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }

    override fun onResume() {
        super.onResume()
        lv.layoutManager = linearLayoutManager
        lv.adapter = adapter
    }

    override fun swapLoadingToList(characters : MutableList<Character>){
        loadedCharacters.addAll(characters)
        loadedCharacters = filterList()
        adapter.updateAdapter(loadedCharacters)
        lv.addOnScrollListener(scrollListener)
        lv.visibility = View.VISIBLE
        fail.visibility = View.GONE
        progress_circular.visibility = View.GONE
    }

    private fun filterList() : MutableList<Character>{
        return loadedCharacters.distinctBy{ it.id }.toMutableList()
    }

    override fun swapLoadingToSearchList(characters : MutableList<Character>){
        adapter.updateAdapter(characters)
        lv.removeOnScrollListener(scrollListener)
        lv.visibility = View.VISIBLE
        fail.visibility = View.GONE
        progress_circular.visibility = View.GONE
    }

    override fun showErr(text : String?){
        Toast.makeText(this@MainActivity, "$text", Toast.LENGTH_LONG).show()
    }
    override fun onFirstLoadError(){
        progress_circular.visibility = View.GONE
        fail.visibility = View.VISIBLE
    }
    private fun clickListener(id: String){
        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra(CHARACTER_ID_BUNDLE, id)
        startActivity(intent)
    }
}