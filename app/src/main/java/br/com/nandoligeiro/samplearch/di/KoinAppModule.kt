package br.com.nandoligeiro.samplearch.di

import br.com.nandoligeiro.samplearch.data.repository.home.HomeRepository
import br.com.nandoligeiro.samplearch.domain.repository.IHomeRepository
import br.com.nandoligeiro.samplearch.presentation.home.HomeViewModel
import org.koin.dsl.module

val appModule = module {

    single<IHomeRepository> { HomeRepository(get()) }
    factory { HomeViewModel(get()) }
    factory { HomeRepository(get()) }
}

