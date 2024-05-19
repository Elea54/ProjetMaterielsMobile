package fr.epf.min2.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min2.projetandroid.model.Country

class ListCountriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_countries)

        var recyclerView = findViewById<RecyclerView>(R.id.list_countries_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val countries = Country.generateCountryList()
        recyclerView.adapter = CountryAdapter(countries)
    }
}