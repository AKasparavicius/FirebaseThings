package lt.arnas.firebasethings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import lt.arnas.firebasethings.databinding.ActivityMainBinding
import lt.arnas.firebasethings.databinding.ActivitySecondBinding

private lateinit var binding: ActivitySecondBinding
private lateinit var firebaseAuth: FirebaseAuth

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        signUpButton()
        backButton()

    }

    fun signUpButton() {
        binding.confirmRegisterBtn.setOnClickListener {
            val email = binding.emailFieldRgstr.text.toString()
            val password = binding.passwordFieldRgstr.text.toString()
            val passwordConfirm = binding.passwordFieldCnfrm.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
                if (password.equals(passwordConfirm)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(),
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Passwords are not matching!",
                        Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "One or more fields are empty!",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun backButton() {
        binding.goBackBtn.setOnClickListener {
            finish()
        }
    }
}