package com.jimenard.stockapp.repository.database

import androidx.room.Dao
import com.jimenard.stockapp.repository.model.Invoice

@Dao
abstract class  InvoiceDao : GenericDao<Invoice>{
}