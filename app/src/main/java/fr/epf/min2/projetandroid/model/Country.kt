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
                    "Germany",
                    "Allemagne",
                    listOf("Berlin"),
                    listOf("Europe"),
                    "https://flagcdn.com/w320/de.png"
                )
            )
            countries.add(
                    Country(
                        "Italy",
                        "Italie",
                        listOf("Rome"),
                        listOf("Europe"),
                        "https://flagcdn.com/w320/it.png"
                    )
                    )
            countries.add(
                Country(
                    "Spain",
                    "Espagne",
                    listOf("Madrid"),
                    listOf("Europe"),
                    "https://flagcdn.com/w320/es.png"
                )
            )
            countries.add(
                Country(
                    "United States of America",
                    "États-Unis",
                    listOf("Washington, D.C."),
                    listOf("North America"),
                    "https://flagcdn.com/w320/us.png"
                )
            )
            countries.add(
                Country(
                    "Canada",
                    "Canada",
                    listOf("Ottawa"),
                    listOf("North America"),
                    "https://flagcdn.com/w320/ca.png"
                )
            )
            countries.add(
                Country(
                    "Brazil",
                    "Brésil",
                    listOf("Brasília"),
                    listOf("South America"),
                    "https://flagcdn.com/w320/br.png"
                )
            )
            countries.add(
                Country(
                    "Argentina",
                    "Argentine",
                    listOf("Buenos Aires"),
                    listOf("South America"),
                    "https://flagcdn.com/w320/ar.png"
                )
            )
            countries.add(
                Country(
                    "Japan",
                    "Japon",
                    listOf("Tokyo"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/jp.png"
                )
            )
            countries.add(
                Country(
                    "South Korea",
                    "Corée du Sud",
                    listOf("Seoul"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/kr.png"
                )
            )
            countries.add(
                Country(
                    "China",
                    "Chine",
                    listOf("Beijing"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/cn.png"
                )
            )
            countries.add(
                Country(
                    "Australia",
                    "Australie",
                    listOf("Canberra"),
                    listOf("Oceania"),
                    "https://flagcdn.com/w320/au.png"
                )
            )
            countries.add(
                Country(
                    "New Zealand",
                    "Nouvelle-Zélande",
                    listOf("Wellington"),
                    listOf("Oceania"),
                    "https://flagcdn.com/w320/nz.png"
                )
            )
            countries.add(
                Country(
                    "India",
                    "Inde",
                    listOf("New Delhi"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/in.png"
                )
            )
            countries.add(
                    Country(
                        "Russia",
                        "Russie",
                        listOf("Moscow"),
                        listOf("Europe", "Asia"),
                        "https://flagcdn.com/w320/ru.png"
                    )
                    )
            countries.add(
                Country(
                    "Egypt",
                    "Égypte",
                    listOf("Cairo"),
                    listOf("Africa", "Asia"),
                    "https://flagcdn.com/w320/eg.png"
                )
            )
            countries.add(
                Country(
                    "South Africa",
                    "Afrique du Sud",
                    listOf("Pretoria", "Cape Town", "Bloemfontein"),
                    listOf("Africa"),
                    "https://flagcdn.com/w320/za.png"
                )
            )
            countries.add(
                Country(
                    "Nigeria",
                    "Nigéria",
                    listOf("Abuja"),
                    listOf("Africa"),
                    "https://flagcdn.com/w320/ng.png"
                )
            )
            countries.add(
                Country(
                    "Saudi Arabia",
                    "Arabie saoudite",
                    listOf("Riyadh"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/sa.png"
                )
            )
            countries.add(
                Country(
                    "Turkey",
                    "Turquie",
                    listOf("Ankara"),
                    listOf("Europe", "Asia"),
                    "https://flagcdn.com/w320/tr.png"
                )
            )
            countries.add(
                Country(
                    "Thailand",
                    "Thaïlande",
                    listOf("Bangkok"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/th.png"
                )
            )
            countries.add(
                Country(
                    "Vietnam",
                    "Vietnam",
                    listOf("Hanoi"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/vn.png"
                )
            )
            countries.add(
                Country(
                    "Mexico",
                    "Mexique",
                    listOf("Mexico City"),
                    listOf("North America"),
                    "https://flagcdn.com/w320/mx.png"
                )
            )
            countries.add(
                Country(
                    "Indonesia",
                    "Indonésie",
                    listOf("Jakarta"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/id.png"
                )
            )
            countries.add(
                Country(
                    "Philippines",
                    "Philippines",
                    listOf("Manila"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/ph.png"
                )
            )
            countries.add(
                Country(
                    "Iran",
                    "Iran",
                    listOf("Tehran"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/ir.png"
                )
            )
            countries.add(
                Country(
                    "Iraq",
                    "Irak",
                    listOf("Baghdad"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/iq.png"
                )
            )
            countries.add(
                Country(
                    "Pakistan",
                    "Pakistan",
                    listOf("Islamabad"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/pk.png"
                )
            )
            countries.add(
                Country(
                    "Bangladesh",
                    "Bangladesh",
                    listOf("Dhaka"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/bd.png"
                )
            )
            return countries
        }
    }
}

