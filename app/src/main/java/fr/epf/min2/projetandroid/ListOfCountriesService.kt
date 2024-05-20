package fr.epf.min2.projetandroid

import retrofit2.http.GET
import retrofit2.http.Query

interface ListOfCountriesService{
//    @GET("translation/fr")
    @GET("translation/Fr?fields=name,translations,capital,continents,flags")
    suspend fun getAllCountries(): List<CountryResult>
}

data class CountryResult(val name: Name, val translations : Translations, val capital: List<String>,
                            val continents: List<String>, val flags: Flags)
data class Name(val common : String)

data class Translations(val fra: Fra)

data class Fra(val common: String)

data class Flags(val png: String)
