package com.app.financeapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.financeapp.Database.CategoryTotalSum
import com.app.financeapp.Database.FinanceItem
import com.app.financeapp.Database.FinanceItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DBViewModel(private val itemRepository: FinanceItemsRepository) : ViewModel() {

    init {
        viewModelScope.launch {

        }
    }
    fun insert(item: FinanceItem) = viewModelScope.launch {

        itemRepository.insert(item)
    }
    fun getAllSpendingItems() : Flow<List<FinanceItem>>{
        return itemRepository.getSpendingItems()
    }

    fun getUniqueSpendingCategoriesWithTotalSums() : Flow<List<CategoryTotalSum>>{
        return itemRepository.getUniqueSpendingCategoriesWithTotalSums()
    }

    fun getUniqueIncomeCategoriesWithTotalSums() : Flow<List<CategoryTotalSum>>{
        return itemRepository.getUniqueIncomeCategoriesWithTotalSums()
    }
}