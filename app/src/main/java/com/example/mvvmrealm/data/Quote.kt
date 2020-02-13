package com.example.mvvmrealm.data

class Quote(val title: String, val message: String) {
    override fun toString(): String {
        return "$title - $message"
    }
}