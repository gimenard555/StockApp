package com.jimenard.stockapp.app.di

import android.content.Context
import com.jimenard.stockapp.app.StockApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class]
)
/**
 * Componente para inyectar objetos en las actividades a partir del contexto de la aplicacion
 */
interface StockComponent : AndroidInjector<StockApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<StockApp>() {

        @BindsInstance
        abstract fun appContext(appContext: Context): Builder
    }
}