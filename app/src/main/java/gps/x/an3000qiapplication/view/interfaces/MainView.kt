package gps.x.an3000qiapplication.view.interfaces

import gps.x.an3000qiapplication.model.Character

interface MainView{
    fun swapLoadingToList(characters : MutableList<Character>)

    fun showErr(text : String?)

    fun onFirstLoadError()

    fun swapLoadingToSearchList(characters : MutableList<Character>)
}