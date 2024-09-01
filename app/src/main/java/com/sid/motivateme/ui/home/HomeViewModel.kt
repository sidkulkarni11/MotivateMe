package com.sid.motivateme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sid.motivateme.QuoteRepository
import com.sid.motivateme.quotenetwork.QuoteNetworkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor(private val repository: QuoteRepository) : ViewModel() {

    val isFirstRun  = repository.isFirstRun().asLiveData()
    val quotesInstall  = MutableLiveData<Boolean>()

    fun markFirstRunAsCompleted() = viewModelScope.launch(Dispatchers.IO) {
        repository.setFirstRunCompleted()
    }

    fun getRandomQuote(): LiveData<List<QuoteNetworkEntity>> {
        return repository.getRandomQuoteFlow().asLiveData()
    }

    fun fetchRandomQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRandomQuote()
        }
    }

}