package com.jimenard.stockapp.ui.views

import android.view.View
import com.jimenard.stockapp.R
import com.jimenard.stockapp.repository.model.Customer
import com.jimenard.stockapp.ui.util.AdapterObject
import kotlinx.android.synthetic.main.item_customer_view_layout.view.*

class ACustomer(layout: Int = R.layout.item_customer_view_layout) :
    AdapterObject<Customer>(layout) {

    override fun chargeView(
        customer: Customer,
        view: View,
        position: Int
    ): View {
        view.customer_name.text = customer.customerName
        view.customer_phone_number.text = customer.customerPhone
        return view.item_container
    }

    override fun changeViewState(lastView: View, currentView: View, itemRecyclerView: Customer?) {
    }
}