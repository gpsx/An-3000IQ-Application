package gps.x.an3000qiapplication.controller

import com.orhanobut.hawk.Hawk
import gps.x.an3000qiapplication.model.CharacterDetail
import gps.x.an3000qiapplication.repository.CharacterDetailRepository
import gps.x.an3000qiapplication.view.containts.FAVORITE_ID_BUNDLE
import gps.x.an3000qiapplication.view.interfaces.DetailView
var favoriteList : MutableList<Int> = Hawk.get(FAVORITE_ID_BUNDLE, mutableListOf())

class CharacterDetailController (private val activity : DetailView){
    fun getCharacterDetail(id : String) = CharacterDetailRepository.getCharacterDetail(id, this)

    fun updateText(character: CharacterDetail) = activity.updateText(character)

    fun showErr(err : String?) = activity.showErr(err)

    fun addFavorite(characterId: Int) {
        favoriteList.add(characterId)
        Hawk.put(FAVORITE_ID_BUNDLE, favoriteList)
    }
    fun removeFavorite(characterId: Int) {
        favoriteList.remove(characterId)
        Hawk.put(FAVORITE_ID_BUNDLE, favoriteList)
    }
    fun requestFavoriteState (id: Int) : Boolean{
        return favoriteList.let {
             it.any { i -> (i == id) }
        }
    }
}