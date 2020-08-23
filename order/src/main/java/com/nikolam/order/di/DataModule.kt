package com.nikolam.order.di

import com.nikolam.order.data.IMenuRepository
import com.nikolam.order.data.ImplMenuRepository
import com.nikolam.order.data.network.FirebaseDataSource
import com.nikolam.order.data.network.NetworkDataSource
import org.koin.dsl.module


val dataModule = module {

    single<IMenuRepository>{ ImplMenuRepository(get()) }

    single<NetworkDataSource>{ FirebaseDataSource(get()) }

}