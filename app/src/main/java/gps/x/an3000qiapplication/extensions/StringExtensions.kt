package gps.x.an3000qiapplication.extensions

fun String.getEpisodeNumber(): String{
    return this.split("/").last()
}