package com.example.myforegroundservice2.useCases

interface SharedRepository {

    fun set(any: Any, key: String)
    fun get(key: String): Any?
    fun clear()

}