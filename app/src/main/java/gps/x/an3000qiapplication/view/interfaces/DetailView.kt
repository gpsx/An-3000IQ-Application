package gps.x.an3000qiapplication.view.interfaces

import gps.x.an3000qiapplication.model.CharacterDetail

interface DetailView {
    fun updateText(character: CharacterDetail)

    fun showErr(err: String?)
}