package com.eos_gnss.producthuntsampleappp.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.eos_gnss.producthuntsampleappp.R
import com.eos_gnss.producthuntsampleappp.databinding.FragmentPostDetailBinding
import com.eos_gnss.producthuntsampleappp.util.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class PostDetailFragment : Fragment() {

    private lateinit var postDetailBinding: FragmentPostDetailBinding

    private val viewModel: PostDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.get("post") as String
        viewModel.getPostDetails(id)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postDetailBinding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return postDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getPosDetailLiveData.observe(viewLifecycleOwner, {result ->
            when(result) {
                is Resource.Success -> {
                    postDetailBinding.postDetailTitle.text = result.data.name
                    postDetailBinding.postDetailTagline.text = result.data.tagline
                    Glide.with(this)
                        .load(result.data.thumbnail?.url)
                        .into(postDetailBinding.postDetailProfile)
                    postDetailBinding.postDetailDescription.text = result.data.description
                    postDetailBinding.postDetailMakerName.text = result.data.makers[0].username

                    postDetailBinding.postDetailMakerName.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_postDetailFragment_to_profileFragment,
                            bundleOf("maker_id" to result.data.makers[0].id)
                        )
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

    companion object {
        @JvmStatic
        fun newInstance(): PostDetailFragment = PostDetailFragment()
    }


}