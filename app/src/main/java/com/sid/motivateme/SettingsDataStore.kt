package com.sid.motivateme

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings")

class SettingsDataStore @Inject constructor(private val context: Context) {

    private val FIRST_RUN_KEY = booleanPreferencesKey("first_run")

    // Check if it's the first run
    val isFirstRun: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[FIRST_RUN_KEY] ?: true
        }

    // Set first run to false
    suspend fun setFirstRunCompleted() {
        context.dataStore.edit { preferences ->
            preferences[FIRST_RUN_KEY] = false
        }
    }
}
