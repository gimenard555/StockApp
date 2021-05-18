package com.jimenard.stockapp.repository.database

import androidx.room.Dao
import androidx.room.Query
import com.jimenard.stockapp.repository.model.Customer

@Dao
abstract class CustomerDao : GenericDao<Customer> {

    @Query("SELECT * FROM " + Customer.TABLE_NAME)
    abstract suspend fun getCustomers():List<Customer>
}