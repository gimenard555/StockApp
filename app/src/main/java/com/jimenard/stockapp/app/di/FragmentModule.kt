package com.jimenard.stockapp.app.di

import com.jimenard.stockapp.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Listado de las actividades que necesitan de inyecccion de dependencias para el view model
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeManagerFragment(): ManagerFragment

    @ContributesAndroidInjector
    abstract fun contributeMarketPlaceFragment(): MarketPlaceFragment

    @ContributesAndroidInjector
    abstract fun contributeCustomerFragment(): CustomerFragment

    @ContributesAndroidInjector
    abstract fun contributeStockFragment(): StockFragment

    @ContributesAndroidInjector
    abstract fun contributeInvoiceFragment(): InvoiceFragment
}