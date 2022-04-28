package com.eos_gnss.producthuntsampleappp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eos_gnss.producthuntsampleappp.GetPostsQuery
import com.eos_gnss.producthuntsampleappp.R
import com.eos_gnss.producthuntsampleappp.databinding.FragmentHomeBinding
import com.eos_gnss.producthuntsampleappp.util.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: HomeAdapter

    private val posts = mutableListOf<GetPostsQuery.Edge>()

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPosts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setupObserver()

    }

    private fun setUpUI() {
        linearLayoutManager = LinearLayoutManager(context)
        homeBinding.mainRecyclerView.layoutManager = linearLayoutManager

        adapter = HomeAdapter(emptyList()) {
            findNavController().navigate(
                R.id.action_homeFragment_to_postDetailFragment,
                bundleOf("post" to it.node.id)
            )
        }
        homeBinding.mainRecyclerView.adapter = adapter
    }

    private fun setupObserver() {

        viewModel.getPostLiveData.observe(viewLifecycleOwner, { result ->
            when(result) {
                is Resource.Success -> {
                    homeBinding.progressBar.visibility = View.GONE
                    homeBinding.mainRecyclerView.visibility = View.VISIBLE
                    homeBinding.errorLabel.visibility = View.GONE
                    setUpData(result.data)
                }
                is Resource.Failure -> {
                    homeBinding.progressBar.visibility = View.GONE
                    homeBinding.mainRecyclerView.visibility = View.GONE
                    homeBinding.errorLabel.visibility = View.VISIBLE
                    homeBinding.errorLabel.text = result.exception.message.toString()
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()

                }
                is Resource.Loading -> {
                    homeBinding.progressBar.visibility = View.VISIBLE
                    homeBinding.mainRecyclerView.visibility = View.GONE
                    homeBinding.errorLabel.visibility = View.GONE
                }
            }
        })
    }

    private fun setUpData(posts: List<GetPostsQuery.Edge>) {
        adapter.setUpData(posts)
        adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment = HomeFragment()
    }

}