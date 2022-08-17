package com.ezzy.cashapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezzy.cashapp.data.database.CashRegisterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCashEntryDatabase(
        @ApplicationContext context: Context
    ): CashRegisterDatabase = Room.databaseBuilder(
        context,
        CashRegisterDatabase::class.java,
        "cash_register_db"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideCashEntryDao(database: CashRegisterDatabase) =
        database.cashEntryDao()

}