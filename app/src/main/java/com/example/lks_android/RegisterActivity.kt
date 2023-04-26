package com.example.lks_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var db:DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        db=DatabaseHelper(this)

        val nameText=findViewById<EditText>(R.id.register_input_name)
        val emailText=findViewById<EditText>(R.id.register_input_email)
        val passwordText=findViewById<EditText>(R.id.register_input_password)
        val confirmPasswordText=findViewById<EditText>(R.id.register_input_confirmPass)
        val registerButton=findViewById<Button>(R.id.btn_register)
        registerButton.setOnClickListener {
            val name = nameText.text.toString().trim()
            val email = emailText.text.toString().trim()
            val password = passwordText.text.toString().trim()
            val confirmPassword = confirmPasswordText.text.toString().trim()

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (name.isEmpty()) {
                Toast.makeText(this, "Nama Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Email Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else if (confirmPassword.isEmpty()) {
                Toast.makeText(this, "Confirm Passoword Tidak beleh Kosong", Toast.LENGTH_SHORT)
                    .show()
            } else if (confirmPassword != password) {
                Toast.makeText(this, "Confirm Password harus sama !", Toast.LENGTH_SHORT).show()
            } else
                if (email.matches(emailPattern.toRegex())) {
                        db.addUser(name,email,password)
                    Toast.makeText(this,"Berhasil Melakukan Registrasi",Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Email Harus mengguanakan @", Toast.LENGTH_SHORT).show()
                }
            }


        val moveText=findViewById<TextView>(R.id.txt_to_login)
        moveText.setOnClickListener{
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val moveImage=findViewById<ImageView>(R.id.row_back_register)
        moveImage.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}