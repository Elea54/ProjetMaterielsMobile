package fr.epf.min2.projetandroid

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min2.projetandroid.data.clearJsonFile
import fr.epf.min2.projetandroid.data.readListFromJson
import fr.epf.min2.projetandroid.model.Country
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


class ListFavorisActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    var countriesFavorisList = emptyList<Country>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_countries)

        recyclerView = findViewById<RecyclerView>(R.id.list_countries_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listFavoris()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_favoris_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_favoris_item -> {
                deleteConfirmationPopUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteConfirmationPopUp() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Supprimer tous les favoris ?")
        builder.setPositiveButton("Oui") { dialogInterface: DialogInterface, i: Int ->
            deleteAllFavoris()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("Non") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun listFavoris() {
        countriesFavorisList = readListFromJson(this)
        val adapter = CountryAdapter(countriesFavorisList)
        recyclerView.adapter = adapter
    }

    private fun deleteAllFavoris() {
        clearJsonFile(this)
        listFavoris()
    }


}