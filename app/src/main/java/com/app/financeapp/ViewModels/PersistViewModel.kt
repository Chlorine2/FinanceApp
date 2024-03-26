package com.app.financeapp.ViewModels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.financeapp.Database.FinanceItem
import com.app.financeapp.Database.FinanceItemsRepository
import com.app.financeapp.Database.OfflineFinanceItemRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PersistViewModel(private val itemRepository: FinanceItemsRepository) : ViewModel() {

    init {
        viewModelScope.launch {

        }
    }
    fun insert(item: FinanceItem) = viewModelScope.launch {

        itemRepository.insert(item)
    }
}