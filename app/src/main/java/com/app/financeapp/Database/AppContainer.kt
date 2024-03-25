package com.app.financeapp.Database

import android.content.Context


interface AppContainer{
    val itemsRepository : FinanceItemsRepository
}

class AppDataClass(context : Context) : AppContainer{
    override val itemsRepository: FinanceItemsRepository by lazy { OfflineFinanceItemRepository(FinanceDatabase.getDatabase(context).financeDao())}
}