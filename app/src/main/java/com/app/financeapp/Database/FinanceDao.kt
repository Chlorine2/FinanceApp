package com.app.financeapp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {

    @Insert
    suspend fun insert(item : FinanceItem)

    @Update
    suspend fun update(item : FinanceItem)

    @Query("DELETE FROM FinanceTable WHERE id = :id")
    suspend fun  delete(id : Int)

    @Query("SELECT * FROM FinanceTable WHERE total < 0 ORDER BY date")
    fun getSpendingItems() : Flow<List<FinanceItem>>

    @Query("SELECT * FROM FinanceTable WHERE total > 0 ORDER BY date")
    fun getIncomeItems() : Flow<List<FinanceItem>>

    @Query("SELECT * FROM FinanceTable WHERE id == :id")
    fun getItem(id : Int) : Flow<FinanceItem>

    @Query("SELECT category, SUM(total) AS total FROM FinanceTable WHERE total < 0 GROUP BY category")
    fun getUniqueSpendingCategoriesWithTotalSums(): Flow<List<CategoryTotalSum>>

    @Query("SELECT category, SUM(total) AS total FROM FinanceTable WHERE total > 0 GROUP BY category")
    fun getUniqueIncomeCategoriesWithTotalSums(): Flow<List<CategoryTotalSum>>
}