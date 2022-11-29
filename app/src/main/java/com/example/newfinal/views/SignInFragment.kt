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
import com.example.newfinal.databinding.SignInBinding
import com.google.firebase.auth.FirebaseUser


class SignInFragment: Fragment(R.layout.sign_in) {

    private lateinit var binding: SignInBinding
    private lateinit var viewModel: AuthenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(AuthenViewModel::class.java)
        viewModel.userData.observe(this, Observer<FirebaseUser>(){
            newName ->
            if(newName != null){
                findNavController().navigate(R.id.action_signInFragment_to_options_fragment2)
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SignInBinding.bind(view)

        Log.e("aaa", "smth")
        binding.signInButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
            var email: String = binding.emailPhone!!.text.toString()
            var pass: String = binding.password!!.text.toString()
            Log.e(email, pass)
            if(!email.isEmpty() && !pass.isEmpty()){
                viewModel.signIn(email, pass)
            }
            }
        })
        binding.signUpInsideIn.setOnClickListener {
            Log.e("check", "clicked")
            findNavController().navigate(R.id.action_signInFragment_to_sign_Up2)
        }
    }
}