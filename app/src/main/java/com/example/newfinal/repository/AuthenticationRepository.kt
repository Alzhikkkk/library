package com.example.newfinal.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository{
    var firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>
    lateinit var userLoggedMutableLiveData: MutableLiveData<Boolean>
    private var auth: FirebaseAuth
    var application : Application

    constructor(application: Application){
        this.application = application
        auth= FirebaseAuth.getInstance()
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        if (auth.currentUser !=null){
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    public fun register(email: String, pass: String){
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(OnCompleteListener <AuthResult>{
                task ->
            if (task.isSuccessful){
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            }else{
                Log.e("err", task.exception!!.message.toString() )
                Toast.makeText(application, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    public fun login(email: String, pass: String){
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(OnCompleteListener <AuthResult>{
                task ->
            if (task.isSuccessful){
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            }else{
                Log.e("err", task.exception!!.message.toString() )
                Toast.makeText(application, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    public fun signOut(){
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }
}