package fr.epf.min2.projetandroid.model

import android.os.Parcelable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val officialName: String,
    val frenchName: String,
    val capital: List<String>,
    val continent: List<String>,
    val flag: String
): Parcelable {

    companion object {
        fun generateCountryList(): ArrayList<Country> {
            val countries = ArrayList<Country>()
            countries.add(
                Country(
                    "France",
                    "France",
                    listOf("Paris"),
                    listOf("Europe"),
                    "https://flagcdn.com/w320/fr.png"
                )
            )
            countries.add(
                Country(
                    "Deutschland",
                    "Allemagne",
                    listOf("Berlin"),
                    listOf("Europe"),
                    "https://flagcdn.com/w320/de.png"
                )
            )
            countries.add(
                Country(
                    "México",
                    "Méxique",
                    listOf("Mexico City"),
                    listOf("North America"),
                    "https://flagcdn.com/w320/mx.png"
                )
            )
            return countries
        }
    }
}

