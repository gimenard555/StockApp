package com.jimenard.stockapp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jimenard.stockapp.repository.model.Converters
import com.jimenard.stockapp.repository.model.Customer
import com.jimenard.stockapp.repository.model.Invoice
import com.jimenard.stockapp.repository.model.Stock
import javax.inject.Singleton

/**
 * Listado con las tablas de la base de datos
 */
@Database(
    entities = [
        Customer::class,
        Invoice::class,
        Stock::class
    ],
    version = StockDB.VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
/**
 * Modelo de la base de datos
 */
@Singleton
abstract class StockDB : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "SMART_DB"
        const val VERSION = 1
        var INSTANCE: StockDB? = null

        fun getDatabase(context: Context): StockDB? {
            if (INSTANCE == null) {
                synchronized(StockDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            StockDB::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

    abstract fun getCustomerDao(): CustomerDao
    abstract fun getStockDao(): StockDao
    abstract fun getInvoiceDao(): InvoiceDao
}