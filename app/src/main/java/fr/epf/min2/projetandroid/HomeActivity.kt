package fr.epf.min2.projetandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val countriesListButton = findViewById<Button>(R.id.home_list_button)
        val favorisListButton = findViewById<Button>(R.id.favoris_button)

        countriesListButton.click {
            val intent = Intent(this, ListCountriesActivity::class.java)
            startActivity(intent)
        }

        favorisListButton.click {
            val intent = Intent(this, ListFavorisActivity::class.java)
            startActivity(intent)
        }

    }
}

fun View.click(action : (View) -> Unit){
    Log.d("CLICK","click !")
    this.setOnClickListener(action)
}