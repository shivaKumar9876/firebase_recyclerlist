package com.example.recyclerviewinfocom.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewinfocom.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.BtnSignIn.setOnClickListener {
            validateInputFields()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun validateInputFields() {
       val isValidUser  =  valiadateUserName()
        val isValidPassword = validatePassword()
           if (isValidUser && isValidPassword) {
               binding.etUsername.text?.clear()
               binding.etPassword.text?.clear()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun valiadateUserName(): Boolean {
        val inputEmail = binding.etUsername.text.toString()
        if (inputEmail.isEmpty()) {
            binding.etUsername.error = "Enter input"
            return false
        }
        if (inputEmail.length <= 9) {
            binding.etUsername.error = "User name must be at least 10 characters"
            return false
        }
        return true
    }

    fun validatePassword(): Boolean {
        val inputPassword = binding.etPassword.text.toString()

        if (inputPassword.isEmpty()) {
            binding.etPassword.error = "Empty password field"
            return false
        }
        val regex = "^(?=.*[A-Z])(?=.*[@#$%^&+*=!{}|;':\"\\\\|,.<>/?])(?=.*[0-9]).{7,}$"
        val result = Pattern.matches(regex,inputPassword)
        if(!result)
            binding.etPassword.error = "Password must be 7 characters with one uppercase alphabet and one special character and numeric"
        return result
    }
}