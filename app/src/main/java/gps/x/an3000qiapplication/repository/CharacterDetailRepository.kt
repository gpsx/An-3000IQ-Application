package gps.x.an3000qiapplication.repository

import gps.x.an3000qiapplication.config.RetrofitConfig
import gps.x.an3000qiapplication.controller.CharacterDetailController
import gps.x.an3000qiapplication.model.CharacterDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CharacterDetailRepository {
    fun getCharacterDetail(id: String, controller: CharacterDetailController) {
        val call : Call<CharacterDetail> = RetrofitConfig().getCharacterService().searchCharacterDetail(id)

        call.enqueue(object : Callback<CharacterDetail> {
            override fun onResponse(call: Call<CharacterDetail>, response: Response<CharacterDetail>) {
                controller.updateText(response.body()!!)
            }

            override fun onFailure(call: Call<CharacterDetail>, t: Throwable) {
                controller.showErr(t.message)
            }
        })

    }
}