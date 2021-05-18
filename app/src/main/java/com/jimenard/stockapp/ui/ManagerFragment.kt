package com.jimenard.stockapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.jimenard.stockapp.app.util.BaseFragment
import com.jimenard.stockapp.databinding.FragmentManagerBinding
import com.jimenard.stockapp.ui.util.ManagerAdapter
import com.jimenard.stockapp.ui.util.changeSelected
import com.jimenard.stockapp.ui.util.changeUnselected

class ManagerFragment : BaseFragment() {

    private lateinit var binding: FragmentManagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentManagerBinding.inflate(this.layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initPages()
        customersSelected()
    }

    private fun initViews() {
        this.binding.customers.setOnClickListener {
            customersSelected()
            this.binding.managerPages.setCurrentItem(1, true)
        }
        this.binding.products.setOnClickListener {
            productsSelected()
            this.binding.managerPages.setCurrentItem(0, true)
        }
        this.binding.orders.setOnClickListener {
            ordersSelected()
            this.binding.managerPages.setCurrentItem(2, true)
        }
    }

    private fun initPages() {
        val pageAdapter = ManagerAdapter(this.requireActivity())
        this.binding.managerPages.adapter = pageAdapter
        this.binding.managerPages.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> productsSelected()
                    1 -> customersSelected()
                    2 -> ordersSelected()
                    else -> {
                    }
                }
            }
        })
    }

    private fun ordersSelected() {
        this.binding.orders.changeSelected()
        this.binding.customers.changeUnselected()
        this.binding.products.changeUnselected()

    }

    private fun productsSelected() {
        this.binding.orders.changeUnselected()
        this.binding.customers.changeUnselected()
        this.binding.products.changeSelected()
    }

    private fun customersSelected() {
        this.binding.orders.changeUnselected()
        this.binding.customers.changeSelected()
        this.binding.products.changeUnselected()
    }
}