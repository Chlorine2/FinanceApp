package com.app.financeapp.Database

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface FinanceItemsRepository {

    suspend fun insert(item : FinanceItem)

    suspend fun update(item : FinanceItem)

    suspend fun  delete(id: Int)

    fun getSpendingItems() : Flow<List<FinanceItem>>

    fun getIncomeItems() : Flow<List<FinanceItem>>

    fun getItem(id : Int) : Flow<FinanceItem>
}