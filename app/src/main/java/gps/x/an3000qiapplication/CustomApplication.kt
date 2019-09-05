package gps.x.an3000qiapplication

import android.app.Application
import com.orhanobut.hawk.Hawk

class CustomApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}