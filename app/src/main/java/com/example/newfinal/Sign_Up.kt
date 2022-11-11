package com.example.newfinal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newfinal.databinding.ActivitySignUpBinding

class Sign_Up : Fragment(R.layout.activity_sign_up){

    private lateinit var binding: ActivitySignUpBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ActivitySignUpBinding.bind(view)

        binding.signUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_sign_Up2_to_signInFragment)
        }
    }

}