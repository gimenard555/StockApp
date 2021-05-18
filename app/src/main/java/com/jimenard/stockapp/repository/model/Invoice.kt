package com.jimenard.stockapp.repository.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = Invoice.TABLE_NAME)
class Invoice(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = INVOICE_NUMBER)
    var invoiceNumber: Int,

    @NonNull
    @ColumnInfo(name = PRODUCTS)
    var products: List<Stock>,

    @NonNull
    @ColumnInfo(name = CUSTOMER)
    var customer: Int,

    @NonNull
    @ColumnInfo(name = DATE)
    var date: String,

    @NonNull
    @ColumnInfo(name = TOTAL)
    var total: Double
) {
    companion object {
        const val TABLE_NAME = "Invoice"
        const val PRODUCTS = "products"
        const val CUSTOMER = "customer"
        const val DATE = "date"
        const val TOTAL = "total"
        const val INVOICE_NUMBER = "invoiceNumber"
    }
}