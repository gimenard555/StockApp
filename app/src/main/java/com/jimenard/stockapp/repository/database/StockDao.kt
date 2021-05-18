package com.jimenard.stockapp.repository.database

import androidx.room.Dao
import androidx.room.Query
import com.jimenard.stockapp.repository.model.Stock

@Dao
abstract class StockDao : GenericDao<Stock> {

    @Query("SELECT * FROM " + Stock.TABLE_NAME)
    abstract suspend fun getProducts(): List<Stock>
}