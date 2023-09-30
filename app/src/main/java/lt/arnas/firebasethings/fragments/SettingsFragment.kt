package lt.arnas.firebasethings.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import lt.arnas.firebasethings.R
import lt.arnas.firebasethings.databinding.FragmentProfileBinding
import lt.arnas.firebasethings.databinding.FragmentSettingsBinding

private lateinit var binding: FragmentSettingsBinding
private lateinit var firebaseAuth: FirebaseAuth

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()

    }
}