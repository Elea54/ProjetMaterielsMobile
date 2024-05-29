package fr.epf.min2.projetandroid.data

import android.content.Context
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
    val gson = Gson()
    val jsonString = File(context.filesDir, "countries.json").readText()
    val listType = object : TypeToken<List<Country>>() {}.type
    return gson.fromJson(jsonString, listType)
}

fun addCountryToList(context: Context, newCountry: Country) {
    val list = readListFromJson(context).toMutableList()
    list.add(newCountry)
    saveListToJson(context, list)
}