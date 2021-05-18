package com.jimenard.stockapp.repository

import android.content.Context
import androidx.lifecycle.liveData
import com.jimenard.stockapp.repository.database.StockDB
import com.jimenard.stockapp.repository.model.Customer
import com.jimenard.stockapp.repository.model.Invoice
import com.jimenard.stockapp.repository.model.Stock
import com.jimenard.stockapp.ui.util.logError
import kotlinx.coroutines.Dispatchers

class ManagerRepository(context: Context) {
    private val stock = StockDB.getDatabase(context)
    private val stockDao = this.stock?.getStockDao()
    private val invoiceDao = this.stock?.getInvoiceDao()
    private val customerDao = this.stock?.getCustomerDao()

    //Aqui se hace crud de los clientes <------------------------------------------------------------

    /**
     * Se inserta un nuevo cliente
     * @param customer informacion del nuevo cliente
     */
    fun saveCustomer(customer: Customer) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = customerDao?.insertRow(customer)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    /**
     * Se elimina el cliente seleccionado
     * @param customer informacion del cliente que se elimina
     */
    fun deleteCustomer(customer: Customer) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = customerDao?.deleteRow(customer)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    /**
     * Se actualiza informacion de un cliente
     * @param customer informacion del cliente que se actualiza
     */
    fun updateCustomer(customer: Customer) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = customerDao?.updateRow(customer)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    /**
     * Se obtienen todos los clientes
     */
    fun getCustomers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = customerDao?.getCustomers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    //Aqui se hace crud de los productos <------------------------------------------------------------
    /**
     * Se inserta un nuevo producto
     * @param product informacion del producto
     */
    fun saveProduct(product: Stock) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = stockDao?.insertRow(product)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    /**
     * Se elimina el producto seleccionado
     * @param product informacion del producto que se va a eliminar
     */
    fun deleteProduct(product: Stock) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = stockDao?.deleteRow(product)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    /**
     * Se actualiza la informacion del producto seleccionado
     * @param product informacion del producto que se actualiza
     */
    fun updateProduct(product: Stock) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            logError(product.toString())
            emit(Resource.success(data = stockDao?.updateRow(product)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

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


    //Aqui se hace crud de las facturas <------------------------------------------------------------

    /**
     * Se inserta una factura
     */
    fun saveInvoice(invoice: Invoice) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = invoiceDao?.insertRow(invoice)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}