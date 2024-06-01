package fr.epf.min2.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.epf.min2.projetandroid.data.addCountryToList
import fr.epf.min2.projetandroid.data.isCountryInFavorisList
import fr.epf.min2.projetandroid.data.removeCountryFromFavorisList
import fr.epf.min2.projetandroid.model.Country

class CountryDetailsActivity : AppCompatActivity() {
    private var country: Country? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_county_details)

        val flagImageView = findViewById<ImageView>(R.id.country_flag_details_imageView)
        val frNameTextView = findViewById<TextView>(R.id.country_name_fr_details_textView)
        val englishNameTextView = findViewById<TextView>(R.id.counrty_english_name_details_textView)
        val capitalTextView = findViewById<TextView>(R.id.counrty_capital_details_textView)
        val continentTextView = findViewById<TextView>(R.id.counrty_continent_details_textView)

        intent.extras?.apply {
            country = getParcelable(COUNTRY_ID_EXTRA) as? Country
            country?.let {
                Picasso.get()
                    .load(it.flag)
                    .into(flagImageView)
                frNameTextView.text = it.frenchName
                englishNameTextView.text = it.officialName
                capitalTextView.text = it.capital.get(0)
                continentTextView.text = it.continent.get(0)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favoris_menu, menu)
        updateFavorisIcon(menu?.findItem(R.id.star_favoris_item))
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.star_favoris_item -> {
                countryAsFavoris()
                updateFavorisIcon(item)
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateFavorisIcon(item: MenuItem?) {
        val isCountryFavoris = country?.let { isCountryInFavorisList(this, it) } ?: false
        if (isCountryFavoris) {
            item?.setIcon(R.drawable.baseline_star_24)
        } else {
            item?.setIcon(R.drawable.baseline_star_border_24)
        }
    }

    private fun countryAsFavoris() {
        Log.d("myTag", "Favoris " + (country?.frenchName ))
        country?.let {
            if (isCountryInFavorisList(this, it)) {
                removeCountryFromFavorisList(this, it)
            } else {
                addCountryToList(this, it)
            }
        }
//        clearJsonFile(this)
    }
}