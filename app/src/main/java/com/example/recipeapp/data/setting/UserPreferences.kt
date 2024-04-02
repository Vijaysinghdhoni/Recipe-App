package com.example.recipeapp.data.setting

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val context: Context, name: String) {

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = name)
    private val prefDataStore = context.preferencesDataStore

    private val booleanKey = booleanPreferencesKey("userLoginStatus")
    fun readIntPref(): Flow<Boolean?> {
        return prefDataStore.data.map { preferences ->
            preferences[booleanKey]
        }
    }

    suspend fun writeIntPref(value : Boolean) {
        context.preferencesDataStore.edit { settings ->
            settings[booleanKey] = value
        }
    }

    suspend fun clearIntPref() {
        context.preferencesDataStore.edit { settings ->
            settings.remove(booleanKey)
        }
    }


}