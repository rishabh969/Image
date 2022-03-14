package com.example.imageapp.repository


import android.content.Context
import android.util.Log
import com.example.imageapp.R
import com.example.imageapp.helper.ResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataModification {
   fun fetchData(context: Context):ArrayList<ResponseItem> {

       val objectArrayString: String =
           context.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }

       val gson = Gson()
       val listPersonType = object : TypeToken<List<ResponseItem>>() {}.type
       val dataList: ArrayList<ResponseItem> = gson.fromJson(objectArrayString, listPersonType)
       dataList.forEachIndexed { idx, person ->
           Log.i("data", "> Item $idx:\n$person")
       }
           return dataList
       }
}