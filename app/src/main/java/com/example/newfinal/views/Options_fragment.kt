package com.example.newfinal.views

import AuthenViewModel
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newfinal.R
import com.example.newfinal.databinding.OptionsGenreBinding

import com.example.newfinal.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class Options_fragment : Fragment(R.layout.options_genre)  {

    private lateinit var binding: OptionsGenreBinding
    private lateinit var drRef: DatabaseReference
    private lateinit var viewModel: AuthenViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(AuthenViewModel::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = com.example.newfinal.databinding.OptionsGenreBinding.bind(view)

        val tt = binding.nameUser

        getItem(tt)


        binding.searchBut?.setOnClickListener {
            findNavController().navigate(R.id.action_options_fragment2_to_searchFragment)
        }

        binding.wishl?.setOnClickListener{
            findNavController().navigate(R.id.action_options_fragment2_to_bookWishlistRecyclerView)
        }


        binding.profil?.setOnClickListener {
            findNavController().navigate(R.id.action_options_fragment2_to_personal_fragment)
        }

        binding.signOut.setOnClickListener{
            viewModel.signOut()
            findNavController().navigate(R.id.action_options_fragment2_to_signInFragment)
        }

    }

    private fun getItem(tt : TextView) {
        auth= FirebaseAuth.getInstance()
        drRef = FirebaseDatabase.getInstance().getReference("Users")
        drRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    // for(empSnap in snapshot.children){
                    // val empData = empSnap.getValue(Users::class.java)
                    val empSnap = snapshot.child(auth.currentUser!!.uid).getValue(Users::class.java)
                    //println(empSnap!!.full_name)

                    val r = empSnap!!.full_name.toString()
                    tt.setText(r)

                    //emaill = em
                    // passs = empSnap.pass.toString()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}