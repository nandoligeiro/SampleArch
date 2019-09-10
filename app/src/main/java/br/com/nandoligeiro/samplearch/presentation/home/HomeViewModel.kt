package br.com.nandoligeiro.samplearch.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.nandoligeiro.samplearch.data.model.Repo
import br.com.nandoligeiro.samplearch.data.repository.home.HomeRepository


class HomeViewModel : ViewModel() {

    val repositories: MutableLiveData<List<Repo>> = HomeRepository().getRepositories()
}