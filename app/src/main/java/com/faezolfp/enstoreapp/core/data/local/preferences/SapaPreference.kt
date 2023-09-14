package com.faezolfp.enstoreapp.core.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class Sapareferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val GRETTING_TEXT = stringPreferencesKey("gretting_text")
    private val IS_NIGTH = booleanPreferencesKey("is_night")

    fun getGrettingText(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[GRETTING_TEXT] ?: "Have Nice Day :)"
        }
    }

    suspend fun saveGrettingText(dataGreeting: String) {
        dataStore.edit { preferences ->
            preferences[GRETTING_TEXT] = dataGreeting
        }
    }

    fun isNigth(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_NIGTH] ?: false
        }
    }

    suspend fun saveIsNight(isNight: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_NIGTH] = isNight
        }
    }
}
