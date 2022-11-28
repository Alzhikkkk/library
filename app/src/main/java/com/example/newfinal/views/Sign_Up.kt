package com.example.newfinal.views

import AuthenViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.newfinal.R
import com.example.newfinal.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseUser

class Sign_Up : Fragment(R.layout.activity_sign_up){

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: AuthenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(AuthenViewModel::class.java)
        viewModel.userData.observe(this, Observer<FirebaseUser>(){
                newName ->
            if(newName != null){
                findNavController().navigate(R.id.action_sign_Up2_to_signInFragment)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ActivitySignUpBinding.bind(view)
        Log.e("aaa", "smth")



        binding.signUpButton.setOnClickListener {
            var email: String = binding.emailSignUp!!.text.toString()
            var pass: String = binding.passSignUp!!.text.toString()

            if(!email.isEmpty() && !pass.isEmpty()){
                viewModel.register(email, pass)
            }
        }

    }

}