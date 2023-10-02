package lt.arnas.firebasethings.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import lt.arnas.firebasethings.R
import lt.arnas.firebasethings.databinding.FragmentProfileBinding

private lateinit var binding: FragmentProfileBinding
private lateinit var firebaseAuth: FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()

    }
}