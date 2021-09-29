package br.com.nandoligeiro.samplearch.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nandoligeiro.samplearch.data.repository.home.RepoData
import br.com.nandoligeiro.samplearch.databinding.CardHomeBinding

class HomeAdapter(private val listener: (RepoData) -> Unit) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var repositoryList = ArrayList<RepoData>()

    companion object{
        const val EMPTY_LIST = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = CardHomeBinding.inflate(LayoutInflater.from(parent.context))
        return HomeViewHolder(binding)
    }

    override fun getItemCount() = if (repositoryList.isNullOrEmpty()) EMPTY_LIST else repositoryList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindingView(repositoryList[position],listener)
    }

    fun notifyAdapter(gitRepositoryList: List<RepoData>){
        repositoryList = gitRepositoryList as ArrayList<RepoData>
        notifyDataSetChanged()
    }

    class HomeViewHolder(private val binding: CardHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindingView(repo: RepoData, listener: (RepoData) -> Unit) {
            binding.username.text = repo.authorName
            binding.title.text = repo.repoName
            binding.description.text = repo.description
            binding.forks.text = repo.numberOfForks.toString()
            binding.stars.text = repo.numberOfStars.toString()

            itemView.setOnClickListener{
                listener(repo)
            }
        }
    }
}
