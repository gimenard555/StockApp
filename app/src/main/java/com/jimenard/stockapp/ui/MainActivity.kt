package com.jimenard.stockapp.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.jimenard.stockapp.R
import com.jimenard.stockapp.app.util.BaseActivity
import com.jimenard.stockapp.databinding.ActivityMainBinding
import com.jimenard.stockapp.ui.util.changeSelected
import com.jimenard.stockapp.ui.util.changeUnselected

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.main_fragment)
        val navController = (fragment as NavHostFragment).navController
        marketplaceSelected()
        this.binding.manager.setOnClickListener {
            managerSelected()
            navController.navigate(R.id.manager_fragment)
        }
        this.binding.marketplace.setOnClickListener {
            marketplaceSelected()
            navController.navigate(R.id.marketplace_fragment)
        }
    }

    private fun marketplaceSelected() {
        this.binding.marketplace.changeSelected()
        this.binding.manager.changeUnselected()
    }

    private fun managerSelected() {
        this.binding.manager.changeSelected()
        this.binding.marketplace.changeUnselected()
    }
}