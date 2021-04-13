package com.example.myforegroundservice2.repositories

import android.content.Context
import com.example.myforegroundservice2.useCases.SharedRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val KEY_SAVED_OBJECT = "app_saved_object"

class SharedRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
) : SharedRepository {

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