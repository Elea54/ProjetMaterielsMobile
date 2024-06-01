package fr.epf.min2.projetandroid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val officialName: String,
    val frenchName: String,
    val capital: List<String>,
    val continent: List<String>,
    val flag: String,
    val latlng: List<Double>
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
                    "https://flagcdn.com/w320/fr.png",
                    listOf(46.0, 2.0)
                )
            )
            countries.add(
                Country(
                    "Germany",
                    "Allemagne",
                    listOf("Berlin"),
                    listOf("Europe"),
                    "https://flagcdn.com/w320/de.png",
                    listOf(51.0, 9.0)
                )
            )
            countries.add(
                    Country(
                        "Italy",
                        "Italie",
                        listOf("Rome"),
                        listOf("Europe"),
                        "https://flagcdn.com/w320/it.png",
                        listOf(42.83333333, 12.83333333)
                    )
                    )
            countries.add(
                Country(
                    "Spain",
                    "Espagne",
                    listOf("Madrid"),
                    listOf("Europe"),
                    "https://flagcdn.com/w320/es.png",
                    listOf(40.0, -4.0)
                )
            )
            countries.add(
                Country(
                    "United States of America",
                    "États-Unis",
                    listOf("Washington, D.C."),
                    listOf("North America"),
                    "https://flagcdn.com/w320/us.png",
                    listOf(38.0, -97.0)
                )
            )
            countries.add(
                Country(
                    "Canada",
                    "Canada",
                    listOf("Ottawa"),
                    listOf("North America"),
                    "https://flagcdn.com/w320/ca.png",
                    listOf(60.0, -95.0)
                )
            )
            countries.add(
                Country(
                    "Brazil",
                    "Brésil",
                    listOf("Brasília"),
                    listOf("South America"),
                    "https://flagcdn.com/w320/br.png",
                    listOf(-10.0, -55.0)
                )
            )
            countries.add(
                Country(
                    "Argentina",
                    "Argentine",
                    listOf("Buenos Aires"),
                    listOf("South America"),
                    "https://flagcdn.com/w320/ar.png",
                    listOf(-34.0, -64.0)
                )
            )
            countries.add(
                Country(
                    "Japan",
                    "Japon",
                    listOf("Tokyo"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/jp.png",
                    listOf(36.0, 138.0)
                )
            )
            countries.add(
                Country(
                    "South Korea",
                    "Corée du Sud",
                    listOf("Seoul"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/kr.png",
                    listOf(37.0, 127.5)
                )
            )
            countries.add(
                Country(
                    "China",
                    "Chine",
                    listOf("Beijing"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/cn.png",
                    listOf(22.16666666, 113.55)
                )
            )
            countries.add(
                Country(
                    "Australia",
                    "Australie",
                    listOf("Canberra"),
                    listOf("Oceania"),
                    "https://flagcdn.com/w320/au.png",
                    listOf(-27.0, 133.0)
                )
            )
            countries.add(
                Country(
                    "New Zealand",
                    "Nouvelle-Zélande",
                    listOf("Wellington"),
                    listOf("Oceania"),
                    "https://flagcdn.com/w320/nz.png",
                    listOf(-41.0, 174.0)
                )
            )
            countries.add(
                Country(
                    "India",
                    "Inde",
                    listOf("New Delhi"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/in.png",
                    listOf(20.0, 77.0)
                )
            )
            countries.add(
                    Country(
                        "Russia",
                        "Russie",
                        listOf("Moscow"),
                        listOf("Europe", "Asia"),
                        "https://flagcdn.com/w320/ru.png",
                        listOf(60.0, 100.0)
                    )
                    )
            countries.add(
                Country(
                    "Egypt",
                    "Égypte",
                    listOf("Cairo"),
                    listOf("Africa", "Asia"),
                    "https://flagcdn.com/w320/eg.png",
                    listOf(27.0, 30.0)
                )
            )
            countries.add(
                Country(
                    "South Africa",
                    "Afrique du Sud",
                    listOf("Pretoria", "Cape Town", "Bloemfontein"),
                    listOf("Africa"),
                    "https://flagcdn.com/w320/za.png",
                    listOf(-29.0, 24.0)
                )
            )
            countries.add(
                Country(
                    "Nigeria",
                    "Nigéria",
                    listOf("Abuja"),
                    listOf("Africa"),
                    "https://flagcdn.com/w320/ng.png",
                    listOf(10.0, 8.0)
                )
            )
            countries.add(
                Country(
                    "Turkey",
                    "Turquie",
                    listOf("Ankara"),
                    listOf("Europe", "Asia"),
                    "https://flagcdn.com/w320/tr.png",
                    listOf(39.0, 35.0)
                )
            )
            countries.add(
                Country(
                    "Thailand",
                    "Thaïlande",
                    listOf("Bangkok"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/th.png",
                    listOf(15.0, 100.0)
                )
            )
            countries.add(
                Country(
                    "Vietnam",
                    "Vietnam",
                    listOf("Hanoi"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/vn.png",
                    listOf(16.16666666, 107.83333333)
                )
            )
            countries.add(
                Country(
                    "Mexico",
                    "Mexique",
                    listOf("Mexico City"),
                    listOf("North America"),
                    "https://flagcdn.com/w320/mx.png",
                    listOf(23.0, -102.0)
                )
            )
            countries.add(
                Country(
                    "Indonesia",
                    "Indonésie",
                    listOf("Jakarta"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/id.png",
                    listOf(-5.0, 120.0)
                )
            )
            countries.add(
                Country(
                    "Philippines",
                    "Philippines",
                    listOf("Manila"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/ph.png",
                    listOf(13.0, 122.0)
                )
            )
            countries.add(
                Country(
                    "Iran",
                    "Iran",
                    listOf("Tehran"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/ir.png",
                    listOf(32.0, 53.0)
                )
            )
            countries.add(
                Country(
                    "Iraq",
                    "Irak",
                    listOf("Baghdad"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/iq.png",
                    listOf(33.0, 44.0)
                )
            )
            countries.add(
                Country(
                    "Pakistan",
                    "Pakistan",
                    listOf("Islamabad"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/pk.png",
                    listOf(30.0, 70.0)
                )
            )
            countries.add(
                Country(
                    "Bangladesh",
                    "Bangladesh",
                    listOf("Dhaka"),
                    listOf("Asia"),
                    "https://flagcdn.com/w320/bd.png",
                    listOf(24.0, 90.0)
                )
            )
            return countries
        }
    }
}

