package com.sid.motivateme

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao {
        return quoteDatabase.quoteDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): QuoteDatabase {
        return QuoteDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext appContext: Context): SettingsDataStore {
        return SettingsDataStore(appContext)
    }
}
