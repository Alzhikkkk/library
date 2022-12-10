package com.example.newfinal.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.newfinal.R
import com.example.newfinal.databinding.MainBookBinding
import com.example.newfinal.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Personal_fragment: Fragment(R.layout.main_book) {
    private lateinit var drRef: DatabaseReference
    private lateinit var binding: MainBookBinding
    private lateinit var arr: ArrayList<Users>
    private lateinit var auth: FirebaseAuth
    private lateinit var id : String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainBookBinding.bind(view)

        val tt = binding.profName
        val bb = binding.profEmail
        getItem(tt, bb)


        binding.editInfo.setOnClickListener{
            val em = bb.text.toString()
            val full = tt.text.toString()
            val pp = binding.profPass.text.toString()

            val user = Users(em, pp, full)
            drRef = FirebaseDatabase.getInstance().getReference("Users")
            drRef.child(auth.currentUser!!.uid).setValue(user)

            tt.setText(full)
            bb.setText(em)
        }

    }

    private fun getItem(tt : EditText, bb: EditText) {
        auth= FirebaseAuth.getInstance()
        drRef = FirebaseDatabase.getInstance().getReference("Users")
        drRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    // for(empSnap in snapshot.children){
                    // val empData = empSnap.getValue(Users::class.java)
                    val empSnap = snapshot.child(auth.currentUser!!.uid).getValue(Users::class.java)
                    //println(empSnap!!.full_name)

                    val r = empSnap!!.full_name.toString()
                    tt.setText(r)
                    //  full = r
                    val em = empSnap!!.email.toString()
                    bb.setText(em)
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