package com.faezolfp.enstoreapp.core.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class Sapareferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val GRETTING_TEXT = stringPreferencesKey("gretting_text")

    fun getGrettingText(): Flow<String>{
        return dataStore.data.map { preferences ->
            preferences[GRETTING_TEXT] ?: "Have Nice Day :)"
        }
    }

    suspend fun saveGrettingText(dataGreeting: String){
        dataStore.edit { preferences->
            preferences[GRETTING_TEXT] = dataGreeting
        }
    }

    private val IS_LOGIN = booleanPreferencesKey("is_login")
    private val ID_USER = intPreferencesKey("is_iduser")

    fun getIsLogin(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }
    }

    suspend fun saveIsLogin(dataLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGIN] = dataLogin
        }
    }

    suspend fun saveIdUser(idUser: Int) {
        dataStore.edit { preferences ->
            preferences[ID_USER] = idUser
        }
    }

    fun getIdUser(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[ID_USER] ?: 0
        }
    }

}
