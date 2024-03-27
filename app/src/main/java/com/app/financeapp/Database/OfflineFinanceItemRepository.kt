package com.app.financeapp.Database

import kotlinx.coroutines.flow.Flow

class OfflineFinanceItemRepository(private val financeDao: FinanceDao) : FinanceItemsRepository {
    override suspend fun insert(item: FinanceItem) = financeDao.insert(item)

    override suspend fun update(item: FinanceItem) = financeDao.update(item)

    override suspend fun delete(id: Int) = financeDao.delete(id)
    override fun getUniqueItems(): Flow<List<String>> = financeDao.getUniqueCategory()

    override fun getSpendingItems(): Flow<List<FinanceItem>> = financeDao.getSpendingItems()

    override fun getIncomeItems(): Flow<List<FinanceItem>> = financeDao.getIncomeItems()

    override fun getItem(id: Int): Flow<FinanceItem>  = financeDao.getItem(id)
    override fun getUniqueCategoriesWithTotalSums(): Flow<List<CategoryTotalSum>> = financeDao.getUniqueCategoriesWithTotalSums()
}