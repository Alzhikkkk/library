import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.newfinal.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser


class AuthenViewModel(application: Application): AndroidViewModel(application){
    private var repository: AuthenticationRepository
    var userData: MutableLiveData<FirebaseUser>
    private var loggedStatus: MutableLiveData<Boolean>

    init {
        repository = AuthenticationRepository(application)
        userData = repository.firebaseUserMutableLiveData
        loggedStatus = repository.userLoggedMutableLiveData
    }
    fun register(email: String, pass: String){
        repository.register(email, pass)
    }

    fun signIn(email: String, pass: String){
        repository.login(email, pass)
    }

    fun signOut(){
        repository.signOut()
    }

}

