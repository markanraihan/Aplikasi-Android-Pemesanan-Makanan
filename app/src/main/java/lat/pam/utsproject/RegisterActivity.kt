package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        val registerButton = findViewById<Button>(R.id.btnRegister)
        val usernameInput = findViewById<EditText>(R.id.etRegisterUsername)
        val passwordInput = findViewById<EditText>(R.id.etRegisterPassword)

        // Set click listener for the register button
        registerButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // Validate user input
            if (username.length < 3) {
                Toast.makeText(this, "Username harus minimal 3 huruf!", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password harus setidaknya 6 huruf", Toast.LENGTH_SHORT).show()
            } else {
                // Save user data using SharedPreferences
                val sharedPrefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
                sharedPrefs.edit {
                    putString("username", username)
                    putString("password", password)
                    apply()
                }

                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()

                // Go back to login screen after registration
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
