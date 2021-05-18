package com.jimenard.stockapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jimenard.stockapp.app.util.BaseFragment
import com.jimenard.stockapp.databinding.FragmentInvoiceBinding
import com.jimenard.stockapp.viewmodel.BaseViewModelFactory
import com.jimenard.stockapp.viewmodel.MarketplaceViewModel
import javax.inject.Inject

class InvoiceFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<MarketplaceViewModel>
    private val viewModel: MarketplaceViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentInvoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentInvoiceBinding.inflate(this.layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}