package com.example.unitbooks.di

import com.example.unitbooks.data.CatalogPagingSource
import com.example.unitbooks.data.Repository
import com.example.unitbooks.data.RepositoryImpl
import com.example.unitbooks.network.ClientRetrofit
import com.example.unitbooks.view.main.shop.ShopViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
//    viewModel { MainViewModel(get()) }
    viewModel { ShopViewModel(get()) }
}

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(remote = get(), pagingSource = CatalogPagingSource(get()))
    }
}

val networkModule = module {
    single { ClientRetrofit.create(androidContext()) }
}
