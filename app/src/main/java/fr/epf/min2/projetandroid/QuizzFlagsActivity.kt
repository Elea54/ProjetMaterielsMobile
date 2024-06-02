package fr.epf.min2.projetandroid

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import fr.epf.min2.projetandroid.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.random.Random
class QuizzFlagsActivity : AppCompatActivity() {

    lateinit var quizzProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_flags)
        quizzProgressBar = findViewById<ProgressBar>(R.id.quizz_progress_bar)
        val countryName = findViewById<TextView>(R.id.country_name_quizz_textView)
        countryName.text = ""
        showPopup("Trouve le bon drapeau !", this)
        lifecycleScope.launch {
            quizzProgressBar.visibility = View.VISIBLE
            var countries = getAllCountriesFromAPI()
//            var countries = getCountriesIfNotAPI()
            Log.d("myTag", countries.toString())
            quizzProgressBar.visibility = View.GONE
            commencementQuizz(countries)
        }
    }

    private fun getCountriesIfNotAPI(): List<Country> {
        return Country.generateCountryList()
    }

    private suspend fun getAllCountriesFromAPI(): List<Country> = withContext(Dispatchers.IO) {
        var countryResults = listOf<CountryResult>()
        var newCountries = listOf<Country>()

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

        try {
            countryResults = countriesService.getAllCountries()
        } catch (e: Exception) {
            Log.e("myTag", "Error fetching countries", e)
        }

        newCountries = countryResults.map {
            Country(
                it.name.common,
                it.translations.fra.common,
                if (it.capital.isEmpty()) listOf("") else it.capital,
                if (it.continents.isEmpty()) listOf("") else it.continents,
                it.flags.png,
                it.latlng
            )
        }

        return@withContext newCountries
    }

    private fun commencementQuizz(countriesList : List<Country>) {
        var listOfSelectedCountries = mutableListOf<Country>()
        val countryName = findViewById<TextView>(R.id.country_name_quizz_textView)
        val flag1ImageView = findViewById<ImageView>(R.id.flag3_imageView)
        val flag2ImageView = findViewById<ImageView>(R.id.flag2_imageView)
        val flag3ImageView = findViewById<ImageView>(R.id.flag1_imageView)


        val randomMax = countriesList.size-1
        val indexRandomCountryToFind = Random.nextInt(0, randomMax+1)
        val countryToFind = countriesList.get(indexRandomCountryToFind)
        Log.d("myTag", countryToFind.toString())
        listOfSelectedCountries.add(countryToFind)
        countryName.text = countryToFind.frenchName

        var indexRandomCountry2 = Random.nextInt(0, randomMax+1)
        while (indexRandomCountry2 == indexRandomCountryToFind){
            indexRandomCountry2 = Random.nextInt(0, randomMax+1)
        }
        listOfSelectedCountries.add(countriesList.get(indexRandomCountry2))

        var indexRandomCountry3 = Random.nextInt(0, randomMax+1)
        while (indexRandomCountry3 == indexRandomCountryToFind || indexRandomCountry3 == indexRandomCountry2){
            indexRandomCountry3 = Random.nextInt(0, randomMax+1)
        }

        listOfSelectedCountries.add(countriesList.get(indexRandomCountry3))
        listOfSelectedCountries.shuffle()

        Picasso.get()
            .load(listOfSelectedCountries.get(0).flag)
            .into(flag1ImageView)

        Picasso.get()
            .load(listOfSelectedCountries.get(1).flag)
            .into(flag2ImageView)

        Picasso.get()
            .load(listOfSelectedCountries.get(2).flag)
            .into(flag3ImageView)

        flag1ImageView.setOnClickListener {
            checkAnswer(listOfSelectedCountries[0], countryToFind, countriesList)
        }

        flag2ImageView.setOnClickListener {
            checkAnswer(listOfSelectedCountries[1], countryToFind, countriesList)
        }

        flag3ImageView.setOnClickListener {
            checkAnswer(listOfSelectedCountries[2], countryToFind, countriesList)
        }
    }

    private fun checkAnswer(selectedCountry: Country, countryToFind: Country, countriesList: List<Country>) {
        if (selectedCountry == countryToFind) {
            commencementQuizz(countriesList)
        } else {
            showPopup("Ce n'est pas le bon drapeau !", this)
        }
    }


}


