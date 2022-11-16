package com.example.newfinal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newfinal.databinding.SignInBinding

class SignInFragment: Fragment(R.layout.sign_in) {

    private lateinit var binding: SignInBinding
    private lateinit var vm: MainViewModel
    private  var SAVED_TEXT_KEY = "SavedText";
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("email", binding.emailPhone?.text.toString() )
        outState.putString("password", binding.password?.text.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SignInBinding.bind(view)

        Log.e("aaa", "smth")
        vm = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    if (savedInstanceState != null){
        var saveEmailPhone = savedInstanceState.getString("email")
        var savePassword = savedInstanceState.getString("password")
        Log.e("bro", saveEmailPhone.toString())
        binding.emailPhone?.text ?: saveEmailPhone;
        binding.password?.text?: savePassword;
    }

        binding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_options_fragment2)
        }
        binding.signUpInsideIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_sign_Up2)
        }
    }
}