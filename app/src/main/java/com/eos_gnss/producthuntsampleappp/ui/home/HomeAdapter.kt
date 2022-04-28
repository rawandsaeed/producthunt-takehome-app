package com.eos_gnss.producthuntsampleappp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eos_gnss.producthuntsampleappp.GetPostsQuery
import com.eos_gnss.producthuntsampleappp.databinding.HomeRecyclerviewItemRowBinding

class HomeAdapter(
    private val posts: List<GetPostsQuery.Edge>,
    private val clickListener: (GetPostsQuery.Edge) -> Unit
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val mutablePosts = mutableListOf<GetPostsQuery.Edge>()

    init {
         mutablePosts.addAll(posts)
    }

    class HomeViewHolder constructor(private val binding: HomeRecyclerviewItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindPost(post: GetPostsQuery.Edge, listener: (GetPostsQuery.Edge) -> Unit){
            binding.postTitle.text = post.node.name
            binding.postTagline.text = post.node.tagline
            binding.postVoteCount.text = post.node.votesCount.toString()
            binding.container.setOnClickListener {
                listener(post)
            }
            Glide.with(binding.root)
                .load(post.node.thumbnail?.url)
                .into(binding.postProfile)
        }
        companion object {
            fun from(parent: ViewGroup): HomeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeRecyclerviewItemRowBinding.inflate(layoutInflater, parent, false)
                return HomeViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val post = mutablePosts[position]
        holder.bindPost(post, clickListener)
    }

    override fun getItemCount(): Int {
        return mutablePosts.size
    }

    fun setUpData(posts: List<GetPostsQuery.Edge>) {
        mutablePosts.addAll(posts)

    }
}

