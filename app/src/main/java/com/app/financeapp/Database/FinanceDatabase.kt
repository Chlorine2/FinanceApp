package com.app.financeapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FinanceItem::class], version = 8, exportSchema = false)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun financeDao() : FinanceDao
    companion object{
        @Volatile
        private var Instance : FinanceDatabase? = null

        fun getDatabase(context : Context) : FinanceDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, FinanceDatabase::class.java, "finance_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}