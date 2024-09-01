package com.sid.motivateme

import com.sid.motivateme.quotenetwork.QuoteApiService
import com.sid.motivateme.quotenetwork.QuoteNetworkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    private val apiService: QuoteApiService
) {



    fun isFirstRun(): Flow<Boolean> {
        return settingsDataStore.isFirstRun
    }

    suspend fun setFirstRunCompleted() {
        settingsDataStore.setFirstRunCompleted()
    }





    suspend fun getRandomQuote(): List<QuoteNetworkEntity> {
        return apiService.getRandomQuote()
    }

    fun getRandomQuoteFlow(): Flow<List<QuoteNetworkEntity>> = flow {
        emit(apiService.getRandomQuote())
    }



}
