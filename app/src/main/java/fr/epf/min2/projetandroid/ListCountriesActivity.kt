package fr.epf.min2.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min2.projetandroid.model.Country
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ListCountriesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_countries)

        recyclerView = findViewById<RecyclerView>(R.id.list_countries_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        synchro()
//        val countries = Country.generateCountryList()
//        recyclerView.adapter = CountryAdapter(countries)
    }
    private fun synchro() {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        val countriesService = retrofit.create(ListOfCountriesService::class.java)
        runBlocking {
            val coutriesResult = countriesService.getAllCountries()
            val countries = coutriesResult.map {
                Country(it.name.common, it.translations.fra.common, it.capital, it.continents, it.flags.png)
            }
            val adapter = CountryAdapter(countries)
            recyclerView.adapter = adapter
        }

    }


}