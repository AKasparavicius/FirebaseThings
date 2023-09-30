package lt.arnas.firebasethings.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import lt.arnas.firebasethings.LoggedInScreen
import lt.arnas.firebasethings.MainActivity
import lt.arnas.firebasethings.R
import lt.arnas.firebasethings.databinding.FragmentHomeBinding


private lateinit var binding: FragmentHomeBinding
private lateinit var firebaseAuth: FirebaseAuth

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()

        logoutAcc()

        binding.username.text = firebaseAuth.currentUser?.email
    }
    private fun logoutAcc() {
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
        }
    }
}