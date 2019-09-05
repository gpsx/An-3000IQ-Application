package gps.x.an3000qiapplication.config

import gps.x.an3000qiapplication.service.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    lateinit var retrofit: Retrofit
    init {
        retrofitConfig()
    }
    fun retrofitConfig(){

        retrofit = Retrofit.Builder()
            .client(CustomHttpClient().getOkHttpClient())
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCharacterService(): CharacterService {
        return this.retrofit.create(CharacterService::class.java)
    }
}