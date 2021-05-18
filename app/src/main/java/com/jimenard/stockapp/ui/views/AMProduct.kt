package com.jimenard.stockapp.ui.views

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import com.bumptech.glide.Glide
import com.jimenard.stockapp.R
import com.jimenard.stockapp.repository.model.Stock
import com.jimenard.stockapp.ui.util.AdapterObject
import kotlinx.android.synthetic.main.item_product_market_layout.view.*

class AMProduct(layout: Int = R.layout.item_product_market_layout) :
    AdapterObject<Stock>(layout) {

    override fun chargeView(
        product: Stock,
        view: View,
        position: Int
    ): View {
        val displayMetrics = DisplayMetrics()
        ((view.context) as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels / 4
        view.image.layoutParams.height = width
        view.image.layoutParams.width = width
        Glide.with(view)
            .load(product.productImage)
            .centerCrop()
            .into(view.image)
        view.product_name.text = product.productName
        view.product_price.text = product.productPrice
        return view.item_container
    }

    override fun changeViewState(lastView: View, currentView: View, itemRecyclerView: Stock?) {
        lastView.radio_button.isSelected = !lastView.radio_button.isSelected
    }
}