package fr.epf.min2.projetandroid.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.epf.min2.projetandroid.model.Country
import java.io.File

fun saveListToJson(context: Context, list: List<Country>) {
    val gson = Gson()
    val jsonString = gson.toJson(list)
    File(context.filesDir, "countries.json").writeText(jsonString)
}

fun readListFromJson(context: Context): List<Country> {
    val file = File(context.filesDir, "countries.json")
    if (!file.exists() || file.readText().isEmpty()) {
        return emptyList()
    }

    val gson = Gson()
    val jsonString = file.readText()
    val listType = object : TypeToken<List<Country>>() {}.type
    val list = gson.fromJson<List<Country>>(jsonString, listType)
    return list
}

fun addCountryToList(context: Context, newCountry: Country) {
    val list = readListFromJson(context).toMutableList()
    if(!list.contains(newCountry)){
        list.add(newCountry)
        saveListToJson(context, list)
    }else{
        Log.d("myTag", "Le pays est déjà présent dans la liste.")
    }
}

fun isCountryInFavorisList(context: Context, country: Country): Boolean {
    val favorisList = readListFromJson(context)
    return favorisList.contains(country)
}

fun removeCountryFromFavorisList(context: Context, country: Country){
    val list = readListFromJson(context).toMutableList()
    if(list.contains(country)){
        list.remove(country)
        saveListToJson(context, list)
    }else{
        Log.d("myTag", "Le pays n'est pas présent dans le fichier.")
    }
}

fun clearJsonFile(context: Context) {
    val file = File(context.filesDir, "countries.json")
    if (file.exists()) {
        file.writeText("")
    } else {
        Log.d("myTag", "Le fichier JSON n'existe pas.")
    }
}