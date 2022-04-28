package com.eos_gnss.producthuntsampleappp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.eos_gnss.producthuntsampleappp.databinding.FragmentProfileBinding
import com.eos_gnss.producthuntsampleappp.util.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val makerId = arguments?.get("maker_id") as String
        viewModel.getUserProfile(id = makerId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserProfileLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    profileBinding.userName.text = result.data.name
                    profileBinding.userHeadline.text = result.data.headline
                    Glide.with(this)
                        .load(result.data.profileImage)
                        .into(profileBinding.userProfileAvatar)

                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}