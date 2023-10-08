package com.test.testfordrumncode.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {

    single(named(IO_DISPATCHER)) { Dispatchers.IO }


}

const val IO_DISPATCHER = "IO"
