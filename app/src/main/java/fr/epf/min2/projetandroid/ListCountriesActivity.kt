package fr.epf.min2.projetandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min2.projetandroid.model.Country
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ListCountriesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_countries)

        recyclerView = findViewById<RecyclerView>(R.id.list_countries_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        synchro("France")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_bar, menu)
        val menuItem = menu?.findItem(R.id.search_bar_item)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint ="Search Here"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                synchro(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Votre logique lors du changement de texte de la requête
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun synchro(querySearched: String) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        val countriesService = retrofit.create(ListOfCountriesService::class.java)

        lifecycleScope.launch {
            try {
                progressBar.visibility = View.VISIBLE
                Log.d("myTag", "avant")

                val countryResultsName = try {
                    countriesService.getCountriesByName(querySearched)
                } catch (e: Exception) {
                    Log.e("myTag", "Error fetching countries by name", e)
                    emptyList<CountryResult>()
                }

                val countryResultsCapital = try {
                    countriesService.getCountriesByCapital(querySearched)
                } catch (e: Exception) {
                    Log.e("myTag", "Error fetching countries by capital", e)
                    emptyList<CountryResult>()
                }
                val countryResults = countryResultsName + countryResultsCapital
                Log.d("myTag", countryResults.toString())
                val countries = countryResults.map {
                    Country(it.name.common, it.translations.fra.common, it.capital, it.continents, it.flags.png)
                }
                Log.d("myTag", countries.toString())
                progressBar.visibility = View.GONE
                val adapter = CountryAdapter(countries)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.e("myTag", "Error fetching countries", e)
                progressBar.visibility = View.GONE
            }
        }
    }
}