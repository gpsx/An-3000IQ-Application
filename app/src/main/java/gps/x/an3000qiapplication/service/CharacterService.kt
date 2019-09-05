package gps.x.an3000qiapplication.service

import gps.x.an3000qiapplication.model.ApiResponse
import gps.x.an3000qiapplication.model.Character
import gps.x.an3000qiapplication.model.CharacterDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character/")
    fun searchCharacter(@Query("page")page : Int): Call<ApiResponse>

    @GET("character/{id}")
    fun searchCharacterDetail(@Path("id") id : String): Call<CharacterDetail>

    @GET("character/0,{ids}")
    fun searchFavoriteCharacter(@Path("ids") id : String): Call<List<Character>>

    @GET("character/")
    fun searchCharacterByName(@Query("name")name : String): Call<ApiResponse>
}