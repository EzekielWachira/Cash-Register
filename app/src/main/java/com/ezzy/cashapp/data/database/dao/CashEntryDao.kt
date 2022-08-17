package com.ezzy.cashapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ezzy.cashapp.data.model.CashEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface CashEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEntry(entry: CashEntry)

    @Query("SELECT * FROM `cash_entries` ORDER BY `id` DESC")
    fun getEntries(): Flow<List<CashEntry>>

    @Query("SELECT SUM(`amount`) FROM `cash_entries`")
    fun getSum(): Flow<Float?>

    @Query("DELETE FROM `cash_entries`")
    suspend fun clearEntries()


}