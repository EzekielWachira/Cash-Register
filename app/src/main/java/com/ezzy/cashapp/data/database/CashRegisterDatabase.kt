package com.ezzy.cashapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezzy.cashapp.data.database.dao.CashEntryDao
import com.ezzy.cashapp.data.model.CashEntry

@Database(
    entities = [
        CashEntry::class
    ],
    version = 1,
    exportSchema = true
)
abstract class CashRegisterDatabase: RoomDatabase() {
    abstract fun cashEntryDao(): CashEntryDao
}