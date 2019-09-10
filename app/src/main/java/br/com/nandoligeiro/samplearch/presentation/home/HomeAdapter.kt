package br.com.nandoligeiro.samplearch.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nandoligeiro.samplearch.R
import br.com.nandoligeiro.samplearch.data.model.Repo

class HomeAdapter(
    private val repos: List<Repo>,
    private val listener: () -> Unit
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindingView(listener)

    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindingView(listener: () -> Unit){
            itemView.setOnClickListener{
                listener()
            }
        }


    }
}