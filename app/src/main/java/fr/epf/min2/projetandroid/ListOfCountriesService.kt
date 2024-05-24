package fr.epf.min2.projetandroid

import retrofit2.http.GET
import retrofit2.http.Path

interface ListOfCountriesService{
    @GET("translation/{countryName}?fields=name,translations,capital,continents,flags")
    suspend fun getCountriesByName(@Path("countryName") country: String): List<CountryResult>

    @GET("capital/{capital}?fields=name,translations,capital,continents,flags")
    suspend fun getCountriesByCapital(@Path("capital") capital: String): List<CountryResult>

}

data class CountryResult(val name: Name, val translations : Translations, val capital: List<String>,
                            val continents: List<String>, val flags: Flags)
data class Name(val common : String)

data class Translations(val fra: Fra)

data class Fra(val common: String)

data class Flags(val png: String)
