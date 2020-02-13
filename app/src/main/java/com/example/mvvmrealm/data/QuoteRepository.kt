package com.example.mvvmrealm.data

class QuoteRepository private constructor(private val quoteDao: FakeQuoteDao){


    fun addQuote(quote: Quote) {
        quoteDao.addQoute(quote)
    }

    fun getQuotes() = quoteDao.getQoutes()


    companion object {

        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(quoteDao: FakeQuoteDao) =
            instance?: synchronized(this) {
                instance ?: QuoteRepository(quoteDao).also {
                    instance = it
                }
            }
    }
}