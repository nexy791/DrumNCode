package com.test.testfordrumncode

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.request.CachePolicy
import com.test.testfordrumncode.di.dataModule
import com.test.testfordrumncode.di.dbModule
import com.test.testfordrumncode.di.dispatcherModule
import com.test.testfordrumncode.di.domainDi
import com.test.testfordrumncode.di.mapperModule
import com.test.testfordrumncode.di.networkModule
import com.test.testfordrumncode.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

// maybe need add koin & image loader to separate classes for better readability
class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    dispatcherModule,
                    domainDi,
                    dataModule,
                    mapperModule,
                    presentationModule,
                    dbModule
                )
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .allowHardware(false)
            .build()
    }

}