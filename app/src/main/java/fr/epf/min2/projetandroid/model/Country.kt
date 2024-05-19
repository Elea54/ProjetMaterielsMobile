package fr.epf.min2.projetandroid.model

import android.widget.ImageView
import com.squareup.picasso.Picasso

data class Country(
    val officialName: String,
    val frenchName: String,
    val capital: String,
    val continent: String,
    val flag: String
){

    companion object {
        fun generateCountryList(): ArrayList<Country> {
            val countries = ArrayList<Country>()
            countries.add(
                Country(
                    "France",
                    "France",
                    "Paris",
                    "Europe",
                    "https://flagcdn.com/w320/fr.png"
                )
            )
            countries.add(
                Country(
                    "Deutschland",
                    "Allemagne",
                    "Berlin",
                    "Europe",
                    "https://flagcdn.com/w320/de.png"
                )
            )
            countries.add(
                Country(
                    "México",
                    "Méxique",
                    "Mexico City",
                    "North America",
                    "https://flagcdn.com/w320/mx.png"
                )
            )
            return countries
        }
    }
}

