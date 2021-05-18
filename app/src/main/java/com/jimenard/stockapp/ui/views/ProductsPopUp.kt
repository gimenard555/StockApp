package com.jimenard.stockapp.ui.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.jimenard.stockapp.R
import com.jimenard.stockapp.databinding.ProductsPopUpBinding
import com.jimenard.stockapp.repository.model.Stock
import com.jimenard.stockapp.ui.util.initCurrentSettings

class ProductsPopUp(context: Context) : Dialog(context) {

    private lateinit var binding: ProductsPopUpBinding
    private var productId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ProductsPopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        this.binding.closeButton.setOnClickListener { closePopup() }
        if (product != null) {
            initUpdate(product!!)
        } else {
            this.binding.okButton.setOnClickListener {
                notifier.invoke(initProductData(), ProcessType.CREATE)
                closePopup()
            }
        }
    }

    private fun initUpdate(product: Stock) {
        this.binding.deleteButton.visibility = View.VISIBLE
        this.binding.okButton.text = context.resources.getString(R.string.update_product)
        initUpdateProduct(product)
        this.binding.okButton.setOnClickListener {
            notifier.invoke(initProductData(), ProcessType.UPDATE)
            closePopup()
        }
        this.binding.deleteButton.setOnClickListener {
            notifier.invoke(initProductData(), ProcessType.DELETE)
            closePopup()
        }
    }

    private fun initProductData() = Stock(
        productId = this.productId,
        productImage = this.binding.productImage.text.toString(),
        productName = this.binding.productName.text.toString(),
        productPrice = this.binding.productPrice.text.toString(),
        productQuantity = this.binding.productQuantity.text.toString().toInt()
    )


    private fun initUpdateProduct(product: Stock) {
        this.binding.productImage.setText(product.productImage)
        this.binding.productName.setText(product.productName)
        this.binding.productPrice.setText(product.productPrice)
        this.binding.productQuantity.setText(product.productQuantity.toString())
        this.productId = product.productId
    }


    companion object {

        lateinit var notifier: (Stock, ProcessType) -> Unit
        private var productPopUp: ProductsPopUp? = null
        private var product: Stock? = null

        /**
         * Se inicia el pop Up
         */
        fun startPopUp(
            context: Context,
            product: Stock? = null,
            notifier: (Stock, ProcessType) -> Unit
        ) {
            this.product = product
            this.productPopUp = ProductsPopUp(context)
            this.notifier = notifier
            this.productPopUp?.initCurrentSettings(context)
        }

        /**
         * Se cierra el popUp
         */
        fun closePopup() {
            this.productPopUp?.dismiss()
        }
    }
}