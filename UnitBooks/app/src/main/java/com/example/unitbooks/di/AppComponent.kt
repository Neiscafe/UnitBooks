package com.example.unitbooks.di

import com.example.unitbooks.data.CatalogPagingSource
import com.example.unitbooks.data.Repository
import com.example.unitbooks.data.RepositoryImpl
import com.example.unitbooks.data.SearchPagingSource
import com.example.unitbooks.database.RoomDataBase
import com.example.unitbooks.network.ClientRetrofit
import com.example.unitbooks.view.main.cart.viewmodel.CartViewModel
import com.example.unitbooks.view.main.shop.viewmodel.SearchViewModel
import com.example.unitbooks.view.main.shop.viewmodel.ShopViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShopViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { CartViewModel(get()) }
}

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(
            remote = get(),
            catalogSource = CatalogPagingSource(get()),
            searchSource = SearchPagingSource(get()),
            bookDao = get()
        )
    }
}

val networkModule = module {
    single { ClientRetrofit.create(androidContext()) }
}

val databaseModule = module {
    single { RoomDataBase.getDatabase(androidContext()).bookDao }
}

val appComponent = listOf(
    networkModule, databaseModule, repositoryModule, viewModelModule
)