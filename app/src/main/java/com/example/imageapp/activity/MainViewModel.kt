package com.example.imageapp.activity

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.imageapp.helper.ResponseItem
import com.example.imageapp.repository.DataModification

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun getProjectList(context: Context): ArrayList<ResponseItem> {
        return DataModification().fetchData(context)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel","Model cleared")
    }
}