package lt.arnas.firebasethings.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import lt.arnas.firebasethings.R
import lt.arnas.firebasethings.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        addBankAccount()

    }

    private fun addBankAccount() {
        val userId = firebaseAuth.currentUser!!.uid

        binding.addBankAcc.setOnClickListener {
            val bankAcc = binding.bankAccount.text.toString()

            val bankAccount = hashMapOf(
                "bankAcc" to bankAcc
            )

            if(binding.bankAccount.text.toString().isNotEmpty()
                && binding.bankAccount.text.toString().length == 20) {
                db.collection("user").document(userId)
                    .collection("balance")
                    .document("bankAcc")
                    .set(bankAccount)
                binding.bankAccount.text.clear()
                Toast.makeText(activity, "Bank account added successfully!\n " +
                        "You may now add funds to ALT36.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Invalid bank account!\n " +
                        "Please carefully check the field!", Toast.LENGTH_LONG).show()
            }
        }
    }
}