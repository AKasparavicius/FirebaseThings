package lt.arnas.firebasethings

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import lt.arnas.firebasethings.databinding.ActivityLoggedInScreenBinding
import lt.arnas.firebasethings.fragments.HomeFragment
import lt.arnas.firebasethings.fragments.ProfileFragment
import lt.arnas.firebasethings.fragments.SettingsFragment

class LoggedInScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLoggedInScreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in_screen)
        changeFragment(HomeFragment())

        binding = ActivityLoggedInScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.navBar.setOnItemReselectedListener {
            when(it.itemId) {
                R.id.home -> changeFragment(HomeFragment())
                R.id.settings -> changeFragment(SettingsFragment())
                R.id.profile -> changeFragment(ProfileFragment())
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}