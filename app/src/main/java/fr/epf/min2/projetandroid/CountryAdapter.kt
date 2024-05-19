package fr.epf.min2.projetandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.epf.min2.projetandroid.model.Country

class CountryViewHolder(view : View) : RecyclerView.ViewHolder(view)

class CountryAdapter(val coutries: ArrayList<Country>)  : RecyclerView.Adapter<CountryViewHolder>(){
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
        countryCapitalTextView.text = country.capital

        val imageView = view.findViewById<ImageView>(R.id.courty_view_imageview)
        Picasso.get()
            .load(country.flag)
            .into(imageView)

        val cardVIew = view.findViewById<CardView>(R.id.country_view_cardview)
    }
}