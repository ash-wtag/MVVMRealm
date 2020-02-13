package com.example.mvvmrealm

import androidx.lifecycle.ViewModel
import com.example.mvvmrealm.data.Quote
import com.example.mvvmrealm.data.QuoteRepository

class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}