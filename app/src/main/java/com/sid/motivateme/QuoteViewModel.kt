package com.sid.motivateme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sid.motivateme.quotenetwork.QuoteNetworkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {

    val allQuotes: LiveData<List<QuoteEntity>> = repository.allQuotes.asLiveData()
    val isFirstRun  = repository.isFirstRun().asLiveData()
    val quotesInstall  = MutableLiveData<Boolean>()

    fun insert(quote: QuoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(quote)
    }

    fun update(quote: QuoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(quote)
    }

    fun delete(quote: QuoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(quote)
    }

    fun getQuoteById(id: Int): LiveData<QuoteEntity?> {
        return repository.getQuoteById(id).asLiveData()
    }

    fun insertQuotes(quotes: List<QuoteEntity>) = viewModelScope.launch {
        repository.insertQuotes(quotes)
    }


    fun markFirstRunAsCompleted() = viewModelScope.launch {
        repository.setFirstRunCompleted()
    }

   suspend fun insertAllQuotes(){
       quotesInstall.postValue(false)
       viewModelScope.async {
          repository.insertQuotes( repository.getQuoteList())
       }.await()
       quotesInstall.postValue(true)
   }


   fun getQuoteListSize() : Int{
       return repository.getQuoteList().size
   }



    fun getRandomQuote(): LiveData<List<QuoteNetworkEntity>> {
        return repository.getRandomQuoteFlow().asLiveData()
    }

    // Method to fetch a random quote
    fun fetchRandomQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRandomQuote()
        }
    }

}
