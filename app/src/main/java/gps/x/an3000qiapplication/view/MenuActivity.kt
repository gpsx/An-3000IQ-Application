package gps.x.an3000qiapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import gps.x.an3000qiapplication.R
import gps.x.an3000qiapplication.controller.favoriteList
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        all_characters_button.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        favorite_button.setOnClickListener {
            if (favoriteList != mutableListOf<Int>()) {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText(this@MenuActivity, "No favorite Character", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
