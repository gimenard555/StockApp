package com.jimenard.stockapp.ui.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jimenard.stockapp.ui.CustomerFragment
import com.jimenard.stockapp.ui.InvoiceFragment
import com.jimenard.stockapp.ui.StockFragment

class ManagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StockFragment()
            1 -> CustomerFragment()
            2 -> InvoiceFragment()
            else -> Fragment()
        }
    }

    companion object {
        const val NUM_PAGES = 3
    }
}