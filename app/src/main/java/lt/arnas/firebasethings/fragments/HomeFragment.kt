package lt.arnas.firebasethings.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import lt.arnas.firebasethings.LoggedInScreen
import lt.arnas.firebasethings.MainActivity
import lt.arnas.firebasethings.R
import lt.arnas.firebasethings.databinding.FragmentHomeBinding



class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.username.text = firebaseAuth.currentUser?.email

        addBalance()
        displayBalance()
    }

    private fun displayBalance() {
        val userId = firebaseAuth.currentUser!!.uid

        val ref = db.collection("user").document(userId)
            .collection("balance").document("balance")

        ref.get().addOnSuccessListener {
            val balance = it.data?.get("balance")?.toString()

            binding.balance.text = "$" + balance
        }
    }

    private fun addBalance() {
        val userId = firebaseAuth.currentUser!!.uid

        binding.addBalance.setOnClickListener {
            val aBalance = binding.moneyAmount.text.toString()
            val finalBalance = Integer.parseInt(aBalance)

            val userBalance = hashMapOf(
                "balance" to finalBalance
            )

            db.collection("user").document(userId).collection("balance")
                .document("balance")
                .update("balance", FieldValue.increment(finalBalance.toDouble()))
                .addOnSuccessListener {
                    Toast.makeText(activity, "Money added successfully!", Toast.LENGTH_SHORT)
                        .show()
                    binding.moneyAmount.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Something went wrong :(", Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }
}