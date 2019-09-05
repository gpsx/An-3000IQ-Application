package gps.x.an3000qiapplication.repository

import gps.x.an3000qiapplication.config.RetrofitConfig
import gps.x.an3000qiapplication.controller.CharacterController
import gps.x.an3000qiapplication.controller.favoriteList
import gps.x.an3000qiapplication.model.ApiResponse
import gps.x.an3000qiapplication.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CharacterRepository {
    fun getCharacter(controller: CharacterController, page : Int) {
        val call : Call<ApiResponse> = RetrofitConfig().getCharacterService().searchCharacter(page)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                response.body()?.let {
                    controller.swapLoadingToList(it.characters.toMutableList())
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                controller.showErr(t.message)
                controller.onFirstLoadError()
            }
        })

    }
    fun getFavoriteCharacter(controller: CharacterController) {

        val call : Call<List<Character>> = RetrofitConfig().getCharacterService().searchFavoriteCharacter(favoriteList.joinToString(","))

        call.enqueue(object : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                response.body()?.let {
                    controller.swapLoadingToList(it.toMutableList())
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                controller.showErr(t.message)
                controller.onFirstLoadError()
            }
        })

    }
    fun getCharacterByName(controller: CharacterController, query : String) {
        val call : Call<ApiResponse> = RetrofitConfig().getCharacterService().searchCharacterByName(query)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if(response.isSuccessful)
                    controller.swapLoadingToSearchList(response.body()!!.characters.toMutableList())

            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                controller.showErr(t.message)
                controller.onFirstLoadError()
            }
        })

    }
}