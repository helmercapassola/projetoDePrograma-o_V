package com.example.simppleaplications.repository


import com.example.simppleaplications.data.Item
import com.example.simppleaplications.data.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val dao: ItemDao) {
    fun getAll(): Flow<List<Item>> = dao.getAll()
    suspend fun getById(id: Long) = dao.getById(id)
    suspend fun insert(item: Item) = dao.insert(item)
    suspend fun update(item: Item) = dao.update(item)
    suspend fun delete(item: Item) = dao.delete(item)
}
