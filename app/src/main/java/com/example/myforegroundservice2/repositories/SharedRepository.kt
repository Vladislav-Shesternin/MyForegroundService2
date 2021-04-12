package com.example.myforegroundservice2.repositories

import android.content.Context
import com.example.myforegroundservice2.useCases.SharedRepositoryUseCase

const val KEY_SAVED_OBJECT = "app_saved_object"

class SharedRepository(
    private val context: Context
) : SharedRepositoryUseCase {

    companion object {
        private const val NAME_SHARED_REPOSITORY = "app_shared_repository"
    }

    private val sharedRepo =
        context.getSharedPreferences(NAME_SHARED_REPOSITORY, Context.MODE_PRIVATE)

    override fun set(any: Any, key: String) {
        sharedRepo.edit().apply {
            when (any) {
                is Int -> {
                    putInt(key, any)
                }
                // Other types
            }
        }.apply()
    }

    override fun get(key: String): Any? {
        return try {
            sharedRepo.all.getValue(key)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun clear() {
        sharedRepo.edit().clear().apply()
    }
}