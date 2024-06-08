package fr.epf.min2.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.epf.min2.projetandroid.data.addCountryToList
import fr.epf.min2.projetandroid.data.isCountryInFavorisList
import fr.epf.min2.projetandroid.data.removeCountryFromFavorisList
import fr.epf.min2.projetandroid.model.Country
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.util.GeoPoint


class CountryDetailsActivity : AppCompatActivity() {
    private var country: Country? = null
    private lateinit var map: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_county_details)

        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID


        map = findViewById<MapView>(R.id.countryMaps_mapView)
        map.setBuiltInZoomControls(true)
        map.setMultiTouchControls(true)

        val flagImageView = findViewById<ImageView>(R.id.country_flag_details_imageView)
        val frNameTextView = findViewById<TextView>(R.id.country_name_fr_details_textView)
        val englishNameTextView = findViewById<TextView>(R.id.counrty_english_name_details_textView)
        val capitalTextView = findViewById<TextView>(R.id.counrty_capital_details_textView)
        val continentTextView = findViewById<TextView>(R.id.counrty_continent_details_textView)

        intent.extras?.apply {
            country = getParcelable(COUNTRY_ID_EXTRA) as? Country
            country?.let {
                Picasso.get()
                    .load(it.flag)
                    .into(flagImageView)
                frNameTextView.text = it.frenchName
                englishNameTextView.text = it.officialName
                capitalTextView.text = it.capital.get(0)
                continentTextView.text = it.continent.get(0)
                val geoPoint = GeoPoint(it.latlng[0], it.latlng[1])
                addMarker(geoPoint, it.officialName)
                map.controller.setZoom(5.0)
                map.controller.setCenter(geoPoint)
            }
        }
    }

    private fun addMarker(point: GeoPoint, title: String) {
        val marker = Marker(map)
        marker.position = point
        marker.title = title
        map.overlays.add(marker)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favoris_menu, menu)
        updateFavorisIcon(menu?.findItem(R.id.star_favoris_item))
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.star_favoris_item -> {
                countryAsFavoris()
                updateFavorisIcon(item)
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateFavorisIcon(item: MenuItem?) {
        val isCountryFavoris = country?.let { isCountryInFavorisList(this, it) } ?: false
        if (isCountryFavoris) {
            item?.setIcon(R.drawable.baseline_star_24)
        } else {
            item?.setIcon(R.drawable.baseline_star_border_24)
        }
    }

    private fun countryAsFavoris() {
        country?.let {
            if (isCountryInFavorisList(this, it)) {
                removeCountryFromFavorisList(this, it)
            } else {
                addCountryToList(this, it)
            }
        }
    }
}