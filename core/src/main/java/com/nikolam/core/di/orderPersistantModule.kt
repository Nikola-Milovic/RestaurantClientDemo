package com.nikolam.core.di

import com.nikolam.core.order.OrderPersistant
import org.koin.dsl.module

val orderPersistantModule = module {


    single{OrderPersistant()}

}