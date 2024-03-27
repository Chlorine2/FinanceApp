package com.app.financeapp.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "FinanceTable")
data class FinanceItem (
    @PrimaryKey
    val id : Int ?= null,
    val total : Int,
    val account : String,
    val category: String,
    val date : String

)

data class CategoryTotalSum(
    val category: String,
    val total: Int

)