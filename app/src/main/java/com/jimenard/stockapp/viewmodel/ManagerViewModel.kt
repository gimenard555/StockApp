package com.jimenard.stockapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jimenard.stockapp.repository.ManagerRepository
import com.jimenard.stockapp.repository.model.Customer
import com.jimenard.stockapp.repository.model.Stock
import javax.inject.Inject

class ManagerViewModel @Inject constructor(context: Context) : ViewModel() {

    private val managerRepository = ManagerRepository(context)

    //Customer operations
    fun saveCustomer(customer: Customer) = this.managerRepository.saveCustomer(customer)

    fun getCustomers() = this.managerRepository.getCustomers()

    fun updateCustomer(customer: Customer) = this.managerRepository.updateCustomer(customer)

    fun deleteCustomer(customer: Customer) = this.managerRepository.deleteCustomer(customer)

    //Products operations
    fun saveProduct(product: Stock) = this.managerRepository.saveProduct(product)

    fun getProducts() = this.managerRepository.getProducts()

    fun updateProduct(product: Stock) = this.managerRepository.updateProduct(product)

    fun deleteProduct(product: Stock) = this.managerRepository.deleteProduct(product)
}