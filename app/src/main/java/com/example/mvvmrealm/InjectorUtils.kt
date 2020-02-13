package com.example.mvvmrealm

import com.example.mvvmrealm.data.FakeDB
import com.example.mvvmrealm.data.QuoteRepository

object InjectorUtils {

    fun provideQuotesViewModelFactory(): QuoteViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDB.getInstance().quoteDao)
        return QuoteViewModelFactory(quoteRepository)
    }

}