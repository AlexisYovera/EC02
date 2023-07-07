package com.alexis.anotafacil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.text.Editable
import android.util.Patterns
import android.widget.Toast
import androidx.core.os.HandlerCompat
import androidx.core.widget.addTextChangedListener
import com.alexis.anotafacil.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tilEmail.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled = validateEmailPassword(text.toString(),binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled = validateEmailPassword(binding.tilEmail.editText?.text.toString(),text.toString())
        }

        binding.btnLogin.setOnClickListener{
            miToast("Bienvenido")
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun validateEmailPassword(email:String, password:String):Boolean{
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && email =="ejemplo@idat.edu.pe"
        val isPasswordValid = password.isNotEmpty() && password == "123456"
        return isEmailValid && isPasswordValid
    }

    private fun miToast(message:String) {
        val miToast = Toast.makeText(this,message, Toast.LENGTH_LONG)
        miToast.show()
    }
}