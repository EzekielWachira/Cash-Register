package com.ezzy.cashapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ezzy.cashapp.data.model.CashEntry
import com.ezzy.cashapp.data.repository.CashEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CashEntryViewModel @Inject constructor(
    private val cashEntryRepository: CashEntryRepository
): ViewModel() {

    val cashEntries = liveData {
        cashEntryRepository.getEntries().collect { emit(it) }
    }

    val totalEntriesAmount = liveData {
        cashEntryRepository.getSum().collect { emit(it) }
    }

    fun addEntry(entry: CashEntry) = viewModelScope.launch {
        cashEntryRepository.addEntry(entry)
    }

    fun clearEntries() = viewModelScope.launch {
        cashEntryRepository.clearEntries()
    }

}