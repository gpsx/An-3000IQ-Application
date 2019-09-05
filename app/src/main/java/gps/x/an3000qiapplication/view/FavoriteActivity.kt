package gps.x.an3000qiapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gps.x.an3000qiapplication.R
import gps.x.an3000qiapplication.adapters.CharacterAdapter
import gps.x.an3000qiapplication.controller.CharacterController
import gps.x.an3000qiapplication.model.Character
import gps.x.an3000qiapplication.view.containts.CHARACTER_ID_BUNDLE
import gps.x.an3000qiapplication.view.interfaces.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.internetfaik.*

class FavoriteActivity : AppCompatActivity(), MainView {
    override fun swapLoadingToSearchList(characters: MutableList<Character>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val adapter = CharacterAdapter(this, mutableListOf()) { id -> clickListener(id) }
    private val characterController by lazy { CharacterController(this) }
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        characterController.getFavoriteCharacter()
        reload_button.setOnClickListener {
            characterController.getFavoriteCharacter()
            progress_circular.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        lv.layoutManager = linearLayoutManager
        lv.adapter = adapter
    }

    override fun swapLoadingToList(characters : MutableList<Character>){
        adapter.updateAdapter(characters)
        lv.visibility = View.VISIBLE
        fail.visibility = View.GONE
        progress_circular.visibility = View.GONE
    }

    override fun showErr(text : String?){
        Toast.makeText(this@FavoriteActivity, "$text", Toast.LENGTH_LONG).show()
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
