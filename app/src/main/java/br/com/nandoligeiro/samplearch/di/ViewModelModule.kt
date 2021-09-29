package br.com.nandoligeiro.samplearch.di

import br.com.nandoligeiro.samplearch.presentation.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
}