package com.jimenard.stockapp.repository.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Stock.TABLE_NAME)
data class Stock(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PRODUCT_ID)
    var productId: Int = 0,

    @NonNull
    @ColumnInfo(name = PRODUCT_NAME)
    var productName: String,

    @NonNull
    @ColumnInfo(name = PRODUCT_QUANTITY)
    var productQuantity: Int,

    @NonNull
    @ColumnInfo(name = PRODUCT_IMAGE)
    var productImage: String,

    @NonNull
    @ColumnInfo(name = PRODUCT_PRICE)
    var productPrice: String
) {

    companion object {
        const val TABLE_NAME = "product"
        const val PRODUCT_NAME = "productName"
        const val PRODUCT_QUANTITY = "productQuantity"
        const val PRODUCT_IMAGE = "productImage"
        const val PRODUCT_PRICE = "productPrice"
        const val PRODUCT_ID = "productId"
    }
}