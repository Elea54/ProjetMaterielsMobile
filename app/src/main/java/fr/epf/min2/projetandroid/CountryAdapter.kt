package fr.epf.min2.projetandroid

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.epf.min2.projetandroid.model.Country

const val COUNTRY_ID_EXTRA = "countryId"
class CountryViewHolder(view : View) : RecyclerView.ViewHolder(view)

class CountryAdapter(val coutries: List<Country>)  : RecyclerView.Adapter<CountryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.country_view, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount() = coutries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = coutries[position]
        val view = holder.itemView

        val countryNameTextView = view.findViewById<TextView>(R.id.counrty_name_textview)
        countryNameTextView.text = country.frenchName

        val countryCapitalTextView = view.findViewById<TextView>(R.id.counrty_capital_textview)
        countryCapitalTextView.text = country.capital.get(0)

        val imageView = view.findViewById<ImageView>(R.id.courty_view_imageview)
        Picasso.get()
            .load(country.flag)
            .into(imageView)

        val cardVIew = view.findViewById<CardView>(R.id.country_view_cardview)

        cardVIew.click {
            with(it.context){
                val intent = Intent(this, CountryDetailsActivity::class.java)
                intent.putExtra(COUNTRY_ID_EXTRA, country)
                startActivity(intent)
            }
        }
    }
}
