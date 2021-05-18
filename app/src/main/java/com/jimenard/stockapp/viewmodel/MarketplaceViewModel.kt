package com.jimenard.stockapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jimenard.stockapp.repository.MarketPlaceRepository
import javax.inject.Inject

class MarketplaceViewModel @Inject constructor(context: Context) : ViewModel() {

    private val marketPlaceRepository = MarketPlaceRepository(context)

    fun getProducts() = this.marketPlaceRepository.getProducts()
}