package com.ezzy.cashapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table to store the entries
 * @param id the id of the entry when save to database
 * @param amount entry amount
 */
@Entity(tableName = "cash_entries")
data class CashEntry(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val amount: Float
)
