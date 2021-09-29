package br.com.nandoligeiro.samplearch.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel: ViewModel() {

    private var viewModelJob = SupervisorJob()
    protected var scopeJob = CoroutineScope(Dispatchers.Default + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        scopeJob.cancel()
    }
}