package com.jimenard.stockapp.app.di

import com.jimenard.stockapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Listado de las actividades que necesitan de inyecccion de dependencias para el view model
 */
@Module
abstract class ActivityModule {


    //Actividades que se Inyectan
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}