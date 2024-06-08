package fr.epf.min2.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
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
import java.text.Normalizer
import java.util.regex.Pattern

class ListCountriesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    var countries = listOf<Country>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_countries)

        recyclerView = findViewById<RecyclerView>(R.id.list_countries_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        synchro()
//        quandAPIbug()
    }

    private fun quandAPIbug() {
        countries = Country.generateCountryList()
        val adapter = CountryAdapter(countries)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_bar, menu)
        val menuItem = menu?.findItem(R.id.search_bar_item)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Entrer un pays ou une capitale"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                recyclerView.adapter = null
                synchroSearch(removeSpecialChars(query.lowercase()))
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                recyclerView.adapter = null
                synchroSearch(removeSpecialChars(newText.lowercase()))
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun synchroSearch(query: String) {
        var countriesSearched = countries.filter { removeSpecialChars(it.capital[0].lowercase()).contains(query) ||
                removeSpecialChars(it.frenchName.lowercase()).contains(query) ||
                removeSpecialChars(it.officialName.lowercase()).contains(query)
        }
        progressBar.visibility = View.GONE
        val adapter = CountryAdapter(countriesSearched)
        recyclerView.adapter = adapter

    }

    private fun removeSpecialChars(input: String): String {
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        val normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
        val withoutAccents = pattern.matcher(normalized).replaceAll("")
        return withoutAccents.replace("[\\s-]".toRegex(), "")
    }


    private fun synchro() {
        var countryResults = listOf<CountryResult>()

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
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

                countryResults = try {
                    countriesService.getAllCountries()
                } catch (e: Exception) {
                    Log.e("myTag", "Error fetching countries", e)
                    emptyList<CountryResult>()
                }
                countries = countryResults.map {
                    Country(
                        it.name.common,
                        it.translations.fra.common,
                        if (it.capital.isEmpty()) listOf("") else it.capital,
                        if (it.continents.isEmpty()) listOf("") else it.continents,
                        it.flags.png,
                        it.latlng
                    )
                }
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