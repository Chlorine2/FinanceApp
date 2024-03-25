package com.app.financeapp

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.app.financeapp.ViewModels.PersistViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            PersistViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}

fun CreationExtras.inventoryApplication(): FinanceApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinanceApplication)