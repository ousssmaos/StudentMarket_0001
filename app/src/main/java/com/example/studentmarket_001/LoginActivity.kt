package com.example.studentmarket_001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login3)

        auth = Firebase.auth

        val registerText: TextView = findViewById(R.id.signUpTv)

        registerText.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }
        val loginButton : Button= findViewById(R.id.loginBtn)
        loginButton.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val email: EditText = findViewById(R.id.emailEt)
        val password: EditText = findViewById(R.id.PassEt)

        if(email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT)
                .show()
            return
        }
            val emailInput = email.text.toString()
            val passwordInput = password.text.toString()

        auth.createUserWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext,"Authentification failed.${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }