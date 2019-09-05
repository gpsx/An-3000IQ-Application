package gps.x.an3000qiapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import coil.api.load
import coil.transform.CircleCropTransformation
import gps.x.an3000qiapplication.R
import gps.x.an3000qiapplication.controller.CharacterDetailController
import gps.x.an3000qiapplication.extensions.getEpisodeNumber
import gps.x.an3000qiapplication.model.CharacterDetail
import gps.x.an3000qiapplication.view.containts.CHARACTER_ID_BUNDLE
import gps.x.an3000qiapplication.view.interfaces.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.character_layout.view.*

class DetailActivity : AppCompatActivity(), DetailView {

    private val characterController by lazy { CharacterDetailController(this) }
    private var characterId = 0
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent.getStringExtra(CHARACTER_ID_BUNDLE)?.let {
            characterController.getCharacterDetail(it)
        }
        favorite_button.setOnClickListener {
            if (isFavorite)
                characterController.removeFavorite(characterId)
            else
                characterController.addFavorite(characterId)
            defineFavoriteButton(characterId)
        }
    }

    override fun updateText(character: CharacterDetail) {
        character_image.character_image.load(character.image) {
            crossfade(true)
            placeholder(R.drawable.img_placeholder)
            transformations(CircleCropTransformation())
        }
        character_name.text = character.name
        character_status.text = character.status
        character_gender.text = character.gender
        character_species.text = character.species
        character_origin.text = character.origin.name
        character_location.text = character.location.name
        character_episode.text = character.episode.joinToString(",") {
            it.getEpisodeNumber()
        }
        characterId = character.id
        progress_circular.visibility = View.GONE
        defineFavoriteButton(character.id)
    }

    override fun showErr(err : String?){
        Toast.makeText(this@DetailActivity, "$err", Toast.LENGTH_SHORT).show()
    }
    private fun defineFavoriteButton(id: Int){
        favorite_button.visibility = View.VISIBLE
        isFavorite = characterController.requestFavoriteState(id)
        if (isFavorite){
            favorite_button.text = getString(R.string.remove_favorite)
        }else{
            favorite_button.text = getString(R.string.add_favorite)
        }
    }
}
