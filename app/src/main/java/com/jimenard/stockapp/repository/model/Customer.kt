package com.jimenard.stockapp.repository.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Customer.TABLE_NAME)
data class Customer(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CUSTOMER_NUMBER)
    var customerNumber: Int = 0,

    @NonNull
    @ColumnInfo(name = CUSTOMER_NAME)
    var customerName: String,

    @NonNull
    @ColumnInfo(name = CUSTOMER_PHONE)
    var customerPhone: String,

    @NonNull
    @ColumnInfo(name = CUSTOMER_ADDRESS)
    var customerAddress: String
) {

    companion object {
        const val TABLE_NAME = "customer"
        const val CUSTOMER_NAME = "customerName"
        const val CUSTOMER_PHONE = "customerPhone"
        const val CUSTOMER_ADDRESS = "customerAddress"
        const val CUSTOMER_NUMBER = "customerNumber"
    }
}