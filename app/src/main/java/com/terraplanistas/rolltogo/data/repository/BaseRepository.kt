package com.terraplanistas.rolltogo.data.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun getElements(): Flow<List<T>>

    suspend fun addElement(t: T)

    suspend fun addElements(ts: List<T>)

    suspend fun removeElement(t: T)
}