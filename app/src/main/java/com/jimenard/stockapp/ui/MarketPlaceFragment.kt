package com.jimenard.stockapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jimenard.stockapp.app.util.BaseFragment
import com.jimenard.stockapp.databinding.FragmentMarketplaceBinding
import com.jimenard.stockapp.repository.Status
import com.jimenard.stockapp.repository.model.Stock
import com.jimenard.stockapp.ui.util.logError
import com.jimenard.stockapp.ui.views.AMProduct
import com.jimenard.stockapp.ui.views.BaseGridAdapter
import com.jimenard.stockapp.viewmodel.BaseViewModelFactory
import com.jimenard.stockapp.viewmodel.MarketplaceViewModel
import javax.inject.Inject

class MarketPlaceFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<MarketplaceViewModel>
    private val viewModel: MarketplaceViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentMarketplaceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentMarketplaceBinding.inflate(this.layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProducts()
    }

    private fun initProducts() {
        this.viewModel.getProducts().observe(this.viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val products = resource.data ?: ArrayList()
                        if (products.isEmpty()) {
                            this.binding.noProducts.visibility = View.VISIBLE
                            this.binding.products.visibility = View.GONE
                        } else {
                            this.binding.noProducts.visibility = View.GONE
                            this.binding.products.visibility = View.VISIBLE
                            this.binding.products.apply {
                                adapter = BaseGridAdapter(
                                    requireContext(),
                                    products,
                                    AMProduct(),
                                ) { _: View, value: Stock?, _: Int ->

                                }
                            }
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
}