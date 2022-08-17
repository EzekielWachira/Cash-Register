package com.ezzy.cashapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cash_entries")
data class CashEntry(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val amount: Float
)
