package br.com.nandoligeiro.samplearch.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nandoligeiro.samplearch.R
import br.com.nandoligeiro.samplearch.data.network.RequestState
import br.com.nandoligeiro.samplearch.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        lifecycleScope.launchWhenStarted {
            homeViewModel.state.collect { state ->
                when (state) {
                    is RequestState.Loading -> {}
                    is RequestState.Success -> { homeAdapter.notifyAdapter(state.data) }
                    is RequestState.Error -> {
                        if (!state.consumed) {
                            Toast.makeText(
                                activity,
                                "Error!",
                                Toast.LENGTH_SHORT).show()
                            state.consumed = true
                        }
                    }
                }
            }
        }

    }

    private fun setupAdapter() {
        with(binding.recyclerView) {
            layoutManager =
                LinearLayoutManager(this@HomeFragment.activity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            homeAdapter = HomeAdapter {
                //Toast.makeText(this@HomeFragment.activity, "HOme", Toast.LENGTH_SHORT).show()
                view?.let { view ->

                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)

                }
            }
            adapter = homeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}