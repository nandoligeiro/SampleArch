package br.com.nandoligeiro.samplearch.presentation.home

import br.com.nandoligeiro.samplearch.data.network.RequestState
import br.com.nandoligeiro.samplearch.data.repository.home.RepoData
import br.com.nandoligeiro.samplearch.domain.repository.IHomeRepository
import br.com.nandoligeiro.samplearch.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val homeRepository: IHomeRepository): BaseViewModel() {

    private val _state = MutableStateFlow<RequestState<List<RepoData>>>(RequestState.Loading)
    val state: StateFlow<RequestState<List<RepoData>>> get() = _state

    init {
        getGithubRepositories()
    }

    fun getGithubRepositories() = scopeJob.launch {
        val repositories = homeRepository.getRepos()
        repositories.let {
            _state.value = RequestState.Success(it)
        }
    }

}