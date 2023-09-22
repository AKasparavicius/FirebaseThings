package lt.arnas.firebasethings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
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
    }
}