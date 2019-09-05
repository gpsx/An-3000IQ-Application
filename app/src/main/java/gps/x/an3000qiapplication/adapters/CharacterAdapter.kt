package gps.x.an3000qiapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import gps.x.an3000qiapplication.R
import gps.x.an3000qiapplication.model.Character
import kotlinx.android.synthetic.main.character_layout.view.*

class CharacterAdapter(
    private val ctx: Context,
    private var characters: MutableList<Character> = mutableListOf(),
    private val itemClickListener: (String) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharactersViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(LayoutInflater.from(ctx).inflate(R.layout.character_layout, parent, false))
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    fun updateAdapter(list: List<Character>){
        characters = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class CharactersViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {

        fun bind(character: Character) {
            view.character_image.load(character.image) {
                crossfade(true)
                placeholder(R.drawable.img_placeholder)
                transformations(CircleCropTransformation())
            }
            view.character_name.text = character.name
            view.character_status.text = character.status
            view.character_root.setOnClickListener {
                itemClickListener(character.id.toString())
            }
        }
    }
}