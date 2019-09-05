package gps.x.an3000qiapplication.controller

import gps.x.an3000qiapplication.view.interfaces.MainView
import gps.x.an3000qiapplication.model.Character
import gps.x.an3000qiapplication.repository.CharacterRepository

class CharacterController(private val main : MainView){

    fun getCharacters(page : Int) = CharacterRepository.getCharacter(this, page)

    fun getFavoriteCharacter() = CharacterRepository.getFavoriteCharacter(this)

    fun getCharacterByName(string: String) = CharacterRepository.getCharacterByName(this, string)

    fun showErr(err: String?) = main.showErr(err)

    fun swapLoadingToList(characters : MutableList<Character>) = main.swapLoadingToList(characters)

    fun swapLoadingToSearchList(characters : MutableList<Character>) = main.swapLoadingToSearchList(characters)

    fun onFirstLoadError() = main.onFirstLoadError()
}