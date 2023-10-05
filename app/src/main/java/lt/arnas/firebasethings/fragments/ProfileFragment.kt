package lt.arnas.firebasethings.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import lt.arnas.firebasethings.LoggedInScreen
import lt.arnas.firebasethings.MainActivity
import lt.arnas.firebasethings.R
import lt.arnas.firebasethings.databinding.FragmentProfileBinding

private lateinit var binding: FragmentProfileBinding
private lateinit var firebaseAuth: FirebaseAuth
private lateinit var db: FirebaseFirestore

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.userEmail.text = firebaseAuth.currentUser?.email

        logOut()
        addData()

    }

    private fun addData() {
        binding.applyButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            val sName = binding.firstName.text.toString()
            val sSurname = binding.lastName.text.toString()

            val userMap = hashMapOf(
                "name" to sName,
                "surname" to sSurname
            )

            val userId = firebaseAuth.currentUser!!.uid

            db.collection("user").document(userId).set(userMap)
                .addOnSuccessListener {
                    Toast.makeText(activity, "Saved successfully!", Toast.LENGTH_LONG).show()
                    binding.firstName.text.clear()
                    binding.lastName.text.clear()
                    binding.progressBar.visibility = View.INVISIBLE
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.INVISIBLE
                }
        }
    }

    private fun logOut() {
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}