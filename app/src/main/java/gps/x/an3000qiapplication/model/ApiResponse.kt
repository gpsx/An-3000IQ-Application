package gps.x.an3000qiapplication.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("results")
    val characters: List<Character>
)