package lt.arnas.firebasethings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import lt.arnas.firebasethings.databinding.ActivityLoggedInScreenBinding

private lateinit var binding: ActivityLoggedInScreenBinding
private lateinit var firebaseAuth: FirebaseAuth

class LoggedInScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in_screen)

        binding = ActivityLoggedInScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.username.text = firebaseAuth.currentUser?.email.toString()

        logOutButton()

    }

    private fun logOutButton() {
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}