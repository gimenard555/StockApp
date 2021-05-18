package com.jimenard.stockapp.ui.views

import android.view.View
import com.jimenard.stockapp.R
import com.jimenard.stockapp.repository.model.Stock
import com.jimenard.stockapp.ui.util.AdapterObject
import kotlinx.android.synthetic.main.item_product_view_layout.view.*

class AProduct(layout: Int = R.layout.item_product_view_layout) :
    AdapterObject<Stock>(layout) {

    override fun chargeView(
        product: Stock,
        view: View,
        position: Int
    ): View {
        view.name.text = product.productName
        view.quantity.text = product.productQuantity.toString()
        view.price.text = product.productPrice
        return view.item_container
    }

    override fun changeViewState(lastView: View, currentView: View, itemRecyclerView: Stock?) {
    }
}