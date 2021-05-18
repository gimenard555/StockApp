package com.jimenard.stockapp.repository

import android.content.Context
import androidx.lifecycle.liveData
import com.jimenard.stockapp.repository.database.StockDB
import kotlinx.coroutines.Dispatchers

class MarketPlaceRepository(context: Context) {

    private val stock = StockDB.getDatabase(context)
    private val stockDao = this.stock?.getStockDao()
    private val invoiceDao = this.stock?.getInvoiceDao()
    private val customerDao = this.stock?.getCustomerDao()

    /**
     * Se obtienen todos los productos
     */
    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = stockDao?.getProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}