package com.example.myforegroundservice2

import com.example.myforegroundservice2.repositories.KEY_SAVED_OBJECT
import com.example.myforegroundservice2.repositories.SharedRepository

fun SharedRepository.updateCountAppOpenings(): Int {
    return (get(KEY_SAVED_OBJECT) as? Int ?: 0).also { number ->
        set(number.inc(), KEY_SAVED_OBJECT)
    }.inc()
}

fun SharedRepository.countAppOpenings(): Int {
    return (get(KEY_SAVED_OBJECT) as? Int ?: 1).also { number ->
        if (number == 1) set(number, KEY_SAVED_OBJECT)
    }
}
