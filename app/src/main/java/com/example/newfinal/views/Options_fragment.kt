package com.example.newfinal.views

import AuthenViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newfinal.R
import com.example.newfinal.databinding.OptionsGenreBinding
import com.google.firebase.auth.FirebaseUser

class Options_fragment : Fragment(R.layout.options_genre)  {

    private lateinit var binding: OptionsGenreBinding

    private lateinit var viewModel: AuthenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(AuthenViewModel::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OptionsGenreBinding.bind(view)
        binding.poGenre.setOnClickListener{
            findNavController().navigate(R.id.action_options_fragment2_to_recycle_fragment)
        }

        binding.profil.setOnClickListener {
            findNavController().navigate(R.id.action_options_fragment2_to_personal_fragment)
        }

        binding.signOut.setOnClickListener{
            viewModel.signOut()
            findNavController().navigate(R.id.action_options_fragment2_to_signInFragment)
        }

    }
}