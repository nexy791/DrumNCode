package com.test.testfordrumncode.di

import android.content.Context
import androidx.room.Room
import com.test.data.source.shared.local.AppDataBase
import org.koin.dsl.module

val dbModule = module {
    single { provideDb(get()) }
    single { provideFeedDao(get()) }
    single { provideInfoDao(get()) }
}

fun provideDb(context: Context) =
    Room.databaseBuilder(
        context,
        AppDataBase::class.java, AppDataBase.DATABASE_NAME
    ).build()

fun provideFeedDao(db: AppDataBase) = db.feedDao
fun provideInfoDao(db: AppDataBase) = db.infoDao