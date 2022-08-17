package com.ezzy.cashapp.data.repository

import com.ezzy.cashapp.data.database.dao.CashEntryDao
import com.ezzy.cashapp.data.model.CashEntry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CashEntryRepository @Inject constructor(
    private val cashEntryDao: CashEntryDao
) {
    suspend fun addEntry(entry: CashEntry) =
        cashEntryDao.addEntry(entry)

    fun getEntries(): Flow<List<CashEntry>> =
        cashEntryDao.getEntries()

    fun getSum(): Flow<Float?> = cashEntryDao
        .getSum()

    suspend fun clearEntries() = cashEntryDao.clearEntries()
}