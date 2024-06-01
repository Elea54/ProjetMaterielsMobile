package fr.epf.min2.projetandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val countriesListButton = findViewById<Button>(R.id.home_list_button)
        val favorisListButton = findViewById<Button>(R.id.favoris_button)
        val quizzButton = findViewById<Button>(R.id.quizz_button)


        countriesListButton.click {
            val intent = Intent(this, ListCountriesActivity::class.java)
            startActivity(intent)
        }

        favorisListButton.click {
            val intent = Intent(this, ListFavorisActivity::class.java)
            startActivity(intent)
        }
        quizzButton.click {
            val intent = Intent(this, QuizzFlagsActivity::class.java)
            startActivity(intent)
        }

    }
}

fun View.click(action : (View) -> Unit){
    Log.d("CLICK","click !")
    this.setOnClickListener(action)
}


fun showPopup(message: String, context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
        .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->

        })
    val dialog = builder.create()
    dialog.show()
}