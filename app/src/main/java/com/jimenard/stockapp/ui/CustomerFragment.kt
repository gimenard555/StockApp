package com.jimenard.stockapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimenard.stockapp.app.util.BaseFragment
import com.jimenard.stockapp.databinding.FragmentCustomerBinding
import com.jimenard.stockapp.repository.Status
import com.jimenard.stockapp.repository.model.Customer
import com.jimenard.stockapp.ui.util.BaseRecyclerAdapter
import com.jimenard.stockapp.ui.util.logError
import com.jimenard.stockapp.ui.views.ACustomer
import com.jimenard.stockapp.ui.views.CustomerPopUp
import com.jimenard.stockapp.ui.views.ProcessType
import com.jimenard.stockapp.viewmodel.BaseViewModelFactory
import com.jimenard.stockapp.viewmodel.ManagerViewModel
import javax.inject.Inject

class CustomerFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<ManagerViewModel>
    private val viewModel: ManagerViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var binding: FragmentCustomerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentCustomerBinding.inflate(this.layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.addCustomer.setOnClickListener { addNewCustomer() }
        getCustomers()
    }

    private fun addNewCustomer() {
        CustomerPopUp.startPopUp(requireContext(), null) { customer, _ ->
            this.viewModel.saveCustomer(customer).observe(this.viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            getCustomers()
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

    private fun getCustomers() {
        this.viewModel.getCustomers().observe(this.viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val customers = resource.data ?: ArrayList()
                        if (customers.isEmpty()) {
                            this.binding.noData.visibility = View.VISIBLE
                            this.binding.customerHeader.visibility = View.GONE
                            this.binding.customers.visibility = View.GONE
                        } else {
                            chargeCustomers(customers)
                            this.binding.noData.visibility = View.GONE
                            this.binding.customerHeader.visibility = View.VISIBLE
                            this.binding.customers.visibility = View.VISIBLE
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

    private fun chargeCustomers(customers: List<Customer>) {
        this.binding.customers.apply {
            adapter = BaseRecyclerAdapter(
                customers,
                ACustomer(),
                { _: View, customer: Customer, _: Int ->
                    selectedCustomer(customer)
                }
            )
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun selectedCustomer(selected: Customer) {
        CustomerPopUp.startPopUp(requireContext(), selected) { customer, process ->
            when (process) {
                ProcessType.DELETE -> deleteCustomer(customer)
                ProcessType.UPDATE -> updateCustomer(customer)
                ProcessType.CREATE -> {
                }
            }
        }
    }

    private fun updateCustomer(customer: Customer) {
        this.viewModel.updateCustomer(customer).observe(this.viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        getCustomers()
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

    private fun deleteCustomer(customer: Customer) {
        this.viewModel.deleteCustomer(customer).observe(this.viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        getCustomers()
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