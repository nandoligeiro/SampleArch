package br.com.nandoligeiro.samplearch.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nandoligeiro.samplearch.R
import br.com.nandoligeiro.samplearch.data.model.Repo
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)
        viewModel.repositories.observe(viewLifecycleOwner, Observer {setupAdapter(it)})


    }

    private fun setupAdapter(repos: List<Repo>) {
        with(recyclerView){
            layoutManager = LinearLayoutManager(this@HomeFragment.activity,
                RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = HomeAdapter(repos) {
                Toast.makeText(this@HomeFragment.activity,"HOme", Toast.LENGTH_SHORT).show()
                view.let {view->
                    //findNavController(view).navigate(navDirections)
                }
            }

        }
    }
}