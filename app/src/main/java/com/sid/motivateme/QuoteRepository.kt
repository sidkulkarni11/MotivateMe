package com.sid.motivateme

import com.sid.motivateme.quotenetwork.QuoteApiService
import com.sid.motivateme.quotenetwork.QuoteNetworkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteDao: QuoteDao,
    private val settingsDataStore: SettingsDataStore,
    private val apiService: QuoteApiService
) {

    val allQuotes: Flow<List<QuoteEntity>> = quoteDao.getAllQuotes()


    suspend fun insert(quote: QuoteEntity) {
        quoteDao.insert(quote)
    }

    suspend fun update(quote: QuoteEntity) {
        quoteDao.update(quote)
    }

    suspend fun delete(quote: QuoteEntity) {
        quoteDao.delete(quote)
    }

    fun getQuoteById(quoteId: Int): Flow<QuoteEntity?> {
        return quoteDao.getQuoteById(quoteId)
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertQuotes(quotes)
    }

    fun isFirstRun(): Flow<Boolean> {
        return settingsDataStore.isFirstRun
    }

    suspend fun setFirstRunCompleted() {
        settingsDataStore.setFirstRunCompleted()
    }

     fun  getQuoteList(): List<QuoteEntity> {
        val quotes = listOf(
            "The greatest glory in living lies not in never falling, but in rising every time we fall.",
            "The way to get started is to quit talking and begin doing.",
            "Life is what happens when you're busy making other plans.",
            "The purpose of our lives is to be happy.",
            "Get busy living or get busy dying.",
            "You only live once, but if you do it right, once is enough.",
            "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.",
            "In the end, it's not the years in your life that count. It's the life in your years.",
            "Life is really simple, but we insist on making it complicated.",
            "The best way to predict your future is to create it.",
            "Don't watch the clock; do what it does. Keep going.",
            "Success usually comes to those who are too busy to be looking for it.",
            "You miss 100% of the shots you don’t take.",
            "The only impossible journey is the one you never begin.",
            "Act as if what you do makes a difference. It does.",
            "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            "Believe you can and you're halfway there.",
            "The only limit to our realization of tomorrow is our doubts of today.",
            "In three words I can sum up everything I've learned about life: it goes on.",
            "To live is the rarest thing in the world. Most people exist, that is all.",
            "Life isn't about finding yourself. Life is about creating yourself.",
            "We do not remember days; we remember moments.",
            "The only way to do great work is to love what you do.",
            "Life is short, and it is up to you to make it sweet.",
            "The best revenge is massive success.",
            "Your time is limited, don't waste it living someone else's life.",
            "The only person you are destined to become is the person you decide to be.",
            "Go confidently in the direction of your dreams. Live the life you have imagined.",
            "Success is not how high you have climbed, but how you make a positive difference to the world.",
            "We are what we repeatedly do. Excellence, then, is not an act, but a habit.",
            "The future belongs to those who believe in the beauty of their dreams.",
            "It does not matter how slowly you go as long as you do not stop.",
            "You do not find the happy life. You make it.",
            "Life is either a daring adventure or nothing at all.",
            "The only way to achieve the impossible is to believe it is possible.",
            "The journey of a thousand miles begins with one step.",
            "Dream big and dare to fail.",
            "Nothing in life is to be feared, it is only to be understood.",
            "Life is what we make it, always has been, always will be.",
            "The best preparation for tomorrow is doing your best today.",
            "You are never too old to set another goal or to dream a new dream.",
            "Everything you've ever wanted is on the other side of fear.",
            "The secret of getting ahead is getting started.",
            "Do not wait to strike till the iron is hot, but make it hot by striking.",
            "Life is made of ever so many partings welded together.",
            "In the middle of every difficulty lies opportunity.",
            "What lies behind us and what lies before us are tiny matters compared to what lies within us.",
            "Start where you are. Use what you have. Do what you can.",
            "You are not only responsible for what you say, but also for what you do not say.",
            "Happiness is not something ready made. It comes from your own actions.",
            "The mind is everything. What you think you become.",
            "The best way to find yourself is to lose yourself in the service of others.",
            "Your life does not get better by chance, it gets better by change.",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it.",
            "Don’t be pushed around by the fears in your mind. Be led by the dreams in your heart.",
            "The greatest glory in living lies not in never falling, but in rising every time we fall.",
            "Don’t let the noise of others’ opinions drown out your own inner voice.",
            "Do not go where the path may lead, go instead where there is no path and leave a trail.",
            "You have within you right now, everything you need to deal with whatever the world can throw at you.",
            "Everything has beauty, but not everyone can see.",
            "To live is the rarest thing in the world. Most people exist, that is all.",
            "Success is not how high you have climbed, but how you make a positive difference to the world.",
            "In the end, it's not the years in your life that count. It's the life in your years.",
            "We do not remember days; we remember moments.",
            "The only limit to our realization of tomorrow is our doubts of today.",
            "Life is what happens when you're busy making other plans.",
            "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.",
            "The only impossible journey is the one you never begin.",
            "Your time is limited, don't waste it living someone else's life.",
            "Dream big and dare to fail.",
            "Act as if what you do makes a difference. It does.",
            "Success usually comes to those who are too busy to be looking for it.",
            "Believe you can and you're halfway there.",
            "Life isn't about finding yourself. Life is about creating yourself.",
            "The future belongs to those who believe in the beauty of their dreams.",
            "Start where you are. Use what you have. Do what you can.",
            "The only way to achieve the impossible is to believe it is possible.",
            "What we think, we become.",
            "You are never too old to set another goal or to dream a new dream.",
            "The best revenge is massive success.",
            "Life is really simple, but we insist on making it complicated.",
            "The only person you are destined to become is the person you decide to be.",
            "The journey of a thousand miles begins with one step.",
            "You do not find the happy life. You make it.",
            "Everything you've ever wanted is on the other side of fear.",
            "The mind is everything. What you think you become."
        )



        // Combine both lists and remove duplicates
        val allQuotes = (quotes).distinct()


        // Create a list of QuoteEntity with unique quotes
        val quoteEntities = allQuotes.map { quote ->
            QuoteEntity(quote = quote)
        }
        return quoteEntities
    }




    suspend fun getRandomQuote(): List<QuoteNetworkEntity> {
        return apiService.getRandomQuote()
    }

    fun getRandomQuoteFlow(): Flow<List<QuoteNetworkEntity>> = flow {
        emit(apiService.getRandomQuote())
    }


}
