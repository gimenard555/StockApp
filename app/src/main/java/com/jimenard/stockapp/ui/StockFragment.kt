package com.jimenard.stockapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimenard.stockapp.app.util.BaseFragment
import com.jimenard.stockapp.databinding.FragmentStockBinding
import com.jimenard.stockapp.repository.Status
import com.jimenard.stockapp.repository.model.Stock
import com.jimenard.stockapp.ui.util.BaseRecyclerAdapter
import com.jimenard.stockapp.ui.util.logError
import com.jimenard.stockapp.ui.views.AProduct
import com.jimenard.stockapp.ui.views.ProcessType
import com.jimenard.stockapp.ui.views.ProductsPopUp
import com.jimenard.stockapp.viewmodel.BaseViewModelFactory
import com.jimenard.stockapp.viewmodel.ManagerViewModel
import javax.inject.Inject

class StockFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<ManagerViewModel>
    private val viewModel: ManagerViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentStockBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentStockBinding.inflate(this.layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.addProduct.setOnClickListener { addNewProduct() }
        getProducts()
    }

    private fun addNewProduct() {
        ProductsPopUp.startPopUp(requireContext(), null) { product, _ ->
            this.viewModel.saveProduct(product).observe(this.viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            getProducts()
                        }
                        Status.ERROR -> {
                            logError(resource.message)
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })

        }
    }

    private fun getProducts() {
        this.viewModel.getProducts().observe(this.viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val products = resource.data ?: ArrayList()
                        if (products.isEmpty()) {
                            this.binding.noData.visibility = View.VISIBLE
                            this.binding.stockHeader.visibility = View.GONE
                            this.binding.products.visibility = View.GONE
                        } else {
                            chargeProducts(products)
                            this.binding.noData.visibility = View.GONE
                            this.binding.stockHeader.visibility = View.VISIBLE
                            this.binding.products.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR -> {
                        logError(resource.message)
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun chargeProducts(products: List<Stock>) {
        this.binding.products.apply {
            adapter = BaseRecyclerAdapter(
                products,
                AProduct(),
                { _: View, product: Stock, _: Int ->
                    selectedProduct(product)
                }
            )
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun selectedProduct(selected: Stock) {
        ProductsPopUp.startPopUp(requireContext(), selected) { product, process ->
            when (process) {
                ProcessType.DELETE -> deleteCProduct(product)
                ProcessType.UPDATE -> updateProduct(product)
                ProcessType.CREATE -> {
                }
            }
        }
    }

    private fun updateProduct(product: Stock) {
        this.viewModel.updateProduct(product).observe(this.viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        getProducts()
                    }
                    Status.ERROR -> {
                        logError(resource.message)
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun deleteCProduct(product: Stock) {
        this.viewModel.deleteProduct(product).observe(this.viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        getProducts()
                    }
                    Status.ERROR -> {
                        logError(resource.message)
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
}