package com.example.mvvmrealm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuoteDao {

    private val qouteList = mutableListOf<Quote>()
    private val qoutes = MutableLiveData<List<Quote>>()

    init {
        qoutes.value = qouteList
    }

    fun addQoute(quote: Quote) {
        qouteList.add(quote)
        qoutes.value = qouteList
    }

    fun getQoutes() = qoutes as LiveData<List<Quote>>
}