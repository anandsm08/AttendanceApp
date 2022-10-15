package com.example.viewmodeldemo

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import com.example.attendanceapp.model.Record

class MusicPreferences(private val context: Context) {

    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = "checkset"
    )


}