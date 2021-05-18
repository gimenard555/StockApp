package com.jimenard.stockapp.ui.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.jimenard.stockapp.R
import com.jimenard.stockapp.databinding.CustomerPopUpBinding
import com.jimenard.stockapp.repository.model.Customer
import com.jimenard.stockapp.ui.util.initCurrentSettings

class CustomerPopUp(context: Context) : Dialog(context) {

    private lateinit var binding: CustomerPopUpBinding
    private var customerId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = CustomerPopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        this.binding.closeButton.setOnClickListener { closePopup() }
        if (customer != null) {
            initUpdate(customer!!)
        } else {
            this.binding.okButton.setOnClickListener {
                notifier.invoke(initCustomerData(), ProcessType.CREATE)
                closePopup()
            }
        }
    }

    private fun initCustomerData() = Customer(
        customerAddress = this.binding.customerAddress.text.toString(),
        customerNumber = this.customerId,
        customerPhone = this.binding.customerPhoneNumber.text.toString(),
        customerName = this.binding.customerName.text.toString()
    )

    private fun initUpdate(customer: Customer) {
        this.binding.deleteButton.visibility = View.VISIBLE
        this.binding.okButton.text = context.resources.getString(R.string.update_customer)
        initUpdateCustomer(customer)
        this.binding.okButton.setOnClickListener {
            notifier.invoke(initCustomerData(), ProcessType.UPDATE)
            closePopup()
        }
        this.binding.deleteButton.setOnClickListener {
            notifier.invoke(initCustomerData(), ProcessType.DELETE)
            closePopup()
        }
    }

    private fun initUpdateCustomer(customer: Customer) {
        this.binding.customerAddress.setText(customer.customerAddress)
        this.binding.customerName.setText(customer.customerName)
        this.binding.customerPhoneNumber.setText(customer.customerPhone)
        this.customerId = customer.customerNumber
    }


    companion object {

        lateinit var notifier: (Customer, ProcessType) -> Unit
        private var customerPopUp: CustomerPopUp? = null
        private var customer: Customer? = null

        /**
         * Se inicia el pop Up
         */
        fun startPopUp(
            context: Context,
            customer: Customer? = null,
            notifier: (Customer, ProcessType) -> Unit
        ) {
            this.customer = customer
            this.customerPopUp = CustomerPopUp(context)
            this.notifier = notifier
            this.customerPopUp?.initCurrentSettings(context)
        }

        /**
         * Se cierra el popUp
         */
        fun closePopup() {
            this.customerPopUp?.dismiss()
        }
    }

}