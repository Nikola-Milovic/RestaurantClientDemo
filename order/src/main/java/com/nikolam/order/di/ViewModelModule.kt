package com.nikolam.order.di

import com.nikolam.order.ui.OrderViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel{ OrderViewModel(get(), get()) }
}