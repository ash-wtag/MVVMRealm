package com.example.mvvmrealm.data

class FakeDB private constructor(){

    var quoteDao = FakeQuoteDao()
        private set

    companion object {

        @Volatile private var instance: FakeDB? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDB().also {
                    instance = it
                }
            }
    }
}