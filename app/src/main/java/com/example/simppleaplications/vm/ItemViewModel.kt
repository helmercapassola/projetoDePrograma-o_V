package com.example.simppleaplications.vm

import com.example.simppleaplications.data.AppDatabase
import com.example.simppleaplications.data.Item
import com.example.simppleaplications.repository.ItemRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: ItemRepository

    val items = AppDatabase.getInstance(application).itemDao().let { dao ->
        repo = ItemRepository(dao)
        repo.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun insert(title: String, description: String, imageUri: String?) {
        viewModelScope.launch {
            repo.insert(Item(title = title, description = description, imageUri = imageUri))
        }
    }

    suspend fun getById(id: Long): Item? = repo.getById(id)
}
