package lt.arnas.firebasethings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lt.arnas.firebasethings.databinding.ActivityLoggedInScreenBinding

private lateinit var binding: ActivityLoggedInScreenBinding

class LoggedInScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in_screen)

        binding = ActivityLoggedInScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}